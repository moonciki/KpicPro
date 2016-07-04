package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.HomeRequest;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public interface HomeRequestService {

    void save(HomeRequest homeRequest);

    void update(HomeRequest homeRequest);

    List<HomeRequest> getListByUserId(Long userId, Integer page);

    List<HomeRequest> getListByStatus(Integer status, Integer page);

}
