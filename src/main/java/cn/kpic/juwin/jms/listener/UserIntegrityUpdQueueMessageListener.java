package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.mapper.UserIntegrityMapper;
import cn.kpic.juwin.mapper.UserMapper;
import cn.kpic.juwin.service.UserService;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SystemMsgQueueMessageSender systemMsgQueueMessageSender;


    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            JmsUserIntegrityUpd jmsUserIntegrityUpd = (JmsUserIntegrityUpd)objectMessage.getObject();
            if(jmsUserIntegrityUpd != null){
                Integer final_num = 0;
                if(jmsUserIntegrityUpd.getType() == 2){//减少节操值
                    Map params = new HashMap();
                    Integer num = this.userIntegrityMapper.getByUserId(jmsUserIntegrityUpd.getUserId());
                    if((num - jmsUserIntegrityUpd.getNum()) < 0){
                        params.put("num", 0);
                    }else{
                        params.put("num", jmsUserIntegrityUpd.getNum());
                        final_num = num - jmsUserIntegrityUpd.getNum();
                    }
                    params.put("userId", jmsUserIntegrityUpd.getUserId());
                    this.userIntegrityMapper.reduceNum(params);

                    if(final_num <= 38 && final_num > 28){
                        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
                        jmsSystemMsg.setTitle("警告！");
                        jmsSystemMsg.setContent("目前您的节操值已经降低至<span style=\"color:orange\"><b>38</b></span>以下，为了不影响您的正常使用，请增加自己的节操值~");
                        jmsSystemMsg.setUserId(jmsUserIntegrityUpd.getUserId());
                        this.systemMsgQueueMessageSender.send(jmsSystemMsg);
                    }
                    if(final_num <= 28 && final_num > 14){
                        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
                        jmsSystemMsg.setTitle("半禁言通知！");
                        jmsSystemMsg.setContent("目前您的节操值已经降低至<span style=\"color:red\"><b>28</b></span>以下，目前您已经丧失发表主题帖和文章，以及申请上首页的权限");
                        jmsSystemMsg.setUserId(jmsUserIntegrityUpd.getUserId());
                        this.systemMsgQueueMessageSender.send(jmsSystemMsg);
                    }
                    if(final_num <= 14){
                        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
                        jmsSystemMsg.setTitle("禁言通知！");
                        jmsSystemMsg.setContent("目前您的节操值已经降低至<span style=\"color:red\"><b>14</b></span>以下，目前您已经丧失系统几乎全部权限");
                        jmsSystemMsg.setUserId(jmsUserIntegrityUpd.getUserId());
                        this.systemMsgQueueMessageSender.send(jmsSystemMsg);
                    }
                }else{
                    Map params = new HashMap();
                    Integer num = this.userIntegrityMapper.getByUserId(jmsUserIntegrityUpd.getUserId());
                    if((num + jmsUserIntegrityUpd.getNum()) > 100){
                        num = 100 - num;
                        params.put("num", num);
                        final_num = 100;
                    }else{
                        params.put("num", jmsUserIntegrityUpd.getNum());
                        final_num = num + jmsUserIntegrityUpd.getNum();
                    }
                    params.put("userId", jmsUserIntegrityUpd.getUserId());
                    this.userIntegrityMapper.addNum(params);
                    if(final_num >= 81){
                        User user = this.userMapper.findUserById(jmsUserIntegrityUpd.getUserId());
                        if(user.getIsjm() == 0){
                            JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
                            jmsSystemMsg.setTitle("恭喜您！");
                            jmsSystemMsg.setContent("目前您的节操值达到<span style=\"color:green\"><b>81</b></span>以上，您已经成功获得系统加冕，为了保证看到效果，建议您重新登录系统~");
                            jmsSystemMsg.setUserId(jmsUserIntegrityUpd.getUserId());
                            this.systemMsgQueueMessageSender.send(jmsSystemMsg);
                            this.userMapper.jm(jmsUserIntegrityUpd.getUserId());
                        }
                    }
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
