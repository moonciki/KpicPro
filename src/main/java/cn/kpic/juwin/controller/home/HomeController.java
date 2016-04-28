package cn.kpic.juwin.controller.home;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bjsunqinwen on 2016/4/28.
 */
@Controller
public class HomeController {

    private Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/")
    public String index(Model model){
        User curr_user = CurrentUser.getUser();
        if(curr_user != null){
            model.addAttribute("user", curr_user);
        }
        return "/home";
    }

}
