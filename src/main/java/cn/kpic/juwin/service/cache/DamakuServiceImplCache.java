package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.vo.DamakuVo;
import cn.kpic.juwin.domain.vo.Xlh;
import cn.kpic.juwin.service.impl.DamakuServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
@Service
public class DamakuServiceImplCache extends DamakuServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<DamakuVo> getAllDamakuByMvId(Integer mvId, Integer size) {
        String key = String.format(RedisCacheKey.DAMAKU_LIST, mvId);
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<DamakuVo> result = super.getAllDamakuByMvId(mvId, size);
            if(result == null){
                result = Collections.emptyList();
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);//5min过期
            return result;
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<DamakuVo> final_result = xlh.getList();
        return final_result == null ? null : final_result;

    }

}
