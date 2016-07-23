package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.UserNews;
import cn.kpic.juwin.domain.vo.UserNewsVo;
import cn.kpic.juwin.mapper.UserNewsMapper;
import cn.kpic.juwin.service.UserNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/5.
 */
@Service
public class UserNewsServiceImpl implements UserNewsService {

    @Autowired
    private UserNewsMapper userNewsMapper;

    @Override
    @Transactional
    public void save(UserNews userNews) {
        this.userNewsMapper.save(userNews);
    }

    @Override
    public List<UserNewsVo> getAllUserNews(Long userId, int page) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("page", page);
        List<UserNewsVo> result = this.userNewsMapper.getAllUserNews(params);
        return result.size() == 0 ? null : result;
    }
}
