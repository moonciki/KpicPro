package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.HomeRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public interface HomeRequestMapper {

    void save(HomeRequest homeRequest);

    void update(HomeRequest homeRequest);

    List<HomeRequest> getList(Map params);

}
