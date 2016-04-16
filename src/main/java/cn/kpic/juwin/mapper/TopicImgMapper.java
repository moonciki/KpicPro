package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.TopicImg;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
public interface TopicImgMapper {

    public void save(List<TopicImg> topicImg);

    public List<TopicImg> getByTopicId(Long topicId);

}
