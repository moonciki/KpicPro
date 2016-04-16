package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.domain.UserLevel;
import cn.kpic.juwin.domain.vo.JmsUpgrade;
import cn.kpic.juwin.mapper.UserLevelMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by bjsunqinwen on 2016/3/28.
 */
@Service
@Transactional
public class UpgradeQueueMessageListener implements MessageListener{

    private Logger logger = Logger.getLogger(UpgradeQueueMessageListener.class);

    @Autowired
    private UserLevelMapper userLevelMapper;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            JmsUpgrade jmsUpgrade = (JmsUpgrade)objectMessage.getObject();
            UserLevel userLevel = userLevelMapper.getByUserId(jmsUpgrade.getUserId());
            int score_z = userLevel.getScore() + jmsUpgrade.getAddscore();//总分
            int score_s = userLevel.getLevel() == 0 ? 15 : userLevel.getLevel() * 15 * 2;
            if(score_z >= score_s){
                userLevel.setLevel(userLevel.getLevel() + 1);
                userLevel.setScore(score_z - score_s);//将剩余的分存入数据库
            }else{
                userLevel.setScore(score_z);
            }
            userLevelMapper.update(userLevel);

        }catch (JMSException e){
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : upgrade error ! userId = \n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
