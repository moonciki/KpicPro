package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.vo.PbarIndexVo;
import cn.kpic.juwin.domain.vo.UserPbarVo;

import java.util.List;
import java.util.Map;

public interface PbarMapper {

    Long insert(Pbar Pbar);


    void update(Pbar Pbar);


    Pbar getById(Long id);


    void deleteById(Long id);

    List<UserPbarVo> getAllSelfPbar(Map<String, Object> params);

    PbarIndexVo getPbarIndex(Long id);

    void addTopicNum(Long id);

    void addFocusNum(Long id);

    void delFocusNum(Long id);

    void delFocus(Map params);

    List<PbarIndexVo> getSearchResult(Map params);

    List<Long> getAllIds();

    List<Pbar> getAllNewPbars();

    List<Pbar> getAllPbarsByType(Map params);

}