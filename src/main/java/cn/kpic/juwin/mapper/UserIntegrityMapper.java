package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.UserIntegrity;

import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
public interface UserIntegrityMapper {

    void save(UserIntegrity userIntegrity);

    Integer getByUserId(Long userId);

    void reduceNum(Map params);

    void addNum(Map params);

}
