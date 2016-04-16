package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.ReplyPost;
import cn.kpic.juwin.domain.vo.ReplyPostList;
import cn.kpic.juwin.domain.vo.SelfReply;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/3/18.
 */
public interface ReplyPostService {

    public List<ReplyPostList> getAllReplyByTopicId(Long topicId, int page);

    public ReplyPost saveReplyPost(ReplyPost replyPost, Long pbarId, Long topicUserId);

    public List<SelfReply> getAllSelfReply(Long userId, int page, String orderBy);

    public ReplyPost getShortTextById(Long replyId);

}
