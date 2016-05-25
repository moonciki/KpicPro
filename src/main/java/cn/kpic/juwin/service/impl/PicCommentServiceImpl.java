package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.PicComment;
import cn.kpic.juwin.domain.vo.PicCommentVo;
import cn.kpic.juwin.mapper.PicCommentMapper;
import cn.kpic.juwin.service.PicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
@Service
public class PicCommentServiceImpl implements PicCommentService{

    @Autowired
    private PicCommentMapper picCommentMapper;

    @Override
    @Transactional
    public void save(PicComment picComment) {
        this.picCommentMapper.save(picComment);
    }

    @Override
    public List<PicCommentVo> getAllByAlbumId(Long albumId, Integer page) {
        Map params = new HashMap();
        params.put("albumId", albumId);
        params.put("page", page);
        List<PicCommentVo> result = this.picCommentMapper.getAllByAlbumId(params);
        return result.size() == 0 ? null : result;
    }
}
