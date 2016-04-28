package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.SystemMsg;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/27.
 */
public interface SystemMsgService {

    void saveSystemMsg(SystemMsg systemMsg);

    void updateSystemMsg(SystemMsg systemMsg);

    List<SystemMsg> getAllSelfSystemMsg(Long userId, Integer page);

}
