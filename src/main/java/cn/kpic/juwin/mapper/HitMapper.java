package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Hit;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/6/2.
 */
public interface HitMapper {

    void save(Hit hit);

    List<Hit> getAllByPbarIdAndYM(Map params);

}
