package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.TopicTip;
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

    @Transactional
    @Override
    public void save(TopicTip topicTip) {
        this.topicTipMapper.save(topicTip);
    }

}
