package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.HomeRequest;
import cn.kpic.juwin.mapper.HomeRequestMapper;
import cn.kpic.juwin.service.HomeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
@Service
public class HomeRequestServiceImpl implements HomeRequestService{

    @Autowired
    private HomeRequestMapper homeRequestMapper;

    @Override
    @Transactional
    public void save(HomeRequest homeRequest) {
        this.homeRequestMapper.save(homeRequest);
    }

    @Override
    @Transactional
    public void update(HomeRequest homeRequest) {
        this.homeRequestMapper.update(homeRequest);
    }

    @Override
    public List<HomeRequest> getListByUserId(Long userId, Integer page) {
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("page", page);
        List<HomeRequest> result = this.homeRequestMapper.getList(params);
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<HomeRequest> getListByStatus(Integer status, Integer page) {
        Map params = new HashMap();
        params.put("status", status);
        params.put("page", page);
        List<HomeRequest> result = this.homeRequestMapper.getList(params);
        return result.size() == 0 ? null : result;
    }
}
