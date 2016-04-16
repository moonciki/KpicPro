package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.UserLevel;

/**
 * Created by Administrator on 2016/3/27 0027.
 */
public interface UserLevelMapper {

    void save(UserLevel userLevel);

    UserLevel getByUserId(Long userId);

    void update(UserLevel userLevel);
}
