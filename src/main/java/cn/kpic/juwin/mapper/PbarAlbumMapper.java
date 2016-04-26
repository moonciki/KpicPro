package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.PbarAlbum;
import cn.kpic.juwin.domain.vo.PbarAlbumList;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/25.
 */
public interface PbarAlbumMapper {

    void save(PbarAlbum pbarAlbum);

    void del(Long id);

    List<PbarAlbumList> getAllAlbumByPbarId(Map params);

}
