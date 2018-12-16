package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.service.MvCountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/27 0027.
 */
@Service
public class MvCountServiceImpl implements MvCountService {

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void playPlus(Integer mvId, Integer count) {
        redisTemplate.boundValueOps(String.format(RedisCacheKey.PLAY, mvId)).increment(count);
    }

    @Override
    public void damakuPlus(Integer mvId, Integer count) {
        redisTemplate.boundValueOps(String.format(RedisCacheKey.DANMU, mvId)).increment(count);
    }

    @Override
    public void favPlus(Integer mvId, Integer count) {
        redisTemplate.boundValueOps(String.format(RedisCacheKey.FAV, mvId)).increment(count);
    }

    @Override
    public Integer getPlay(Integer mvId) {
        return Integer.parseInt(redisTemplate.boundValueOps(String.format(RedisCacheKey.PLAY, mvId)).get()+"");
    }

    @Override
    public Integer getDamaku(Integer mvId) {
        return Integer.parseInt(redisTemplate.boundValueOps(String.format(RedisCacheKey.DANMU, mvId)).get()+"");
    }
}
