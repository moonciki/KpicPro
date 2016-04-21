package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.PbarManagerApply;
import cn.kpic.juwin.domain.vo.PbarManagerApplyVo;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/13.
 */
public interface PbarManagerApplyMapper {

    void save(PbarManagerApply pbarManagerApply);

    Integer isApply(Map<String, Object> params);

    List<PbarManagerApplyVo> getAllApply(Map params);

    void delete(Long id);

    void pass(Long id);

}
