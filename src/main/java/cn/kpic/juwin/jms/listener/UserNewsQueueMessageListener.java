package cn.kpic.juwin.jms.listener;

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

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            UserNews userNews = (UserNews)objectMessage.getObject();
            Msg msg = new Msg();
            msg.setType(0);
            msg.setUserId(userNews.getUserId());
            Integer num = this.msgMapper.notRead(msg);
            int num2 = 0;
            if(num == null){num2 = 1;}else{num2 = num + 1;}
            msg.setNum(num2);
            this.msgMapper.update(msg);
            this.userNewsMapper.save(userNews);
        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : send user news error !" + e.getMessage());
            e.printStackTrace();
        }
    }
}
