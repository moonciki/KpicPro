package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.PrivateLetter;
import cn.kpic.juwin.domain.vo.PrivateLetterVo;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/26.
 */
public interface PrivateLetterService {

    void save(PrivateLetter privateLetter);

    void update(PrivateLetter privateLetter);

    List<PrivateLetterVo> getAllNotReply(Long userId, Integer page);

    List<PrivateLetterVo> getAllSelf(Long userId, Integer page);

}
