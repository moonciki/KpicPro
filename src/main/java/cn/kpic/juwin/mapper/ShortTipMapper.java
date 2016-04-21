package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.ShortTip;
import cn.kpic.juwin.domain.vo.ShortTips;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
public interface ShortTipMapper {

    void save(ShortTip shortTip);

    List<Long> getIdsByStoreIds(Map<String, Object> params);

    List<ShortTips> getAllShortTips(Map params);

    void delAllShortTips(Long id);

}
