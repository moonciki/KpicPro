package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Damaku;
import cn.kpic.juwin.domain.vo.DamakuVo;

import java.util.List;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public interface DamakuService {

    void save(Damaku damaku);

    List<DamakuVo> getAllDamakuByMvId(Integer mvId, Integer size);

}
