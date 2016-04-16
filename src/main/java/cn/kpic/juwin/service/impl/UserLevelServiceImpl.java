package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.UserLevel;
import cn.kpic.juwin.mapper.UserLevelMapper;
import cn.kpic.juwin.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/3/27 0027.
 */
@Service
public class UserLevelServiceImpl implements UserLevelService {

    @Autowired
    private UserLevelMapper userLevelMapper;

    @Override
    public UserLevel getUserLevelByUserId(Long userId) {
        return userLevelMapper.getByUserId(userId);
    }

    @Transactional
    public void addUserLevel(Long userId){
        UserLevel userLevel = new UserLevel();
        userLevel.setUserId(userId);
        userLevelMapper.save(userLevel);
    }

}
