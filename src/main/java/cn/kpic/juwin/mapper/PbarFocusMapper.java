package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.PbarFocus;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/9.
 */
public interface PbarFocusMapper {

    void save(PbarFocus pbarFocus);

    void del(Long id);

    List<Pbar> getAllPbarFocus(Map<String, Object> params);

    PbarFocus getPbarFocusByPbarIdAndUserId(Map<String, Object> params);

}
