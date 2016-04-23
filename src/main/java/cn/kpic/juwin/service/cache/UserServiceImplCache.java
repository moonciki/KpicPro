package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Created by bjsunqinwen on 2016/4/16.
 */
@Service
public class UserServiceImplCache extends UserServiceImpl{

    private Logger logger = Logger.getLogger(UserServiceImplCache.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getRole(Long userId,Long pbarId){
        String key = RedisCacheKey.PBAR_USER_ROLE + "_p" + pbarId + "_u" + userId;
        if(!redisTemplate.hasKey(key)){
            redisTemplate.boundValueOps(key).set(super.getRole(userId, pbarId));
        }
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
        return (String)redisTemplate.boundValueOps(key).get();
    }

}
