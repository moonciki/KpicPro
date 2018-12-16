package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Mv;
import cn.kpic.juwin.domain.vo.MvVo;
import cn.kpic.juwin.service.impl.MvServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
@Service
public class MvServiceImplCache extends MvServiceImpl{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public MvVo getById(Integer id) {

        String key = String.format(RedisCacheKey.MVBYID, id);
        String json;
        if(!this.redisTemplate.hasKey(key)){
            MvVo result = super.getById(id);
            if(result == null){
                return new MvVo();
            }
            String str = JSON.toJSONString(result);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);//5min过期
            return result;
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        MvVo mv = JSON.parseObject(json, MvVo.class);
        return mv == null ? null : mv;
    }

    @Override
    public List<Mv> getMvsBySlId(Integer slId, Integer mvId) {
        String key = String.format(RedisCacheKey.MVSBYSLID, slId, mvId);
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<Mv> result = super.getMvsBySlId(slId, mvId);
            if(result.size() == 0){
                return new ArrayList();
            }
            String str = JSON.toJSONString(result);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);//5min过期
            return result;
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        List<Mv> mv = JSON.parseObject(json, ArrayList.class);
        return mv == null ? null : mv;
    }

    @Override
    public boolean isFav(Long userId, Integer mvId) {
        String key = String.format(RedisCacheKey.ISFAVMV, userId, mvId);
        String json;
        if(!this.redisTemplate.hasKey(key)){
            Boolean isfav = super.isFav(userId, mvId);
            String str = JSON.toJSONString(isfav);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);//5min过期
            return isfav;
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Boolean isFav = JSON.parseObject(json, Boolean.class);
        return isFav;
    }

    @Override
    public List<Mv> getNewMv(){
        String key = RedisCacheKey.SEND_MUSIC_INDEX;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<Mv> result = super.getNewMv();
            if(result.size() == 0){
                return new ArrayList();
            }
            String str = JSON.toJSONString(result);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);//5min过期
            return result;
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        List<Mv> mv = JSON.parseObject(json, ArrayList.class);
        return mv == null ? null : mv;
    }

}
