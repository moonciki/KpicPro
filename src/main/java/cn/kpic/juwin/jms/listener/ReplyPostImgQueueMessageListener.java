package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.KpicConstant;
import cn.kpic.juwin.domain.ReplyImg;
import cn.kpic.juwin.domain.vo.JmsReplyImg;
import cn.kpic.juwin.mapper.ReplyImgMapper;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/18.
 */
@Service
@Transactional
public class ReplyPostImgQueueMessageListener implements MessageListener {

    private static Logger logger = Logger.getLogger(ReplyPostImgQueueMessageListener.class);

    @Autowired
    private ReplyImgMapper replyImgMapper;

    @Override
    public void onMessage(Message message) {

        ObjectMessage objMsg = (ObjectMessage)message;

        try{
            List<ReplyImg> list = new ArrayList();
            JmsReplyImg jmsReplyImg = (JmsReplyImg)objMsg.getObject();
            Document doc = Jsoup.parse(jmsReplyImg.getContent());
            Elements media = doc.select("[src]");
            for (Element src : media) {
                if (src.tagName().equals("img")){
                    /** 这里拿到用户上传的图片，根据七牛url获取，防止了表情图片的乱入*/
                    if(src.attr("src").substring(0, 37).equals(KpicConstant.QINIU_IMG_URL1)){

                        ReplyImg replyImg = new ReplyImg();
                        replyImg.setTopicId(jmsReplyImg.getTopicPostid());
                        replyImg.setImagePath(StringDeal.removeQuestionMark(src.attr("src")));
                        replyImg.setReplyId(jmsReplyImg.getReplyPostId());

                        list.add(replyImg);
                    }
                }
            }
            if(list.size() > 0){
                replyImgMapper.save(list);
            }
        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : insert reply post img error !\n" + e.getMessage());
            e.printStackTrace();
        }

    }
}
