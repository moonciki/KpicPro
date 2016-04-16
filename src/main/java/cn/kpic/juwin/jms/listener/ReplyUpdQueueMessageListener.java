package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.domain.ReplyPost;
import cn.kpic.juwin.mapper.ReplyPostMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReplyUpdQueueMessageListener implements MessageListener{

    private Logger logger = Logger.getLogger(UserNewsQueueMessageListener.class);

    @Autowired
    private ReplyPostMapper replyPostMapper;

    @Override
    @Transactional
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            Long replyId = (Long)objectMessage.getObject();
            ReplyPost replyPost = replyPostMapper.getById(replyId);
            replyPost.setReplyNum(replyPost.getReplyNum() + 1);
            this.replyPostMapper.update(replyPost);
        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : send user news error !" + e.getMessage());
            e.printStackTrace();
        }
    }
}
