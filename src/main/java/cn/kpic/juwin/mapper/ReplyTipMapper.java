package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.ReplyTip;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
public interface ReplyTipMapper {

    void save(ReplyTip replyTip);

    List<Long> getIdsByStoreIds(Map<String, Object> params);

}
