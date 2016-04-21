package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.service.impl.MsgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by bjsunqinwen on 2016/4/21.
 */
@Service
public class MsgServiceImplCache extends MsgServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer notRead(Long userId, int type) {

        String key = RedisCacheKey.USER_NEWS+userId;
        if(redisTemplate.hasKey(key)){
            return Integer.parseInt(redisTemplate.boundValueOps(key).get()+"");
        }else{
            int num = super.notRead(userId, type);
            redisTemplate.boundValueOps(key).set(num+"");
            redisTemplate.expire(key, 1, TimeUnit.MINUTES);//每一分钟缓存失效一次
            return num;
        }
    }

}
