package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.TopicTip;
import cn.kpic.juwin.mapper.TopicPostMapper;
import cn.kpic.juwin.mapper.TopicTipMapper;
import cn.kpic.juwin.service.TopicTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
@Service
public class TopicTipServiceImpl implements TopicTipService {

    @Autowired
    private TopicTipMapper topicTipMapper;

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Transactional
    @Override
    public void save(TopicTip topicTip) {
        this.topicTipMapper.save(topicTip);
    }

    @Override
    @Transactional
    public void delTopic(Long id) {
        this.topicPostMapper.del(id);
        this.topicTipMapper.delAllTips(id);
    }
}
