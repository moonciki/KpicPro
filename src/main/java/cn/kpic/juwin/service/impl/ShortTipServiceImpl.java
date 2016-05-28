package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.ShortReply;
import cn.kpic.juwin.domain.ShortTip;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.domain.vo.JmsTip;
import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.jms.sender.TipQueueMessageSender;
import cn.kpic.juwin.jms.sender.UserIntegrityUpdQueueMessageSender;
import cn.kpic.juwin.mapper.ShortReplyMapper;
import cn.kpic.juwin.mapper.ShortTipMapper;
import cn.kpic.juwin.service.ShortTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
@Service
public class ShortTipServiceImpl implements ShortTipService {

    @Autowired
    private ShortTipMapper shortTipMapper;

    @Autowired
    private ShortReplyMapper shortReplyMapper;

    @Autowired
    private UserIntegrityUpdQueueMessageSender userIntegrityUpdQueueMessageSender;

    @Autowired
    private TipQueueMessageSender tipQueueMessageSender;

    @Autowired
    private SystemMsgQueueMessageSender systemMsgQueueMessageSender;

    @Transactional
    @Override
    public void save(ShortTip shortTip) {
        this.shortTipMapper.save(shortTip);
    }

    @Override
    @Transactional
    public void delshort(Long id) {
        this.shortReplyMapper.del(id);
        ShortReply shortReply = this.shortReplyMapper.getObj(id);
        JmsUserIntegrityUpd jmsUserIntegrityUpd = new JmsUserIntegrityUpd(shortReply.getUserId(), 1, 2);
        this.userIntegrityUpdQueueMessageSender.send(jmsUserIntegrityUpd);
        JmsTip jmsTip = new JmsTip(id, 3);
        this.tipQueueMessageSender.send(jmsTip);

        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
        jmsSystemMsg.setTitle("你的短评由于违规被管理猿删除，你的节操值-1");
        jmsSystemMsg.setContent("你发布的内容为：<span style=\"color:#FF79BC\">"+shortReply.getContent()+"</span>的短评，可能涉嫌违规，被管理猿删除");
        jmsSystemMsg.setUserId(shortReply.getUserId());
        this.systemMsgQueueMessageSender.send(jmsSystemMsg);
    }

}
