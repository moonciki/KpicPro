package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.KpicConstant;
import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.TopicImg;
import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.vo.JmsTopicImg;
import cn.kpic.juwin.mapper.TopicPostMapper;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/19 0019.
 */
@Service
@Transactional
public class TopicUpdQueueMessageListener implements MessageListener {

    private static Logger logger = Logger.getLogger(TopicUpdQueueMessageListener.class);

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {

        ObjectMessage objectMessage = (ObjectMessage)message;

        try{
            Long id = (Long)objectMessage.getObject();
            TopicPost topicPost = new TopicPost();
            topicPost.setId(id);
            topicPost.setUpdateTime(new Date());
            topicPost.setReplyNum(1);
            topicPostMapper.update(topicPost);

            TopicPost topicPost1 = topicPostMapper.getById(id);
            if(topicPost1 != null){
                /** 清除某团前十页缓存*/
                redisTemplate.delete(RedisCacheKey.PBAR_PAGE + topicPost1.getPbarId());
            }
        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : insert topic post img error !\n" + e.getMessage());
            e.printStackTrace();
        }
    }

}
