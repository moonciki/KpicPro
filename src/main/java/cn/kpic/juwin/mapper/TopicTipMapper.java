package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.TopicTip;
import cn.kpic.juwin.domain.vo.TopicTips;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
public interface TopicTipMapper {

    void save(TopicTip topicTip);

    Long isTip(Map<String, Object> params);

    List<TopicTips> getAllTopicTips(Map params);

    void delAllTips(Long id);

}
