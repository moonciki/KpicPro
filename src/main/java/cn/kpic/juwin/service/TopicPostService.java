package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.TopicPostMsg;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
public interface TopicPostService {

    public List<TopicPost> getTopicByPbarId(Long pbarId);

    public List<PbarHomeTopicPost> getAllTopicByPbarId(Long pbarId, int page);

    public TopicPost addTopicPost(String title, String content, String shortContent, Long pbarId, Long userId);

    public List<PbarHomeTopicPost> getAllTopicOrBlogByPbarId(Long pbarId, int page, int isBlog);

    public List<PbarHomeTopicPost> getAllJpTopicByPbarId(Long pbarId, int page, int isBoutique);

    public TopicPostMsg getByUid(Long uuId);

    public List<PbarHomeTopicPost> getAllTopicPostByUid(Long userId, int page, int isBlog, String orderBy);

}
