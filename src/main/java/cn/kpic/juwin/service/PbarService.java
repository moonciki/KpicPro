package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.vo.PbarIndexVo;
import cn.kpic.juwin.domain.vo.UserPbarVo;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/22.
 */
public interface PbarService {

    void save(Pbar pbar);

    Pbar getPbarById(Long id);

    List<UserPbarVo> getAllSelfPbar(Long userId, int page);

    PbarIndexVo getPbarIndex(Long id);

    List<Pbar> getAllPbarFocus(Long userId, int page);

    void update(Pbar pbar);

    void clearCache(String key);

    void delFocusService(Long userId, Long pbarId);

    List<PbarIndexVo> getSearchResult(String kword, Integer page);

}
