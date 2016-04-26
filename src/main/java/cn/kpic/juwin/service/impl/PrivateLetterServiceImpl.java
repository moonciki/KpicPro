package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.PrivateLetter;
import cn.kpic.juwin.domain.vo.PrivateLetterVo;
import cn.kpic.juwin.mapper.PrivateLetterMapper;
import cn.kpic.juwin.service.PrivateLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/26.
 */
@Service
public class PrivateLetterServiceImpl implements PrivateLetterService{

    @Autowired
    private PrivateLetterMapper privateLetterMapper;


    @Override
    @Transactional
    public void save(PrivateLetter privateLetter) {
        this.privateLetterMapper.save(privateLetter);
    }

    @Override
    @Transactional
    public void update(PrivateLetter privateLetter) {
        this.privateLetterMapper.update(privateLetter);
    }

    @Override
    public List<PrivateLetterVo> getAllNotReply(Long userId, Integer page) {

        Map params = new HashMap();
        params.put("userId", userId);
        params.put("page", page * 10);

        List<PrivateLetterVo> result = this.privateLetterMapper.getAllNotReply(params);

        return result.size() == 0 ? null : result;
    }

    @Override
    public List<PrivateLetterVo> getAllSelf(Long userId, Integer page) {
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("page", page * 10);

        List<PrivateLetterVo> result = this.privateLetterMapper.getAllSelf(params);

        return result.size() == 0 ? null : result;
    }

}
