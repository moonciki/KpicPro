package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Msg;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
public interface MsgService {

    void addMsg(List<Msg> msg);

    Integer notRead(Long userId);

    void update(Msg msg);

    void clear(Msg msg);
}
