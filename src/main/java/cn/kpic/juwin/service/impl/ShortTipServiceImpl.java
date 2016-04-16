package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.ShortTip;
import cn.kpic.juwin.mapper.ShortTipMapper;
import cn.kpic.juwin.service.ShortTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
@Service
public class ShortTipServiceImpl implements ShortTipService {

    @Autowired
    private ShortTipMapper shortTipMapper;

    @Transactional
    @Override
    public void save(ShortTip shortTip) {
        this.shortTipMapper.save(shortTip);
    }

}
