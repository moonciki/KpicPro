package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.KpicConstant;
import cn.kpic.juwin.domain.TopicImg;
import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.vo.JmsTopicImg;
import cn.kpic.juwin.mapper.TopicImgMapper;
import cn.kpic.juwin.mapper.TopicPostMapper;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/10.
 */
@Service
@Transactional
public class TopicPostImgQueueMessageListener implements MessageListener {

    private static Logger logger = Logger.getLogger(TopicPostImgQueueMessageListener.class);

    @Autowired
    private TopicImgMapper topicImgMapper;

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Override
    public void onMessage(Message message) {

        ObjectMessage objMsg = (ObjectMessage)message;
        /** 为了尽可能减少访问数据库的io访问，这里的添加采用批量操作*/
        try{
            List<TopicImg> list = new ArrayList();
            JmsTopicImg jmsTopicImg = (JmsTopicImg)objMsg.getObject();
            Document doc = Jsoup.parse(jmsTopicImg.getContent());
            Elements media = doc.select("[src]");
            for (Element src : media) {
                if (src.tagName().equals("img")){
                    /** 这里拿到用户上传的图片，根据七牛url获取，防止了表情图片的乱入*/
                    String url = src.attr("src").substring(0, 37);
                    if(url.equals(KpicConstant.QINIU_IMG_URL1) || url.equals(KpicConstant.QINIU_IMG_URL2)){
                        TopicImg topicImg = new TopicImg();
                        topicImg.setTopicId(jmsTopicImg.getTopicPostid());
                        topicImg.setImagePath(StringDeal.removeQuestionMark(src.attr("src")));
                        topicImg.setImgKey("1");
                        list.add(topicImg);
                    }
                }

                if(src.tagName().equals("embed")){
                    TopicImg topicImg = new TopicImg();
                    topicImg.setTopicId(jmsTopicImg.getTopicPostid());
                    topicImg.setImagePath(src.attr("src"));
                    topicImg.setImgKey("2");
                    list.add(topicImg);
                }

                if(src.tagName().equals("source")){
                    TopicImg topicImg = new TopicImg();
                    topicImg.setTopicId(jmsTopicImg.getTopicPostid());
                    topicImg.setImagePath(src.attr("src"));
                    topicImg.setImgKey("3");
                    list.add(topicImg);
                }
            }
            if(list.size() > 0){
                topicImgMapper.save(list);
                TopicPost topicPost = new TopicPost();
                topicPost.setId(jmsTopicImg.getTopicPostid());
                topicPost.setNum(list.size());
                this.topicPostMapper.update(topicPost);
            }
        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : insert topic post img error !\n" + e.getMessage());
            e.printStackTrace();
        }
    }

}
