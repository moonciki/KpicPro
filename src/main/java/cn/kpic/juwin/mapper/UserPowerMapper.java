package cn.kpic.juwin.mapper;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/2.
 */
public interface UserPowerMapper {

    List<String> getAllPowerByUserId(Long userId);

}
