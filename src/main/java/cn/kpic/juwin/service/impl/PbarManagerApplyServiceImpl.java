package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.UserPbar;
import cn.kpic.juwin.mapper.PbarManagerApplyMapper;
import cn.kpic.juwin.mapper.UserPbarMapper;
import cn.kpic.juwin.service.PbarManagerApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/4/19.
 */
@Service
public class PbarManagerApplyServiceImpl implements PbarManagerApplyService {

    @Autowired
    private UserPbarMapper userPbarMapper;

    @Autowired
    private PbarManagerApplyMapper pbarManagerApplyMapper;

    @Override
    @Transactional
    public void tg(Long userId, Long pbarId, Long id) {
        UserPbar userPbar = new UserPbar();
        userPbar.setCreateTime(new Date());
        userPbar.setPbarId(pbarId);
        userPbar.setType(2);
        userPbar.setUserId(userId);
        this.userPbarMapper.save(userPbar);
        this.pbarManagerApplyMapper.pass(id);
    }

    @Override
    @Transactional
    public void btg(Long id) {
        this.pbarManagerApplyMapper.delete(id);
    }
}
