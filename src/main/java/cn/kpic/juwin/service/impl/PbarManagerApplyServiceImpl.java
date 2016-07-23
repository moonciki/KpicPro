package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.UserPbar;
import cn.kpic.juwin.mapper.PbarManagerApplyMapper;
import cn.kpic.juwin.mapper.UserPbarMapper;
import cn.kpic.juwin.service.PbarManagerApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/4/19.
 */
@Service
public class PbarManagerApplyServiceImpl implements PbarManagerApplyService {

    @Autowired
    private UserPbarMapper userPbarMapper;

    @Autowired
    private PbarManagerApplyMapper pbarManagerApplyMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void tg(Long userId, Long pbarId, Long id) {
        UserPbar userPbar = new UserPbar();
        userPbar.setCreateTime(new Date());
        userPbar.setPbarId(pbarId);
        userPbar.setType(2);
        userPbar.setUserId(userId);
        String key = RedisCacheKey.PBAR_USER_ROLE + "_p" + pbarId + "_u" + userId;
        this.redisTemplate.delete(key);//清理缓存
        this.userPbarMapper.save(userPbar);
        this.pbarManagerApplyMapper.pass(id);
    }

    @Override
    @Transactional
    public void btg(Long id) {
        this.pbarManagerApplyMapper.delete(id);
    }
}
