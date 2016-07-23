package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.SystemMsg;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.mapper.MsgMapper;
import cn.kpic.juwin.service.SystemMsgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/4/27.
 */
@Service
@Transactional
public class SystemMsgQueueMessageListener implements MessageListener {

    private Logger logger = Logger.getLogger(SystemMsgQueueMessageListener.class);

    @Autowired
    private SystemMsgService systemMsgService;

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{

            JmsSystemMsg jmsSystemMsg = (JmsSystemMsg)objectMessage.getObject();
            SystemMsg systemMsg = new SystemMsg();
            systemMsg.setUserId(jmsSystemMsg.getUserId());
            systemMsg.setContent(jmsSystemMsg.getContent());
            systemMsg.setCreateTime(new Date());
            systemMsg.setIsdel(0);
            systemMsg.setTitle(jmsSystemMsg.getTitle());

            this.systemMsgService.saveSystemMsg(systemMsg);

            Msg msg = new Msg();
            msg.setType(1);
            msg.setUserId(jmsSystemMsg.getUserId());
            this.msgMapper.update(msg);

            /** 清除缓存*/
            this.redisTemplate.delete(RedisCacheKey.USER_NEWS + jmsSystemMsg.getUserId());

        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : insert system msg error !\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
