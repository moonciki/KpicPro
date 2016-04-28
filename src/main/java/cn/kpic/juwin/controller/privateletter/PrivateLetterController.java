package cn.kpic.juwin.controller.privateletter;

import cn.kpic.juwin.domain.PrivateLetter;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserLevel;
import cn.kpic.juwin.domain.vo.PrivateLetterVo;
import cn.kpic.juwin.service.PrivateLetterService;
import cn.kpic.juwin.utils.CurrentUser;
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
 * Created by bjsunqinwen on 2016/4/26.
 */
@Controller
public class PrivateLetterController {

    @Autowired
    private PrivateLetterService privateLetterService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/private/letter", method = RequestMethod.GET)
    public String getSelfLetter(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            model.addAttribute("flag",8);
            return "/user/private_letter_list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

     @RequiresPermissions({"user"})
     @RequestMapping(value = "/user/private/letter", method = RequestMethod.POST)
     @ResponseBody
     public List<PrivateLetterVo> getSelfLetter2(@RequestParam(value = "page", defaultValue = "0", required = true)Integer page, Model model){
        User curr_user = CurrentUser.getUser();
        try{
            return this.privateLetterService.getAllNotReply(curr_user.getId(), page);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/private/save", method = RequestMethod.POST)
    @ResponseBody
    public boolean addLetter(String content, Long userId, Model model){
        User curr_user = CurrentUser.getUser();
        try{
            PrivateLetter privateLetter = new PrivateLetter();
            privateLetter.setCreateTime(new Date());
            privateLetter.setUserId(userId);
            privateLetter.setFromUserId(curr_user.getId());
            privateLetter.setContent(content);
            privateLetter.setIsReply(0);
            privateLetter.setUpdateTime(new Date());
            this.privateLetterService.save(privateLetter);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/private/self/letter", method = RequestMethod.GET)
    public String getSelfLetter3(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            model.addAttribute("flag",81);
            return "/user/private_letter_self_list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/private/self/letter", method = RequestMethod.POST)
    @ResponseBody
    public List<PrivateLetterVo> getSelfLetter4(@RequestParam(value = "page", defaultValue = "0", required = true)Integer page, Model model){
        User curr_user = CurrentUser.getUser();
        try{
            return this.privateLetterService.getAllSelf(curr_user.getId(), page);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/user/private/reply",method = RequestMethod.POST)
    @RequiresPermissions({"user"})
    @ResponseBody
    public boolean replyPrivateLetter(String reply, Long id, Long userId){
        try{
            if("".equals(reply.trim())||id == null){
                return false;
            }
            PrivateLetter privateLetter = new PrivateLetter();
            privateLetter.setIsReply(1);
            privateLetter.setReply(reply);
            privateLetter.setId(id);
            this.privateLetterService.update(privateLetter, userId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
