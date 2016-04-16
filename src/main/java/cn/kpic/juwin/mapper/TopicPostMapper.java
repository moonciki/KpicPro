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

    public Long save(TopicPost topicPost);

    public List<TopicPost> getByPbarId(Long pbarId);

    public TopicOrReplyInfoVo getByPbarIdOfNews(Long id);

    public List<PbarHomeTopicPost> getTopicPostByPbarId(Map<String, Object> params);

    public List<PbarHomeTopicPost> getTopicBlogOrPostByPbarId(Map<String, Object> params);

    public List<PbarHomeTopicPost> getJpTopicByPbarId(Map<String, Object> params);

    public TopicPostMsg getByUid(Long id);

    public TopicPost getById(Long id);

    public void update(TopicPost topicPost);

    public List<PbarHomeTopicPost> getAllTopicPostByUid(Map<String, Object> params);

}
