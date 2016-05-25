package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import cn.kpic.juwin.mapper.PbarMapper;
import cn.kpic.juwin.mapper.UserMapper;
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
 * Created by bjsunqinwen on 2016/4/9.
 */
@Service
@Transactional
public class PbarUpdQueueMessageListener implements MessageListener{

    private Logger logger = Logger.getLogger(PbarUpdQueueMessageListener.class);

    @Autowired
    private PbarMapper pbarMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {

        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            JmsUpdPbar jmsUpdPbar = (JmsUpdPbar)objectMessage.getObject();
            if(jmsUpdPbar != null){
                if(jmsUpdPbar.getType() == 0){//修改帖子数
                    this.pbarMapper.addTopicNum(jmsUpdPbar.getPbarId());
                }else if(jmsUpdPbar.getType() == 1){//修改关注数
                    this.pbarMapper.addFocusNum(jmsUpdPbar.getPbarId());
                }else if (jmsUpdPbar.getType() == 2){//减小关注数
                    this.pbarMapper.delFocusNum(jmsUpdPbar.getPbarId());
                }else{}

                /** 清缓存*/
                String key = RedisCacheKey.PBAR_INDEX + jmsUpdPbar.getPbarId();
                redisTemplate.delete(key);

                if(jmsUpdPbar.getUserId() != null){
                    this.userMapper.updPostNum(jmsUpdPbar.getUserId());
                }

            }
        }catch (JMSException e){
            logger.error("forJMS : update pbar somthing error ! "+e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : update pbar somthing error ! ");
            e.printStackTrace();
        }

    }
}
