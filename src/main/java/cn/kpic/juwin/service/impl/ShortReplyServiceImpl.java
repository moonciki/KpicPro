package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.ShortReply;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserNews;
import cn.kpic.juwin.domain.vo.ReplyPostList;
import cn.kpic.juwin.domain.vo.ShortReplyVo;
import cn.kpic.juwin.domain.vo.ShortReplyVo2;
import cn.kpic.juwin.domain.vo.TopicOrReplyInfoVo;
import cn.kpic.juwin.jms.sender.ReplyUpdQueueMessageSender;
import cn.kpic.juwin.jms.sender.UserNewsQueueMessageSender;
import cn.kpic.juwin.mapper.ShortReplyMapper;
import cn.kpic.juwin.mapper.ShortTipMapper;
import cn.kpic.juwin.service.ShortReplyService;
import cn.kpic.juwin.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2016/4/3 0003.
 */
@Service
public class ShortReplyServiceImpl implements ShortReplyService{

    @Autowired
    private ShortReplyMapper shortReplyMapper;

    @Autowired
    private ShortTipMapper shortTipMapper;

    @Autowired
    private UserNewsQueueMessageSender userNewsQueueMessageSender;

    @Autowired
    private ReplyUpdQueueMessageSender replyUpdQueueMessageSender;

    @Override
    @Transactional
    public void insert(ShortReply shortReply, Long pbarId) {
        User user = CurrentUser.getUser();
        if(user != null){
            this.shortReplyMapper.insert(shortReply);
            if(!String.valueOf(shortReply.getUserId()).equals(String.valueOf(shortReply.getReplyUserId()))){//说明不是自己回复自己，往消息盒子塞信息
                /** 消息盒子相关消息*/
                UserNews userNews = new UserNews();
                userNews.setUserId(shortReply.getReplyUserId());
                userNews.setTitle("【短评回复】");
                userNews.setType(2);
                userNews.setCreateTime(new Date());
                userNews.setFromUserId(shortReply.getUserId());
                userNews.setPbarId(pbarId);
                userNews.setReplyId(shortReply.getReplyId());
                userNews.setShortContent(shortReply.getContent());
                userNewsQueueMessageSender.send(userNews);
            }
            replyUpdQueueMessageSender.send(shortReply.getReplyId());
        }
    }

    @Override
    @Transactional
    public void del(Long shortId) {
        this.shortReplyMapper.del(shortId);
    }

    @Override
    @Transactional
    public void clear() {
        this.shortReplyMapper.clear();
    }

    @Override
    public List<ShortReplyVo> getShortByReplyId(Long replyId, int page) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("replyId", replyId);
        params.put("page", page * 10);
        List<ShortReplyVo> result = this.shortReplyMapper.getShortByReplyId(params);
        User user = CurrentUser.getUser();
        if(user != null){
            if(result != null && result.size() != 0){
                List<Long> storeIds = new ArrayList<Long>();

                for(ShortReplyVo shortReplyVo : result){
                    storeIds.add(shortReplyVo.getShortId());
                }

                Map<String, Object> params2 = new HashMap<String, Object>();
                params2.put("storeIds",storeIds);
                params2.put("userId", user.getId());
                List<Long> store_result = this.shortTipMapper.getIdsByStoreIds(params2);
                if(store_result.size() != 0){
                    for(ShortReplyVo shortReplyVo : result){
                        if(store_result.contains(shortReplyVo.getShortId())){
                            shortReplyVo.setIsTip(true);
                        }
                    }
                }
            }
        }

        return result.size() == 0 ? null : result;
    }

    @Override
    public List<ShortReplyVo2> getShortByUserId(Long userId, int page) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("page", page);
        List<ShortReplyVo2> result = this.shortReplyMapper.getShortByUserId(params);
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<TopicOrReplyInfoVo> getShortByReplyIdOfNews(Long replyId, int page) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("replyId", replyId);
        params.put("page", page);
        List<TopicOrReplyInfoVo> result = this.shortReplyMapper.getShortByReplyIdOfNews(params);
        return result.size() == 0 ? null : result;
    }
}
