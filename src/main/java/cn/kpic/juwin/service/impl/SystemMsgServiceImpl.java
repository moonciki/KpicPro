package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.SystemMsg;
import cn.kpic.juwin.mapper.SystemMsgMapper;
import cn.kpic.juwin.service.SystemMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/27.
 */
@Service
public class SystemMsgServiceImpl implements SystemMsgService {

    @Autowired
    private SystemMsgMapper systemMsgMapper;

    @Override
    @Transactional
    public void saveSystemMsg(SystemMsg systemMsg) {
        this.systemMsgMapper.save(systemMsg);
    }

    @Override
    @Transactional
    public void updateSystemMsg(SystemMsg systemMsg) {
        this.systemMsgMapper.update(systemMsg);
    }

    @Override
    public List<SystemMsg> getAllSelfSystemMsg(Long userId, Integer page) {

        if(userId == null || page == null){
            return null;
        }
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("page", page);

        List<SystemMsg> result = this.systemMsgMapper.getAllSelfSystemMsg(params);

        return result.size() == 0 ? null : result;
    }
}
