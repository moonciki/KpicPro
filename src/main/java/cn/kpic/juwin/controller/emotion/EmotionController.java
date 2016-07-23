package cn.kpic.juwin.controller.emotion;

import cn.kpic.juwin.domain.Emotion;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserEmotion;
import cn.kpic.juwin.domain.vo.EmotionVo;
import cn.kpic.juwin.mapper.UserEmotionMapper;
import cn.kpic.juwin.service.EmotionService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/7 0007.
 */
@Controller
public class EmotionController {

    private Logger logger = Logger.getLogger(EmotionController.class);

    @Autowired
    private EmotionService emotionService;

    @Autowired
    private UserEmotionMapper userEmotionMapper;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/emotion/save", method = RequestMethod.GET)
    public String save1(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            model.addAttribute("flag",100);
            return "/user/make_emotion";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/emotion/save", method = RequestMethod.POST)
    @ResponseBody
    public boolean save1(String title, String url){
        try{
            if(StringUtils.isBlank(title) || StringUtils.isBlank(url)){
                return false;
            }
            Emotion emotion = new Emotion();
            emotion.setCreateTime(new Date());
            emotion.setIsdel(0);
            emotion.setTitle(StringDeal.getText(title));
            emotion.setUrl(url);
            emotion.setUserId(CurrentUser.getUser().getId());
            this.emotionService.save(emotion);
            return true;
        }catch (Exception e){
            logger.error("save emotion error ! userId = " + CurrentUser.getUser().getId());
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "user/emotion/list")
    @ResponseBody
    public List<Emotion> getAllEmotion(Integer page){
        try{
            return this.emotionService.getAllByUserId(CurrentUser.getUser().getId(), page * 10);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "user/emotion/del")
    @ResponseBody
    public boolean delEmotion(Long id){
        try{
            this.userEmotionMapper.del(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping(value = "emotion/pool")
    public String emPool(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            return "/emotions";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequestMapping(value = "emotion/lists")
    @ResponseBody
    public List<EmotionVo> getAllEms(@RequestParam(value = "page", defaultValue = "0", required = false)Integer page){
        try{
            return this.emotionService.getAllEms(page);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "emotion/store")
    @ResponseBody
    public boolean storeEms(Integer id){
        try{
            UserEmotion userEmotion = new UserEmotion();
            userEmotion.setCreateTime(new Date());
            userEmotion.setUserId(CurrentUser.getUser().getId());
            userEmotion.setEmotionId(id);
            this.userEmotionMapper.save(userEmotion);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
