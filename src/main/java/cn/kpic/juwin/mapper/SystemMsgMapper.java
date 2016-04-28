package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.SystemMsg;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/27.
 */
public interface SystemMsgMapper {

    void save(SystemMsg systemMsg);

    void update(SystemMsg systemMsg);

    List<SystemMsg> getAllSelfSystemMsg(Map params);

}
