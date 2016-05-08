package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.PrivateLetter;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.domain.vo.PrivateLetterVo;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.mapper.PrivateLetterMapper;
import cn.kpic.juwin.service.PrivateLetterService;
import cn.kpic.juwin.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/26.
 */
@Service
public class PrivateLetterServiceImpl implements PrivateLetterService{

    @Autowired
    private PrivateLetterMapper privateLetterMapper;

    @Autowired
    private SystemMsgQueueMessageSender systemMsgQueueMessageSender;


    @Override
    @Transactional
    public void save(PrivateLetter privateLetter) {
        this.privateLetterMapper.save(privateLetter);

        User curr_user = CurrentUser.getUser();
        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
        jmsSystemMsg.setTitle("您收到来自 <a href=\"/user/u6514"+curr_user.getId()+"/index.html\" target=\"_blank\">"+curr_user.getName()+"</a> 的一封私信");
        jmsSystemMsg.setContent("<a href=\"/user/private/letter\" target=\"_blank\">点击查看详情</a>");
        jmsSystemMsg.setUserId(privateLetter.getUserId());
        this.systemMsgQueueMessageSender.send(jmsSystemMsg);

    }

    @Override
    @Transactional
    public void update(PrivateLetter privateLetter, Long userId) {
        this.privateLetterMapper.update(privateLetter);
        /** 发送系统消息提醒*/
        JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
        jmsSystemMsg.setTitle("系统消息：您的私信被回复");
        jmsSystemMsg.setContent("<a href=\"/user/private/self/letter\" target=\"_blank\">点击查看详情</a>");
        jmsSystemMsg.setUserId(userId);
        this.systemMsgQueueMessageSender.send(jmsSystemMsg);

    }

    @Override
    public List<PrivateLetterVo> getAllNotReply(Long userId, Integer page) {

        Map params = new HashMap();
        params.put("userId", userId);
        params.put("page", page * 10);

        List<PrivateLetterVo> result = this.privateLetterMapper.getAllNotReply(params);

        return result.size() == 0 ? null : result;
    }

    @Override
    public List<PrivateLetterVo> getAllSelf(Long userId, Integer page) {
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("page", page * 10);

        List<PrivateLetterVo> result = this.privateLetterMapper.getAllSelf(params);

        return result.size() == 0 ? null : result;
    }

}
