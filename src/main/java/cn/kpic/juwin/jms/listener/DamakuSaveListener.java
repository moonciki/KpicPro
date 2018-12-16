package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Damaku;
import cn.kpic.juwin.domain.vo.DamakuVo;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import cn.kpic.juwin.mapper.DamakuMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
@Service
@Transactional
public class DamakuSaveListener implements MessageListener {

    Logger logger = Logger.getLogger(DamakuSaveListener.class);

    @Autowired
    private DamakuMapper damakuMapper;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            Damaku damaku = (Damaku) objectMessage.getObject();
            if(damaku != null){
                this.damakuMapper.save(damaku);
            }
        }catch (JMSException e){
            logger.error("forJMS : save damaku error ! "+e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : save damaku error ! ");
            e.printStackTrace();
        }
    }
}
