package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.TopicTip;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
public interface TopicTipService {

    void save(TopicTip topicTip);

    void delTopic(Long id);

}
