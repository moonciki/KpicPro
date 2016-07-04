package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Msg;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
public interface MsgMapper {

    void addMsg(List<Msg> msg);

    Integer notRead(Msg msg);

    void update(Msg msg);

    void clear(Msg msg);

    Integer notReadByType(Msg msg);

}
