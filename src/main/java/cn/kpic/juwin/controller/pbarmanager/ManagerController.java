package cn.kpic.juwin.controller.pbarmanager;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.service.PbarService;
import cn.kpic.juwin.service.UserService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
            return "/pbar/manager_pbar_info";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("enter pbar manager error !");
            return "/404";
        }
    }

}
