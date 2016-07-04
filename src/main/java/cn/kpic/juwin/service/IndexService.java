package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Home;
import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.PbarType;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.AlbumVo;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.UserVo;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public interface IndexService {

    List<PbarType> getAllTypes();

    List<AlbumVo> getAllAlbums();

    List<UserVo> getAllUsers();

    List<PbarHomeTopicPost> getAllPost(Integer page);

    List<Home> getAllImg();

    List<UserVo> getAllWarnUsers();

    List<Pbar> getAllPbars();

}
