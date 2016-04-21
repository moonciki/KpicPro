package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Album;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/20.
 */
public interface AlbumMapper {

    void save(Album album);

    List<Album> gatAllAlbum(Map params);

    Album gatAlbum(Long id);

}
