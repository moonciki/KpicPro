package cn.kpic.juwin.controller.album;

import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.AlbumVo;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.domain.vo.PicCommentVo;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.mapper.AlbumMapper;
import cn.kpic.juwin.mapper.PicMapper;
import cn.kpic.juwin.service.AlbumService;
import cn.kpic.juwin.service.PicCommentService;
import cn.kpic.juwin.service.UserLevelService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private PicMapper picMapper;

    @Autowired
    private PicCommentService picCommentService;

    @Autowired
    private SystemMsgQueueMessageSender systemMsgQueueMessageSender;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/make/album", method = RequestMethod.GET)
    public String makeAlbum(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
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

    @RequestMapping(value = "/all/album/pics")
    @ResponseBody
    public List<Pic> getAllPics(Long albumId){
        try{
            List<Pic> result = this.picMapper.getAllPics(albumId);
            return result.size() == 0 ? null : result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/list/album", method = RequestMethod.GET)
    public String getAllAlbum(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
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

        AlbumVo album = this.albumMapper.getAlbumMsg(abId);
        if(album == null){
            return "/404";
        }
        model.addAttribute("user", CurrentUser.getUser());
        model.addAttribute("album", album);
        model.addAttribute("pics", this.picMapper.getAllPics(abId));
        return "/album/read_album";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/album/del", method = RequestMethod.POST)
    @ResponseBody
    public boolean delAlbum(Long id){
        try{
            if(id != null){
                this.albumService.delAlbum(id);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("del album error ! the album id = " + id);
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/album/make/music", method = RequestMethod.POST)
    @ResponseBody
    public boolean addMusic(String url, Long albumId){
        try{
            if(StringUtils.isBlank(url) || albumId == null){
                return false;
            }else{
                this.albumService.addMusic(url, albumId);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("add album music error ! the album id = "+albumId);
            return false;
        }
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/album/comment/save")
    @ResponseBody
    public Boolean addComment(String content,Long albumId, Long toUserId){
        if(StringUtils.isBlank(content) || albumId == null || toUserId == null){
            return false;
        }
        try{
            User curr_user = CurrentUser.getUser();
            PicComment picComment = new PicComment();
            picComment.setUserId(curr_user.getId());
            picComment.setCreateTime(new Date());
            picComment.setAlbumId(albumId);
            picComment.setContent(StringDeal.getText(content));
            this.picCommentService.save(picComment);
            /** 发系统通知*/
            if(!String.valueOf(toUserId).equals(String.valueOf(curr_user.getId()))){
                JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
                jmsSystemMsg.setTitle("您发布的图集收到了新的评论");
                jmsSystemMsg.setContent("<a href=\"/album/ab1654"+albumId+"\" target=\"_blank\">点击查看详情</a>");
                jmsSystemMsg.setUserId(toUserId);
                this.systemMsgQueueMessageSender.send(jmsSystemMsg);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("save album commnet is error ! album id = " + albumId);
            return false;
        }
    }

    @RequestMapping(value = "/all/album/pl", method = RequestMethod.POST)
    @ResponseBody
    public List<PicCommentVo> getAll(Long albumId, Integer page){
        if(page == null || albumId == null){
            return null;
        }
        return this.picCommentService.getAllByAlbumId(albumId,page*10);
    }
}
