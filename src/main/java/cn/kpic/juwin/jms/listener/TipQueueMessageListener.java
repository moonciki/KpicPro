package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.vo.JmsTip;
import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import cn.kpic.juwin.mapper.ReplyTipMapper;
import cn.kpic.juwin.mapper.ShortTipMapper;
import cn.kpic.juwin.mapper.TopicTipMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TipQueueMessageListener implements MessageListener {

    private Logger logger = Logger.getLogger(TipQueueMessageListener.class);

    @Autowired
    private TopicTipMapper topicTipMapper;

    @Autowired
    private ReplyTipMapper replyTipMapper;

    @Autowired
    private ShortTipMapper shortTipMapper;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            JmsTip jmsTip = (JmsTip)objectMessage.getObject();
            if(jmsTip != null){
                if(jmsTip.getType() == 1){//清除主题帖举报
                    this.topicTipMapper.delAllTips(jmsTip.getId());
                }else if(jmsTip.getType() == 2){//清除回复帖举报
                    this.replyTipMapper.delAllReplyTips(jmsTip.getId());
                }else{//清除短评举报
                    this.shortTipMapper.delAllShortTips(jmsTip.getId());
                }
            }
        }catch (JMSException e){
            logger.error("forJMS : clear tips error ! "+e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : clear tips error ! ");
            e.printStackTrace();
        }
    }
}
