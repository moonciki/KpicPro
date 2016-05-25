package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserFocus;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/27 0027.
 */
public interface UserFocusMapper {

    void save(UserFocus userFocus);

    void del(Long id);

    void clear();

    int getFansNum(Long userId);

    List<User> getAllFans(Map<String, Object> params);

    List<User> getAllFocus(Map<String, Object> params);

    UserFocus isgz(UserFocus userFocus);

    void delUserFocus(Map params);

}
