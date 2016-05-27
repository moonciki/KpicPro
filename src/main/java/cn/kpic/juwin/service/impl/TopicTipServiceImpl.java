package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.TopicTip;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.domain.vo.JmsTip;
import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.jms.sender.TipQueueMessageSender;
import cn.kpic.juwin.jms.sender.UserIntegrityUpdQueueMessageSender;
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

    @Autowired
    private TipQueueMessageSender tipQueueMessageSender;

    @Autowired
    private UserIntegrityUpdQueueMessageSender userIntegrityUpdQueueMessageSender;

    @Autowired
    private SystemMsgQueueMessageSender systemMsgQueueMessageSender;

    @Transactional
    @Override
    public void save(TopicTip topicTip) {
        this.topicTipMapper.save(topicTip);
    }

    @Override
    @Transactional
    public void delTopic(Long id) {
        this.topicPostMapper.del(id);
        TopicPost topicPost = this.topicPostMapper.getById(id);
        JmsUserIntegrityUpd jmsUserIntegrityUpd = new JmsUserIntegrityUpd(topicPost.getUserId(), 3, 2);
        this.userIntegrityUpdQueueMessageSender.send(jmsUserIntegrityUpd);
        JmsTip jmsTip = new JmsTip(id, 1);
        this.tipQueueMessageSender.send(jmsTip);

        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
        jmsSystemMsg.setTitle("�������������Υ�汻����Գɾ������Ľڲ�ֵ-3");
        jmsSystemMsg.setContent("�㷢���ı���Ϊ��<span style=\"color:#FF79BC\">"+topicPost.getTitle()+"</span>�����ӣ���������Υ�棬������Գɾ��");
        jmsSystemMsg.setUserId(topicPost.getUserId());
        this.systemMsgQueueMessageSender.send(jmsSystemMsg);
    }
}
