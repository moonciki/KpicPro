package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.PrivateLetter;
import cn.kpic.juwin.domain.vo.PrivateLetterVo;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/26.
 */
public interface PrivateLetterMapper {

    void save(PrivateLetter privateLetter);

    void update(PrivateLetter privateLetter);

    List<PrivateLetterVo> getAllNotReply(Map params);

    List<PrivateLetterVo> getAllSelf(Map params);
}
