package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.TopicTip;

import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
public interface TopicTipMapper {

    void save(TopicTip topicTip);

    Long isTip(Map<String, Object> params);

}
