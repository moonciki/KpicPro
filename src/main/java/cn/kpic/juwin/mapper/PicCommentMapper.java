package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.PicComment;
import cn.kpic.juwin.domain.vo.PicCommentVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public interface PicCommentMapper {

    void save(PicComment picComment);

    List<PicCommentVo> getAllByAlbumId(Map params);

}
