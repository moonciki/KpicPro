package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Msg;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
public interface MsgService {

    void addMsg(Msg msg);

    Integer notRead(Long userId, int type);

    void update(Msg msg);
}
