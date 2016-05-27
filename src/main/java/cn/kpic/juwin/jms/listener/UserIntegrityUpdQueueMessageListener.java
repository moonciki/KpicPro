package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import cn.kpic.juwin.mapper.UserIntegrityMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
@Service
@Transactional
public class UserIntegrityUpdQueueMessageListener implements MessageListener{

    private Logger logger = Logger.getLogger(UserIntegrityUpdQueueMessageListener.class);

    @Autowired
    private UserIntegrityMapper userIntegrityMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            JmsUserIntegrityUpd jmsUserIntegrityUpd = (JmsUserIntegrityUpd)objectMessage.getObject();
            if(jmsUserIntegrityUpd != null){
                if(jmsUserIntegrityUpd.getType() == 2){//减少节操值
                    Map params = new HashMap();
                    Integer num = this.userIntegrityMapper.getByUserId(jmsUserIntegrityUpd.getUserId());
                    if((num - jmsUserIntegrityUpd.getNum()) < 0){
                        params.put("num", 0);
                    }else{
                        params.put("num", jmsUserIntegrityUpd.getNum());
                    }
                    params.put("userId", jmsUserIntegrityUpd.getUserId());
                    this.userIntegrityMapper.reduceNum(params);
                }else{
                    Map params = new HashMap();
                    Integer num = this.userIntegrityMapper.getByUserId(jmsUserIntegrityUpd.getUserId());
                    if((num + jmsUserIntegrityUpd.getNum()) > 100){
                        params.put("num", 0);
                    }else{
                        params.put("num", jmsUserIntegrityUpd.getNum());
                    }
                    params.put("userId", jmsUserIntegrityUpd.getUserId());
                    this.userIntegrityMapper.addNum(params);
                }

                /** 清缓存*/
                String key = RedisCacheKey.USER_JC_NUM+"_"+jmsUserIntegrityUpd.getUserId();
                redisTemplate.delete(key);

            }
        }catch (JMSException e){
            logger.error("forJMS : update user integrity error ! "+e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : update user integrity error ! ");
            e.printStackTrace();
        }
    }
}
