package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.UserIntegrity;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
public interface UserIntegrityService {

    void save(UserIntegrity userIntegrity);

    Integer getByUserId(Long userId);

    void reduceNum(Long userId, Integer num);

    void addNum(Long userId, Integer num);

}
