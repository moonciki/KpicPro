package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.UserLevel;
import cn.kpic.juwin.domain.UserNews;
import cn.kpic.juwin.domain.vo.JmsUpgrade;
import cn.kpic.juwin.mapper.MsgMapper;
import cn.kpic.juwin.mapper.UserNewsMapper;
import cn.kpic.juwin.service.MsgService;
import cn.kpic.juwin.service.UserNewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by bjsunqinwen on 2016/4/5.
 */
@Service
@Transactional
public class UserNewsQueueMessageListener implements MessageListener{

    private Logger logger = Logger.getLogger(UserNewsQueueMessageListener.class);

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private UserNewsMapper userNewsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            UserNews userNews = (UserNews)objectMessage.getObject();
            Msg msg = new Msg();
            msg.setType(0);
            msg.setUserId(userNews.getUserId());
            this.msgMapper.update(msg);
            /** 清除缓存*/
            this.redisTemplate.delete(RedisCacheKey.USER_NEWS+userNews.getUserId());
            this.userNewsMapper.save(userNews);
        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : send user news error !" + e.getMessage());
            e.printStackTrace();
        }
    }
}
