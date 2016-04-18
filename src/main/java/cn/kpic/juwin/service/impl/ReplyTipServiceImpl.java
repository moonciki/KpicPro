package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.ReplyTip;
import cn.kpic.juwin.mapper.ReplyPostMapper;
import cn.kpic.juwin.mapper.ReplyTipMapper;
import cn.kpic.juwin.service.ReplyTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
@Service
public class ReplyTipServiceImpl implements ReplyTipService {

    @Autowired
    private ReplyTipMapper replyTipMapper;

    @Autowired
    private ReplyPostMapper replyPostMapper;

    @Transactional
    @Override
    public void save(ReplyTip replyTip) {
        this.replyTipMapper.save(replyTip);
    }

    @Override
    @Transactional
    public void delReply(Long id) {
        this.replyPostMapper.del(id);
        this.replyTipMapper.delAllReplyTips(id);
    }

}
