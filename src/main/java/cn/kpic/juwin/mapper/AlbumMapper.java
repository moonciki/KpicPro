package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Album;
import cn.kpic.juwin.domain.vo.AlbumVo;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/20.
 */
public interface AlbumMapper {

    void save(Album album);

    List<Album> gatAllAlbum(Map params);

    Album gatAlbum(Long id);

    void delAlbum(Long id);

    void addMusic(Map params);

    AlbumVo getAlbumMsg(Long id);
}
