package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.service.impl.UserIntegrityServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
@Service
public class UserIntegrityServiceImplCache extends UserIntegrityServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = Logger.getLogger(UserIntegrityServiceImplCache.class);

    @Override
    public Integer getByUserId(Long userId) {
        String key = RedisCacheKey.USER_JC_NUM+"_"+userId;
        if(redisTemplate.hasKey(key)){
            return Integer.parseInt(redisTemplate.boundValueOps(key).get()+"");
        }else{
            Integer num = super.getByUserId(userId);
            if(num != null){
                redisTemplate.boundValueOps(key).set(num+"");
                redisTemplate.expire(key, 1, TimeUnit.DAYS);
            }else{
                return null;
            }
            return num;
        }
    }

}
