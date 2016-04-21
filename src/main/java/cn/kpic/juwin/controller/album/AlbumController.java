package cn.kpic.juwin.controller.album;

import cn.kpic.juwin.domain.Album;
import cn.kpic.juwin.domain.Pic;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserLevel;
import cn.kpic.juwin.mapper.AlbumMapper;
import cn.kpic.juwin.service.AlbumService;
import cn.kpic.juwin.service.UserLevelService;
import cn.kpic.juwin.utils.CurrentUser;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/20.
 */
@Controller
public class AlbumController {

    private Logger logger = Logger.getLogger(AlbumController.class);

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserLevelService userLevelService;

    @Autowired
    private AlbumMapper albumMapper;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/make/album", method = RequestMethod.GET)
    public String makeAlbum(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("flag",10);
            return "/user/make_album";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/make/album", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveAlbum(Album album, String pics){
        try{
            List<Pic> result = JSON.parseArray(pics, Pic.class);
            this.albumService.saveAlbum(album, result);
            return true;
        }catch (Exception e){
            logger.error("save album error ! userId = " + CurrentUser.getUser().getId());
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/list/album", method = RequestMethod.GET)
    public String getAllAlbum(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("flag",9);
            return "/user/album_list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/list/album", method = RequestMethod.POST)
    @ResponseBody
    public List<Album> getAllAlbum2(@RequestParam(value = "page", defaultValue = "0",required = false)int page){
        User curr_user = CurrentUser.getUser();
        try{
            Map params = new HashMap();
            params.put("page", page*10);
            params.put("userId", curr_user.getId());
            List<Album> result = this.albumMapper.gatAllAlbum(params);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/album/ab1654{abId}", method = RequestMethod.GET)
    public String readAlbum(@PathVariable("abId") Long abId, Model model){

        Album album = this.albumMapper.gatAlbum(abId);
        if(album == null){
            return "/404";
        }
        model.addAttribute("user", CurrentUser.getUser());
        model.addAttribute("album", album);
        return "/album/read_album";
    }
}
