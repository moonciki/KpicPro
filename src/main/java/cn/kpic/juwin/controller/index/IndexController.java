package cn.kpic.juwin.controller.index;

import cn.kpic.juwin.domain.Home;
import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.PbarType;
import cn.kpic.juwin.domain.vo.AlbumVo;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.UserVo;
import cn.kpic.juwin.mapper.PbarMapper;
import cn.kpic.juwin.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private PbarMapper pbarMapper;

    @RequestMapping(value = "/home/img")
    @ResponseBody
    public List<Home> getAllImg(){
        try{
            return this.indexService.getAllImg();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/home/type")
    @ResponseBody
    public List<PbarType> getAllTypes(){
        try{
            return this.indexService.getAllTypes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/home/album")
    @ResponseBody
    public List<AlbumVo> getAllAlbums(){
        try{
            return this.indexService.getAllAlbums();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/home/user")
    @ResponseBody
    public List<UserVo> getAllUsers(){
        try{
            return this.indexService.getAllUsers();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/home/post")
    @ResponseBody
    public List<PbarHomeTopicPost> getAllPost(@RequestParam(value = "page", defaultValue = "0", required = false)Integer page){
        try{
            return this.indexService.getAllPost(page*10);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/home/warn")
    @ResponseBody
    public List<UserVo> getAllWarnUsers(){
        try{
            return this.indexService.getAllWarnUsers();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/home/pbar")
    @ResponseBody
    public List<Pbar> getAllPbars(){
        try{
            return this.indexService.getAllPbars();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/home/new_pbar")
    @ResponseBody
    public List<Pbar> getAllNewPbars(){
        try{
            return this.pbarMapper.getAllNewPbars();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
