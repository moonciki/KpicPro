package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Album;
import cn.kpic.juwin.domain.Pic;
import cn.kpic.juwin.mapper.AlbumMapper;
import cn.kpic.juwin.mapper.PicMapper;
import cn.kpic.juwin.service.AlbumService;
import cn.kpic.juwin.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/20.
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private PicMapper picMapper;

    @Override
    @Transactional
    public void saveAlbum(Album album, List<Pic> pics) {
        album.setUserId(CurrentUser.getUser().getId());
        album.setCreateTime(new Date());
        album.setIsdel(0);
        album.setPicNum(0);
        this.albumMapper.save(album);
        for(Pic pic : pics){
            pic.setAlbumId(album.getId());
        }
        this.picMapper.saves(pics);
    }

}
