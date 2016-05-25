package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.PicComment;
import cn.kpic.juwin.domain.vo.PicCommentVo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public interface PicCommentService {

    void save(PicComment picComment);

    List<PicCommentVo> getAllByAlbumId(Long albumId, Integer page);

}
