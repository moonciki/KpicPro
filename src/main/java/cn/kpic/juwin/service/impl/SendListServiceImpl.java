package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.SendList;
import cn.kpic.juwin.domain.vo.Xlh;
import cn.kpic.juwin.mapper.SendListMapper;
import cn.kpic.juwin.service.SendListService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/29 0029.
 */
@Service
public class SendListServiceImpl implements SendListService {

    @Autowired
    private SendListMapper sendListMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public Integer save(SendList sendList) {
        return this.sendListMapper.save(sendList);
    }

    @Override
    public List<SendList> getNewest() {

        String key = RedisCacheKey.SEND_LIST_INDEX;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<SendList> result = this.sendListMapper.getNewest();
            if(result == null){
                result = Collections.emptyList();
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);//5min过期
            return result;
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<SendList> final_result = xlh.getList();
        return final_result == null ? null : final_result;
    }
}
