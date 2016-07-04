package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.UserPower;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/2.
 */
public interface UserPowerMapper {

    void save(UserPower userPower);

    List<String> getAllPowerByUserId(Long userId);

}
