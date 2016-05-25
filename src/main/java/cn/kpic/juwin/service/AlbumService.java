package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Album;
import cn.kpic.juwin.domain.Pic;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/20.
 */
public interface AlbumService {

    void saveAlbum(Album album, List<Pic> pics);
    void delAlbum(Long id);
    void addMusic(String url, Long id);
}
