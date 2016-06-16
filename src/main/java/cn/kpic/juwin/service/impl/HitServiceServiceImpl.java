package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Hit;
import cn.kpic.juwin.mapper.HitMapper;
import cn.kpic.juwin.mapper.PbarMapper;
import cn.kpic.juwin.service.HitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/6/2.
 */
@Component
public class HitServiceServiceImpl implements HitService {

    @Autowired
    private PbarMapper pbarMapper;

    @Autowired
    private HitMapper hitMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /** 更新每个圈子昨日的点击量，每天的凌晨1点触发该任务*/
    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void updHitTask() {
        SimpleDateFormat myTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        /** 获取昨天日期*/
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String nowStr = myTimeFormat.format(calendar.getTime());

        List<Long> ids = this.pbarMapper.getAllIds();//取出所有圈子id
        for(Long pbarId : ids){
            String key = RedisCacheKey.PBAR_HIT+pbarId+"_"+nowStr;
            String[] dates = nowStr.split("-");
            Hit hit = new Hit();
            hit.setPbarId(pbarId);
            hit.setYear1(Integer.parseInt(dates[0].trim()));
            hit.setMonth1(Integer.parseInt(dates[1].trim()));
            hit.setDay1(Integer.parseInt(dates[2].trim()));
            if(redisTemplate.hasKey(key)){
                hit.setValue1((Integer)redisTemplate.boundValueOps(key).get());
            }else{
                hit.setValue1(0);
            }
            hitMapper.save(hit);
        }
    }
}
