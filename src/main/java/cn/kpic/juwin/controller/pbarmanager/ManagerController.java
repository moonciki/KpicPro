package cn.kpic.juwin.controller.pbarmanager;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.service.PbarService;
import cn.kpic.juwin.service.UserService;
import cn.kpic.juwin.shiro.redis.RedisCache;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bjsunqinwen on 2016/4/16.
 */
@Controller
public class ManagerController {

    private Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private PbarService pbarService;

    @Autowired
    private UserService userService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/sub4615{pbarId}")
    public String enterPbarManager(@PathVariable("pbarId") Long pbarId,Model model){
        try{
            User curr_user = CurrentUser.getUser();
            model.addAttribute("user", curr_user);
            model.addAttribute("role", this.userService.getRole(curr_user.getId(), pbarId));
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 1);
            return "/pbar/manager_pbar_info";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("enter pbar manager error !");
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/upd4615{pbarId}")
    public String updPbarManager(@PathVariable("pbarId") Long pbarId,Model model){
        try{
            User curr_user = CurrentUser.getUser();
            model.addAttribute("user", curr_user);
            model.addAttribute("role", this.userService.getRole(curr_user.getId(), pbarId));
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 2);
            return "/pbar/manager_pbar_update";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("update page enter pbar manager error !");
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/upd4615{pbarId}", method= RequestMethod.POST)
    @ResponseBody
    public boolean updPbarManagerForm(@PathVariable("pbarId") Long pbarId,Pbar pbar){
        try{
            pbar.setId(pbarId);
            this.pbarService.update(pbar);
            this.pbarService.clearCache(RedisCacheKey.PBAR_INDEX+pbarId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("update page enter pbar manager error !");
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/tip4615{pbarId}")
    public String tips(@PathVariable("pbarId") Long pbarId,Model model){
        try{
            User curr_user = CurrentUser.getUser();
            model.addAttribute("user", curr_user);
            model.addAttribute("role", this.userService.getRole(curr_user.getId(), pbarId));
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 3);
            return "/pbar/manager_pbar_tips";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("get all tips page error ! pbarId = " + pbarId);
            return "/404";
        }
    }

}
