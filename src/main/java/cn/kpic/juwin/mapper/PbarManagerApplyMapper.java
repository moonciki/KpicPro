package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.PbarManagerApply;

import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/13.
 */
public interface PbarManagerApplyMapper {

    void save(PbarManagerApply pbarManagerApply);

    Integer isApply(Map<String, Object> params);

}
