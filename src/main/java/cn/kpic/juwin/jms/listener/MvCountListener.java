package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.vo.MvCount;
import cn.kpic.juwin.domain.vo.MvVo;
import cn.kpic.juwin.mapper.MvMapper;
import cn.kpic.juwin.mapper.SendListMapper;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by Administrator on 2017/1/27 0027.
 */
@Service
@Transactional
public class MvCountListener implements MessageListener {

    Logger logger = Logger.getLogger(MvCountListener.class);

    @Resource
    private MvMapper mvMapper;

    @Resource
    private SendListMapper sendListMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            MvCount mvCount = (MvCount) objectMessage.getObject();
            if(mvCount != null){
                //type 1：播放量，2：收藏数，3：弹幕数
                //首先判断传来的参数是否大于10， 大于10的时候做持久化处理
                MvVo mvVo = this.mvMapper.getById(mvCount.getMvId());
                if(MvCount.FAV.equals(mvCount.getType())){
                    this.mvMapper.plusCount(MvCount.FAV, 1, mvCount.getMvId());
                    this.sendListMapper.plusCount(MvCount.FAV, 1, mvVo.getSlId());
                }else{
                    if(mvCount.getCount() >= 10){
                        this.mvMapper.plusCount(mvCount.getType(), mvCount.getCount(), mvCount.getMvId());
                        this.sendListMapper.plusCount(mvCount.getType(), mvCount.getCount(), mvVo.getSlId());

                        if(MvCount.DANMU.equals(mvCount.getType())){
                            redisTemplate.delete(String.format(RedisCacheKey.DANMU, mvCount.getMvId()));
                        }
                        if(MvCount.PLAY.equals(mvCount.getType())){
                            redisTemplate.delete(String.format(RedisCacheKey.PLAY, mvCount.getMvId()));
                        }
                    }
                }
            }
        }catch (JMSException e){
            logger.error("forJMS : save mv count error ! "+e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : save mv count error ! ");
            e.printStackTrace();
        }
    }
}
