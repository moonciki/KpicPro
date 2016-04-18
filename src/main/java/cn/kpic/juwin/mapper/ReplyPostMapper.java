package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.ReplyPost;
import cn.kpic.juwin.domain.vo.ReplyPostList;
import cn.kpic.juwin.domain.vo.SelfReply;
import cn.kpic.juwin.domain.vo.TopicOrReplyInfoVo;

import java.util.List;
import java.util.Map;

public interface ReplyPostMapper {

    public Long insert(ReplyPost ReplyPost);

    public void update(ReplyPost ReplyPost);

    public ReplyPost getById(Long id);

    public void deleteById(Integer id);

    public List<ReplyPostList> getAllReplyByTopicId(Map params);

    public List<SelfReply> getAllSelfReply(Map params);

    public TopicOrReplyInfoVo getByIdOfNews(Long id);

    void del(Long id);

}