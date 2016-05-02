package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.*;
import cn.kpic.juwin.jms.sender.PbarUpdQueueMessageSender;
import cn.kpic.juwin.jms.sender.TopicPostImgQueueMessageSender;
import cn.kpic.juwin.jms.sender.UpgradeQueueMessageSender;
import cn.kpic.juwin.mapper.TopicImgMapper;
import cn.kpic.juwin.mapper.TopicPostMapper;
import cn.kpic.juwin.mapper.TopicTipMapper;
import cn.kpic.juwin.service.TopicPostService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
public class TopicPostServiceImpl implements TopicPostService {

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Autowired
    private TopicImgMapper topicImgMapper;

    @Autowired
    private TopicTipMapper topicTipMapper;

    @Autowired
    private TopicPostImgQueueMessageSender topicPostImgQueueMessageSender;

    @Autowired
    private UpgradeQueueMessageSender upgradeQueueMessageSender;

    @Autowired
    private PbarUpdQueueMessageSender pbarUpdQueueMessageSender;

    @Override
    public List<TopicPost> getTopicByPbarId(Long pbarId) {
        return topicPostMapper.getByPbarId(pbarId);
    }

    @Override
    public List<PbarHomeTopicPost> getAllTopicByPbarId(Long pbarId, int page) {

        Map<String, Object> params = new HashMap<>();
        params.put("pbarId", pbarId);
        params.put("page", page);
        List<PbarHomeTopicPost> list = topicPostMapper.getTopicPostByPbarId(params);

        if(list != null && list.size() != 0){

            for(PbarHomeTopicPost pbarHomeTopicPost : list){
                /** 通过下面这个函数查找到前4张图片*/
                List<TopicImg> listImg = topicImgMapper.getByTopicId(pbarHomeTopicPost.getId());
                pbarHomeTopicPost.setImg(listImg.size() == 0 ? null : listImg);
            }

        }

        return list.size() == 0 ? null : list;
    }

    @Override
    @Transactional
    public TopicPost addTopicPost(String title, String content, String shortContent, Long pbarId, Long userId) {
        User user = CurrentUser.getUser();
        if(user != null){
            Date date = new Date();
            TopicPost topicPost = new TopicPost();
            topicPost.setTitle(title);
            topicPost.setContent(content);
            topicPost.setCreateTime(date);
            topicPost.setPbarId(pbarId);
            topicPost.setShortText(shortContent);
            topicPost.setUpdateTime(date);
            topicPost.setUserId(userId);
            topicPost.setIsBlog(0);
            topicPost.setIsBoutique(0);
            topicPostMapper.save(topicPost);
            /** 持久化图片信息交由队列处理*/
            JmsTopicImg jmsTopicImg = new JmsTopicImg(content, topicPost.getId());
            topicPostImgQueueMessageSender.send(jmsTopicImg);
            /** 升级相关消息*/
            upgradeQueueMessageSender.send(new JmsUpgrade(user.getId(), 5));//经验+5
            /** 修改主题的主题帖数量*/
            JmsUpdPbar jmsUpdPbar = new JmsUpdPbar(0, pbarId);
            pbarUpdQueueMessageSender.send(jmsUpdPbar);
            return topicPost;
        }else{
            return null;
        }
    }

    @Override
    public List<PbarHomeTopicPost> getAllTopicOrBlogByPbarId(Long pbarId, int page, int isBlog) {
        Map<String, Object> params = new HashMap<>();
        params.put("pbarId", pbarId);
        params.put("page", page);
        params.put("isBlog", isBlog);
        List<PbarHomeTopicPost> list = topicPostMapper.getTopicBlogOrPostByPbarId(params);

        if(list != null && list.size() != 0){

            for(PbarHomeTopicPost pbarHomeTopicPost : list){
                /** 通过下面这个函数查找到前4张图片*/
                List<TopicImg> listImg = topicImgMapper.getByTopicId(pbarHomeTopicPost.getId());
                pbarHomeTopicPost.setImg(listImg.size() == 0 ? null : listImg);
            }

        }

        return list.size() == 0 ? null : list;
    }

    @Override
    public List<PbarHomeTopicPost> getAllJpTopicByPbarId(Long pbarId, int page, int isBoutique) {
        Map<String, Object> params = new HashMap<>();
        params.put("pbarId", pbarId);
        params.put("page", page);
        params.put("isBoutique", isBoutique);
        List<PbarHomeTopicPost> list = topicPostMapper.getJpTopicByPbarId(params);

        if(list != null && list.size() != 0){

            for(PbarHomeTopicPost pbarHomeTopicPost : list){
                /** 通过下面这个函数查找到前4张图片*/
                List<TopicImg> listImg = topicImgMapper.getByTopicId(pbarHomeTopicPost.getId());
                pbarHomeTopicPost.setImg(listImg.size() == 0 ? null : listImg);
            }

        }

        return list.size() == 0 ? null : list;
    }

    @Override
    public TopicPostMsg getByUid(Long uuId) {
        TopicPostMsg topicPostMsg = topicPostMapper.getByUid(uuId);
        User user = CurrentUser.getUser();
        if(user != null){
            if(topicPostMsg != null){
                Map<String, Object> params = new HashMap<>();
                params.put("userId", user.getId());
                params.put("topicId", topicPostMsg.getId());
                Long id = this.topicTipMapper.isTip(params);
                if(id != null && id > 0){
                    topicPostMsg.setIsTip(true);
                }
            }
        }

        return topicPostMsg;
    }

    @Override
    public List<PbarHomeTopicPost> getAllTopicPostByUid(Long userId, int page, int isBlog, String orderBy) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("page", page);
        params.put("isBlog", isBlog);
        params.put("orderBy", orderBy);
        List<PbarHomeTopicPost> list = topicPostMapper.getAllTopicPostByUid(params);

        if(list != null && list.size() != 0){

            for(PbarHomeTopicPost pbarHomeTopicPost : list){
                /** 通过下面这个函数查找到前4张图片*/
                List<TopicImg> listImg = topicImgMapper.getByTopicId(pbarHomeTopicPost.getId());
                pbarHomeTopicPost.setImg(listImg.size() == 0 ? null : listImg);
            }

        }

        return list.size() == 0 ? null : list;
    }

    @Override
    @Transactional
    public void del(Long id) {
        this.topicPostMapper.del(id);
        this.topicTipMapper.delAllTips(id);
    }
}