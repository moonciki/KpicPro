package cn.kpic.juwin.controller.home;

import cn.kpic.juwin.domain.Home;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.service.IndexService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/28.
 */
@Controller
public class HomeController {

    private Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/")
    public String index(Model model){
        User curr_user = CurrentUser.getUser();
        if(curr_user != null){
            model.addAttribute("user", curr_user);
        }
        List<Home> result = this.indexService.getAllImg();
        List<Integer> js = new ArrayList<>();
        for(int i = 1; i<= result.size(); i++){
            js.add(i);
        }
        model.addAttribute("imgs", result);
        model.addAttribute("js", js);
        model.addAttribute("size", result == null ? 0 : result.size());
        return "/home";
    }

}
