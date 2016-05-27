package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.ShortReply;
import cn.kpic.juwin.domain.ShortTip;
import cn.kpic.juwin.domain.vo.ShortReplyVo;
import cn.kpic.juwin.domain.vo.ShortReplyVo2;
import cn.kpic.juwin.domain.vo.TopicOrReplyInfoVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/3 0003.
 */
public interface ShortReplyMapper {

    void insert(ShortReply shortReply);

    void del(Long shortId);

    void clear();

    List<ShortReplyVo> getShortByReplyId(Map<String, Object> params);

    List<ShortReplyVo2> getShortByUserId(Map<String, Object> params);

    List<TopicOrReplyInfoVo> getShortByReplyIdOfNews(Map<String, Object> params);

    ShortReply getObj(Long id);

}
