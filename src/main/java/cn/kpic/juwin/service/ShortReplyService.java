package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.ShortReply;
import cn.kpic.juwin.domain.vo.ShortReplyVo;
import cn.kpic.juwin.domain.vo.ShortReplyVo2;
import cn.kpic.juwin.domain.vo.TopicOrReplyInfoVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/3 0003.
 */
public interface ShortReplyService {

    void insert(ShortReply shortReply, Long pbarId);

    void del(Long shortId);

    void clear();

    List<ShortReplyVo> getShortByReplyId(Long replyId, int page);

    List<ShortReplyVo2> getShortByUserId(Long userId, int page);

    List<TopicOrReplyInfoVo> getShortByReplyIdOfNews(Long replyId, int page);

}
