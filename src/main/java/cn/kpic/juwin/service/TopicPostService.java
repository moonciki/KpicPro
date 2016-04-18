package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.TopicPostMsg;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
public interface TopicPostService {

    List<TopicPost> getTopicByPbarId(Long pbarId);

    List<PbarHomeTopicPost> getAllTopicByPbarId(Long pbarId, int page);

    TopicPost addTopicPost(String title, String content, String shortContent, Long pbarId, Long userId);

    List<PbarHomeTopicPost> getAllTopicOrBlogByPbarId(Long pbarId, int page, int isBlog);

    List<PbarHomeTopicPost> getAllJpTopicByPbarId(Long pbarId, int page, int isBoutique);

    TopicPostMsg getByUid(Long uuId);

    List<PbarHomeTopicPost> getAllTopicPostByUid(Long userId, int page, int isBlog, String orderBy);

    void del(Long id);

}
