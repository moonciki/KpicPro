package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Music;
import cn.kpic.juwin.mapper.MusicMapper;
import cn.kpic.juwin.service.MusicService;
import cn.kpic.juwin.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/4/21.
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Override
    @Transactional
    public void saveMusic(Music music) {
        music.setIsdel(0);
        music.setCreateTime(new Date());
        music.setUserId(CurrentUser.getUser().getId());
        this.musicMapper.save(music);
    }

}
