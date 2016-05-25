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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        album.setPicNum(pics.size());
        this.albumMapper.save(album);
        for(Pic pic : pics){
            pic.setAlbumId(album.getId());
        }
        this.picMapper.saves(pics);
    }

    @Override
    @Transactional
    public void delAlbum(Long id) {
        this.albumMapper.delAlbum(id);
    }

    @Override
    public void addMusic(String url, Long id) {
        Map params = new HashMap();
        params.put("id", id);
        params.put("music", url);
        this.albumMapper.addMusic(params);
    }

}
