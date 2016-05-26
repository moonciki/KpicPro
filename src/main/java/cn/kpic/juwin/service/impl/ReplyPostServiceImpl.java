package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.ReplyPost;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserNews;
import cn.kpic.juwin.domain.vo.JmsReplyImg;
import cn.kpic.juwin.domain.vo.JmsUpgrade;
import cn.kpic.juwin.domain.vo.ReplyPostList;
import cn.kpic.juwin.domain.vo.SelfReply;
import cn.kpic.juwin.jms.sender.ReplyPostImgQueueMessageSender;
import cn.kpic.juwin.jms.sender.TopicUpdQueueMessageSender;
import cn.kpic.juwin.jms.sender.UpgradeQueueMessageSender;
import cn.kpic.juwin.jms.sender.UserNewsQueueMessageSender;
import cn.kpic.juwin.mapper.ReplyPostMapper;
import cn.kpic.juwin.mapper.ReplyTipMapper;
import cn.kpic.juwin.service.ReplyPostService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.*;

/**
 * Created by bjsunqinwen on 2016/3/18.
 */
@Service
public class ReplyPostServiceImpl implements ReplyPostService {

    private static Logger logger = Logger.getLogger(ReplyPostServiceImpl.class);

    @Autowired
    private ReplyPostMapper replyPostMapper;

    @Autowired
    private ReplyTipMapper replyTipMapper;

    @Autowired
    private ReplyPostImgQueueMessageSender replyPostImgQueueMessageSender;

    @Autowired
    private TopicUpdQueueMessageSender topicUpdQueueMessageSender;

    @Autowired
    private UpgradeQueueMessageSender upgradeQueueMessageSender;

    @Autowired
    private UserNewsQueueMessageSender userNewsQueueMessageSender;

    @Override
    public List<ReplyPostList> getAllReplyByTopicId(Long topicId, int page) {
        List<ReplyPostList> lists = new ArrayList<>();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("topicId", topicId);
            params.put("page", page);
            lists = replyPostMapper.getAllReplyByTopicId(params);
            User user = CurrentUser.getUser();
            if(user != null){
                if(lists != null && lists.size() != 0){
                    List<Long> storeIds = new ArrayList<>();

                    for(ReplyPostList replyPostList : lists){
                        storeIds.add(replyPostList.getId());
                    }

                    Map<String, Object> params2 = new HashMap<>();
                    params2.put("storeIds",storeIds);
                    params2.put("userId", user.getId());
                    List<Long> store_result = this.replyTipMapper.getIdsByStoreIds(params2);
                    if(store_result.size() != 0){
                        for(ReplyPostList replyPostList : lists){
                            if(store_result.contains(replyPostList.getId())){
                                replyPostList.setIsTip(true);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("get replyList error !  topicId = " + topicId + ", page = " + page);
            e.printStackTrace();
        }
        return lists.size() == 0 ? null : lists;
    }

    @Override
    @Transactional
    public ReplyPost saveReplyPost(ReplyPost replyPost, Long pbarId, Long topicUserId) {
        User user = CurrentUser.getUser();
        if(user != null){
            replyPost.setCreateTime(new Date());
            this.replyPostMapper.insert(replyPost);

            /** 持久化回复贴图片*/
            JmsReplyImg jmsReplyImg = new JmsReplyImg(replyPost.getContent(), replyPost.getTopicId(), replyPost.getId());
            replyPostImgQueueMessageSender.send(jmsReplyImg);

            /** 更新主题帖字段*/
            topicUpdQueueMessageSender.send(replyPost.getTopicId());

            /** 升级相关消息*/
            upgradeQueueMessageSender.send(new JmsUpgrade(user.getId(), 3));//经验+3

            if(!String.valueOf(topicUserId).equals(String.valueOf(replyPost.getUserId()))){//说明不是自己回复自己，往消息盒子塞信息
                /** 消息盒子相关消息*/
                UserNews userNews = new UserNews();
                userNews.setUserId(topicUserId);
                userNews.setTitle("【主题帖的回复】");
                userNews.setType(1);
                userNews.setCreateTime(new Date());
                userNews.setFromUserId(replyPost.getUserId());
                userNews.setPbarId(pbarId);
                userNews.setTopicId(replyPost.getTopicId());
                userNews.setReplyId(replyPost.getId());
                userNews.setShortContent(replyPost.getShortText());
                userNewsQueueMessageSender.send(userNews);
            }

            return replyPost;

        }else{
            return null;
        }

    }

    @Override
    public List<SelfReply> getAllSelfReply(Long userId, int page, String orderBy) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("page", page);
        params.put("orderBy", orderBy);

        List<SelfReply> result = replyPostMapper.getAllSelfReply(params);

        return result.size() == 0 ? null : result;
    }

    @Override
    public ReplyPost getShortTextById(Long replyId) {
        ReplyPost replyPost = this.replyPostMapper.getById(replyId);
        return replyPost == null ? null : replyPost;

    }
}
