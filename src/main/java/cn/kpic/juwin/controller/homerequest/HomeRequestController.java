package cn.kpic.juwin.controller.homerequest;

import cn.kpic.juwin.domain.Home;
import cn.kpic.juwin.domain.HomeRequest;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.service.HomeRequestService;
import cn.kpic.juwin.service.UserIntegrityService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.Map;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
@Controller
public class HomeRequestController {

    private Logger logger = Logger.getLogger(HomeRequestController.class);

    @Autowired
    private HomeRequestService homeRequestService;

    @Autowired
    private UserIntegrityService userIntegrityService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/home/request")
    public String getListByUserId(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            model.addAttribute("flag",101);
            model.addAttribute("jc", this.userIntegrityService.getByUserId(curr_user.getId()));
            return "/user/make_home_request";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/home/save")
    @ResponseBody
    public Boolean save(HomeRequest homeRequest){
        User curr_user = CurrentUser.getUser();
        try{
            homeRequest.setMsg(StringDeal.getText(homeRequest.getMsg()));
            homeRequest.setUserId(curr_user.getId());
            homeRequest.setCreateTime(new Date());
            homeRequest.setStatus(0);
            homeRequest.setIsdel(0);
            this.homeRequestService.save(homeRequest);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("save home request error !");
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/home/list", method = RequestMethod.GET)
    public String selfList(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            model.addAttribute("flag",666);
            model.addAttribute("jc", this.userIntegrityService.getByUserId(curr_user.getId()));
            return "/user/home_request_list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/home/list2", method = RequestMethod.POST)
    @ResponseBody
    public List<HomeRequest> selfListInter(@RequestParam(value = "page", defaultValue = "0", required = false)Integer page){
        User curr_user = CurrentUser.getUser();
        try{
            List<HomeRequest> result = this.homeRequestService.getListByUserId(curr_user.getId(), page*10);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/home/del", method = RequestMethod.POST)
    @ResponseBody
    public Boolean del(Long id){
        User curr_user = CurrentUser.getUser();
        try{
            HomeRequest homeRequest = new HomeRequest();
            homeRequest.setIsdel(1);
            homeRequest.setId(id);
            this.homeRequestService.update(homeRequest);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
