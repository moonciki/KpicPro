package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Damaku;
import cn.kpic.juwin.domain.vo.DamakuVo;
import cn.kpic.juwin.mapper.DamakuMapper;
import cn.kpic.juwin.service.DamakuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public class DamakuServiceImpl implements DamakuService {

    @Autowired
    private DamakuMapper damakuMapper;

    @Override
    public void save(Damaku damaku) {
        this.damakuMapper.save(damaku);
    }

    @Override
    public List<DamakuVo> getAllDamakuByMvId(Integer mvId, Integer size) {
        return this.damakuMapper.getAllDamakuByMvId(mvId, size);
    }
}
