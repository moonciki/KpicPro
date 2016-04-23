package cn.kpic.juwin.controller.music;

import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.Songs;
import cn.kpic.juwin.domain.vo.UploadTokenInfo;
import cn.kpic.juwin.http.HttpRequest;
import cn.kpic.juwin.mapper.MusicMapper;
import cn.kpic.juwin.qiniu.kpic2.QiniuService;
import cn.kpic.juwin.service.MusicService;
import cn.kpic.juwin.service.UserLevelService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.NetEaseMusicUtils;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bjsunqinwen on 2016/4/21.
 */
@Controller
public class MusicController {

    private Logger logger = Logger.getLogger(MusicController.class);

    @Autowired
    private UserLevelService userLevelService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private QiniuService qiniuService;

    /** 获取uptoken*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/music/upload")
    @ResponseBody
    public Map<String, Object> upload(){
        Map<String, Object> map = new HashMap();

        try {
            UploadTokenInfo uploadTokenInfo = qiniuService.generateUploadToken2();
            map.put("success", true);
            map.put("uptoken", uploadTokenInfo.getToken());
        } catch(Throwable t){
            logger.info(t.getMessage(), t);
            map.put("success", false);
            map.put("message", t.getMessage());
        }

        return map;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/make/music", method = RequestMethod.GET)
    public String makeMusic(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("flag",14);
            return "/user/make_music";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/make/music", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveMusic(Music music){
        try{
            this.musicService.saveMusic(music);
            return true;
        }catch (Exception e){
            logger.error("save music error ! userId = " + CurrentUser.getUser().getId());
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/list/music", method = RequestMethod.GET)
    public String getAllMusic(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("flag",13);
            return "/user/music_list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/list/music", method = RequestMethod.POST)
    @ResponseBody
    public List<Music> getAllMusic2(@RequestParam(value = "page", defaultValue = "0",required = false)int page){
        User curr_user = CurrentUser.getUser();
        try{
            Map params = new HashMap();
            params.put("page", page*10);
            params.put("userId", curr_user.getId());
            List<Music> result = this.musicMapper.gatAllMusic(params);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/music/uuid")
    @ResponseBody
    public Map<String, Object> uuid(){
        Map<String, Object> map = new HashMap();

        try {
            String uuid = "music/"+this.generateUUID() + ".mp3";
            map.put("success", true);
            map.put("uuid", uuid);
        } catch(Throwable t){
            logger.info(t.getMessage(), t);
            map.put("success", false);
        }

        return map;
    }

    /** 网易云音乐--搜索接口*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/netease/music/search")
    @ResponseBody
    public List<Songs> all(String key){
        try{
            return NetEaseMusicUtils.getSearchResult(key);
        }catch (Exception e){
            return null;
        }
    }

    private String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
