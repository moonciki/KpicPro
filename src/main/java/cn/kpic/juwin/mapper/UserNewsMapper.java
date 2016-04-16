package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.UserNews;
import cn.kpic.juwin.domain.vo.UserNewsVo;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/5.
 */
public interface UserNewsMapper {

    void save(UserNews userNews);

    List<UserNewsVo> getAllUserNews(Map<String, Object> params);

}
