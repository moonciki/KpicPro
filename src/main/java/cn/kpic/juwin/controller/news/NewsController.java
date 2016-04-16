package cn.kpic.juwin.controller.news;

import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.UserNewsVo;
import cn.kpic.juwin.service.MsgService;
import cn.kpic.juwin.service.UserNewsService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/5.
 */
@Controller
public class NewsController {

    @Autowired
    private UserNewsService userNewsService;

    @Autowired
    private MsgService msgService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/all/notread/news")
    @ResponseBody
    public Integer getAllNotRead(){
        User user = CurrentUser.getUser();
        Integer num = msgService.notRead(user.getId(), 0);
        return num;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/all/user/get/news")
    @ResponseBody
    public List<UserNewsVo> getAllUserNews(@RequestParam(value = "page", defaultValue = "0",required = false)int page){
        User user = CurrentUser.getUser();
        List<UserNewsVo> result = userNewsService.getAllUserNews(user.getId(), page * 10);
        return result;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "user/all/user/news")
    public String getAllUserNewsTransfer(Model model){
        User user_curr = CurrentUser.getUser();
        model.addAttribute("user", user_curr);
        model.addAttribute("flag", 6);
        User user = CurrentUser.getUser();
        model.addAttribute("num", msgService.notRead(user.getId(), 0));
        Msg msg = new Msg();
        msg.setUserId(user.getId());
        msg.setType(0);
        msg.setNum(0);
        msgService.update(msg);
        return "/user/manager_center_user_news";
    }

}
