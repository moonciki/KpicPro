package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Mv;
import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserMv;
import cn.kpic.juwin.domain.vo.JmsTopicImg;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import cn.kpic.juwin.domain.vo.JmsUpgrade;
import cn.kpic.juwin.domain.vo.MvVo;
import cn.kpic.juwin.jms.sender.PbarUpdQueueMessageSender;
import cn.kpic.juwin.jms.sender.UpgradeQueueMessageSender;
import cn.kpic.juwin.mapper.MvMapper;
import cn.kpic.juwin.mapper.TopicPostMapper;
import cn.kpic.juwin.mapper.UserMvMapper;
import cn.kpic.juwin.service.MvService;
import cn.kpic.juwin.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public class MvServiceImpl implements MvService {

    @Autowired
    private MvMapper mvMapper;

    @Autowired
    private UserMvMapper userMvMapper;

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Autowired
    private UpgradeQueueMessageSender upgradeQueueMessageSender;

    @Autowired
    private PbarUpdQueueMessageSender pbarUpdQueueMessageSender;

    @Override
    @Transactional
    public void save(Mv mv) {
        User user = CurrentUser.getUser();
        this.mvMapper.save(mv);
        Date date = new Date();
        TopicPost topicPost = new TopicPost();
        topicPost.setTitle("如何评价「"+mv.getTitle()+"」？你觉得这个作品怎么样呢？");
        topicPost.setContent("<p>对应播放页：<a href='/media/mv"+mv.getId()+"'>http://www.kabii.cn/media/mv"+mv.getId()+"</a><br/>请在下面写写对这首音乐的看法或者相关科普等等，评论区是用来闲聊的地方~</p>");
        topicPost.setCreateTime(date);
        topicPost.setPbarId(2L);
        topicPost.setShortText("评论区是用来闲聊的好地方~");
        topicPost.setUpdateTime(date);
        topicPost.setUserId(user.getId());
        topicPost.setIsBlog(0);
        topicPost.setIsBoutique(0);
        topicPostMapper.save(topicPost);

        mvMapper.updateTopicId(topicPost.getId(), mv.getId());

        /** 升级相关消息*/
        upgradeQueueMessageSender.send(new JmsUpgrade(user.getId(), 5));//经验+5
        /** 修改主题的主题帖数量*/
        JmsUpdPbar jmsUpdPbar = new JmsUpdPbar(0, 2L, user.getId());
        pbarUpdQueueMessageSender.send(jmsUpdPbar);
    }

    @Override
    public MvVo getById(Integer id) {
        return this.mvMapper.getById(id);
    }

    @Override
    public List<Mv> getMvsBySlId(Integer slId, Integer mvId) {
        return this.mvMapper.getMvsBySlId(slId, mvId);
    }

    @Override
    public boolean isFav(Long userId, Integer mvId) {
        Integer count = this.userMvMapper.isFav(userId, mvId);
        return count > 0 ? true : false;
    }

    @Override
    public Integer saveUserMv(Integer mvId, Long userId) {
        UserMv userMv = new UserMv();
        userMv.setMvId(mvId);
        userMv.setUserId(userId);
        userMv.setCreateTime(new Date());
        return this.userMvMapper.save(userMv);
    }

    @Override
    public List<Mv> getNewMv() {
        return this.mvMapper.getNewMv();
    }
}
