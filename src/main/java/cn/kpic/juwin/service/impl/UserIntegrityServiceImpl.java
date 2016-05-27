package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.UserIntegrity;
import cn.kpic.juwin.mapper.UserIntegrityMapper;
import cn.kpic.juwin.service.UserIntegrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
public class UserIntegrityServiceImpl implements UserIntegrityService {

    @Autowired
    private UserIntegrityMapper userIntegrityMapper;

    @Override
    @Transactional
    public void save(UserIntegrity userIntegrity) {
        this.userIntegrityMapper.save(userIntegrity);
    }

    @Override
    public Integer getByUserId(Long userId) {
        return this.userIntegrityMapper.getByUserId(userId);
    }

    @Override
    public void reduceNum(Long userId, Integer num) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("num", num);
        this.userIntegrityMapper.reduceNum(params);
    }

    @Override
    public void addNum(Long userId, Integer num) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("num", num);
        this.userIntegrityMapper.addNum(params);
    }
}
