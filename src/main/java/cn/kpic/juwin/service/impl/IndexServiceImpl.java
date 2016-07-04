package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.AlbumVo;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.UserVo;
import cn.kpic.juwin.mapper.IndexMapper;
import cn.kpic.juwin.mapper.TopicImgMapper;
import cn.kpic.juwin.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Autowired
    private TopicImgMapper topicImgMapper;

    @Override
    public List<PbarType> getAllTypes() {
        List<PbarType> result = this.indexMapper.getAllTypes();
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<AlbumVo> getAllAlbums() {
        List<AlbumVo> result = this.indexMapper.getAllAlbums();
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<UserVo> getAllUsers() {
        List<UserVo> result = this.indexMapper.getAllUsers();
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<PbarHomeTopicPost> getAllPost(Integer page) {
        List<PbarHomeTopicPost> result = this.indexMapper.getAllPost(page);
        if(result != null && result.size() != 0){
            for(PbarHomeTopicPost pbarHomeTopicPost : result){
                /** 通过下面这个函数查找到前4张图片*/
                List<TopicImg> listImg = topicImgMapper.getByTopicId(pbarHomeTopicPost.getId());
                pbarHomeTopicPost.setImg(listImg.size() == 0 ? null : listImg);
            }
        }
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<Home> getAllImg() {
        List<Home> result = this.indexMapper.getAllImg();
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<UserVo> getAllWarnUsers() {
        List<UserVo> result = this.indexMapper.getAllWarnUsers();
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<Pbar> getAllPbars() {
        List<Pbar> result = this.indexMapper.getAllPbars();
        return result.size() == 0 ? null : result;
    }
}
