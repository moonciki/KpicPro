package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.vo.PbarIndexVo;
import cn.kpic.juwin.service.impl.PbarServiceImpl;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjsunqinwen on 2016/4/8.
 */
@Service
public class PbarServiceImplCache extends PbarServiceImpl{

    private static Logger logger = Logger.getLogger(PbarServiceImplCache.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PbarIndexVo getPbarIndex(Long id) {
        String key = RedisCacheKey.PBAR_INDEX + id;

        /** 缓存不存在*/
        if(!redisTemplate.hasKey(key)){
            PbarIndexVo pbarIndexVo = super.getPbarIndex(id);
            if(pbarIndexVo == null){
                return null;
            }
            String json = JSON.toJSONString(pbarIndexVo);
            redisTemplate.boundValueOps(key).set(json);
            redisTemplate.expire(key, 2, TimeUnit.DAYS);//缓存每两天过期一次
        }

        return JSON.parseObject((String)redisTemplate.boundValueOps(key).get(), PbarIndexVo.class);
    }

    @Override
    public void clearCache(String key) {
        if(this.redisTemplate.hasKey(key)){
            this.redisTemplate.delete(key);
        }
    }

    /** 累加点击量*/
    @Override
    public void updPbarHit(Long pbarId){
        SimpleDateFormat myTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr = myTimeFormat.format(new Date());
        String key = RedisCacheKey.PBAR_HIT+pbarId+"_"+nowStr;
        if(!redisTemplate.hasKey(key)){
            /** key不存在的时候创建该key，初始值为1*/
            redisTemplate.boundValueOps(key).set(1);
            redisTemplate.expire(key, 2, TimeUnit.DAYS);
        }else{
            /** key存在，直接取出累加1即可*/
            Integer num = (Integer)redisTemplate.boundValueOps(key).get();
            redisTemplate.boundValueOps(key).set(num + 1);
        }
    }
}
