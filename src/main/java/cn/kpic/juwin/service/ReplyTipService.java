package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.ReplyTip;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
public interface ReplyTipService {

    void save(ReplyTip replyTip);

    void delReply(Long id);

}
