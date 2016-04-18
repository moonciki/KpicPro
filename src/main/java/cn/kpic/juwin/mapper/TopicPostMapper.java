package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.TopicOrReplyInfoVo;
import cn.kpic.juwin.domain.vo.TopicPostMsg;

import javax.jms.Topic;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
public interface TopicPostMapper {

    Long save(TopicPost topicPost);

    List<TopicPost> getByPbarId(Long pbarId);

    TopicOrReplyInfoVo getByPbarIdOfNews(Long id);

    List<PbarHomeTopicPost> getTopicPostByPbarId(Map<String, Object> params);

    List<PbarHomeTopicPost> getTopicBlogOrPostByPbarId(Map<String, Object> params);

    List<PbarHomeTopicPost> getJpTopicByPbarId(Map<String, Object> params);

    TopicPostMsg getByUid(Long id);

    TopicPost getById(Long id);

    void update(TopicPost topicPost);

    List<PbarHomeTopicPost> getAllTopicPostByUid(Map<String, Object> params);

    void del(Long id);

}
