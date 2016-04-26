package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.PbarAlbum;
import cn.kpic.juwin.domain.vo.PbarAlbumList;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/25.
 */
public interface PbarAlbumService {

    void addPbarAlbum(PbarAlbum pbarAlbum);

    void delete(Long id);

    List<PbarAlbumList> getAllAlbumByPbarId(Long pbarId, Integer page);

}
