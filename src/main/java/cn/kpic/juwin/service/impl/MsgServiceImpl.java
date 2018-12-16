package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.mapper.MsgMapper;
import cn.kpic.juwin.service.MsgService;
import cn.kpic.juwin.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
public class MsgServiceImpl implements MsgService{

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void addMsg(List<Msg> msg) {
        this.msgMapper.addMsg(msg);
    }

    @Override
    public Integer notRead(Long userId) {
        Msg msg = new Msg();
        msg.setUserId(userId);
        return this.msgMapper.notRead(msg);
    }

    @Override
    @Transactional
    public void update(Msg msg) {
        User user = CurrentUser.getUser();
        if(user != null){
            String key = RedisCacheKey.USER_NEWS+user.getId();
            this.redisTemplate.delete(key);//������
            this.msgMapper.update(msg);
        }

    }

    @Override
    public void clear(Msg msg) {
        User user = CurrentUser.getUser();
        if(user != null){
            String key = RedisCacheKey.USER_NEWS+user.getId();
            this.redisTemplate.delete(key);
            this.msgMapper.clear(msg);
        }
    }
}
