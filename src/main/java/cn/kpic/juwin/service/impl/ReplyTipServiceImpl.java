package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.ReplyPost;
import cn.kpic.juwin.domain.ReplyTip;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.domain.vo.JmsTip;
import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.jms.sender.TipQueueMessageSender;
import cn.kpic.juwin.jms.sender.UserIntegrityUpdQueueMessageSender;
import cn.kpic.juwin.mapper.ReplyPostMapper;
import cn.kpic.juwin.mapper.ReplyTipMapper;
import cn.kpic.juwin.service.ReplyTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
@Service
public class ReplyTipServiceImpl implements ReplyTipService {

    @Autowired
    private ReplyTipMapper replyTipMapper;

    @Autowired
    private ReplyPostMapper replyPostMapper;

    @Autowired
    private UserIntegrityUpdQueueMessageSender userIntegrityUpdQueueMessageSender;

    @Autowired
    private TipQueueMessageSender tipQueueMessageSender;

    @Autowired
    private SystemMsgQueueMessageSender systemMsgQueueMessageSender;

    @Transactional
    @Override
    public void save(ReplyTip replyTip) {
        this.replyTipMapper.save(replyTip);
    }

    @Override
    @Transactional
    public void delReply(Long id) {
        this.replyPostMapper.del(id);
        ReplyPost replyPost = this.replyPostMapper.getById(id);
        JmsUserIntegrityUpd jmsUserIntegrityUpd = new JmsUserIntegrityUpd(replyPost.getUserId(), 2, 2);
        this.userIntegrityUpdQueueMessageSender.send(jmsUserIntegrityUpd);
        JmsTip jmsTip = new JmsTip(id, 2);
        this.tipQueueMessageSender.send(jmsTip);

        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
        jmsSystemMsg.setTitle("你的回复帖由于违规被管理猿删除，你的节操值-2");
        jmsSystemMsg.setContent("你发布的内容为：<span style=\"color:#FF79BC\">"+replyPost.getShortText()+"</span>的回复帖子，可能涉嫌违规，被管理猿删除");
        jmsSystemMsg.setUserId(replyPost.getUserId());
        this.systemMsgQueueMessageSender.send(jmsSystemMsg);
    }

}
