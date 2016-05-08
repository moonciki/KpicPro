package cn.kpic.juwin.controller.user;

import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import cn.kpic.juwin.domain.vo.JmsUpgrade;
import cn.kpic.juwin.domain.vo.TopicManager;
import cn.kpic.juwin.jms.sender.PbarUpdQueueMessageSender;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.jms.sender.UpgradeQueueMessageSender;
import cn.kpic.juwin.mapper.PbarFocusMapper;
import cn.kpic.juwin.mapper.PbarManagerApplyMapper;
import cn.kpic.juwin.mapper.UserFocusMapper;
import cn.kpic.juwin.service.PbarService;
import cn.kpic.juwin.service.UserLevelService;
import cn.kpic.juwin.service.UserService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private UserLevelService userLevelService;

    @Autowired
    private PbarManagerApplyMapper pbarManagerApplyMapper;

    @Autowired
    private UserFocusMapper userFocusMapper;

    @Autowired
    private PbarService pbarService;

    @Autowired
    private PbarFocusMapper pbarFocusMapper;

    @Autowired
    private PbarUpdQueueMessageSender pbarUpdQueueMessageSender;

    @Autowired
    private SystemMsgQueueMessageSender systemMsgQueueMessageSender;

    @Autowired
    private UpgradeQueueMessageSender upgradeQueueMessageSender;

    private Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/user/getuser")
    @ResponseBody
    public User getUserById(Model model){
        User user = new User();
        try{
            user = userService.getUserById(1L);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping(value = "/user/all")
    @ResponseBody
    public List<User> getAllUser(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "page", defaultValue = "1",required = false)int page,Model model){
        List<User> list = new ArrayList<>();
        try{
            list = userService.getAllUser();
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @RequestMapping(value = "/list")
    public String getUserList(Model model){
        return "/list";
    }

    @RequestMapping(value = "/qxbg")
    public String qxbg(Model model){
        return "/qxbg";
    }

    @RequestMapping(value = "/sw")
    @ResponseBody
    public String sw(Model model){
        User user = new User();
        user.setName("ddddd");
        user.setAge(18);
        try{
            this.userService.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "sd";
    }

    @RequestMapping(value = "/login")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            Object object = subject.getPrincipal();
            if(object != null && object instanceof User){
                User currentUser = (User)object;
            }
        }
        return "/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            subject.logout();
        }
        return "/index";
    }

    @RequestMapping(value = "/logout2")
    @ResponseBody
    public Map<String, Object> logout2(){
        Map<String, Object> map = new HashMap<>();
        try{
            Subject subject = SecurityUtils.getSubject();
            if(subject != null){
                subject.logout();
            }
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "/index";
    }

    @RequestMapping(value = "/jugelogin")
    @ResponseBody
    public Map<String, Object> jugeLogin(String name,String password, Model model){
        Map<String, Object> map = new HashMap<>();
        try{
            map.put("success", userService.jugeLogin(name, password));
        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/realogin")
    public String realogin(String name, String password){
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(name, password);
            token.setRememberMe(true);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
            return "/404";
        }
        return "redirect:/operate/qx";
    }

    @RequiresPermissions({"user","add"})
    @RequestMapping(value = "/operate/add")
    public String add(){
        try{
        }catch (UnauthorizedException e){
            e.printStackTrace();
            return "redirect:/403";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "/pass";
    }
    @RequiresPermissions({"user","del"})
    @RequestMapping(value = "/operate/add2")
    public String del(){
        try{
        }catch (UnauthorizedException e){
            e.printStackTrace();
            return "redirect:/403";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "/pass";
    }

    @RequestMapping(value = "/operate/qx")
    @RequiresPermissions("user")
    public String qx(){
        return "/qx";
    }

    @RequestMapping(value = "/403")
    public String page403(){
        return "/403";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/info.html")
    public String getUserInfo(Model model){

        try{

            User curr_user = CurrentUser.getUser();
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("allscore", CurrentUser.getFinalScore(userLevel.getLevel()));
            model.addAttribute("fansNum", userFocusMapper.getFansNum(curr_user.getId()));
            model.addAttribute("flag", 1);

            return "/user/user_self_info";

        }catch (Exception e){
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/u6514{userId}/focus/subjects.html")
    public String getPbarFocus(@PathVariable("userId")Long userId, Model model){
        try{

            User curr_user = CurrentUser.getUser();
            if(curr_user != null){
                model.addAttribute("user", curr_user);
                if(String.valueOf(curr_user.getId()).equals(String.valueOf(userId))){
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                }else{
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
            }else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }

            model.addAttribute("flag", 2);

            return "/user/user_self_subjects";

        }catch (Exception e){
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/u6514{userId}/history/topic.html")
    public String historyTopic(@PathVariable("userId")Long userId, Model model){
        try{

            User curr_user = CurrentUser.getUser();
            if(curr_user != null){
                model.addAttribute("user", curr_user);
                if(String.valueOf(curr_user.getId()).equals(String.valueOf(userId))){
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                }else{
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
            }else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }
            model.addAttribute("flag", 4);

            return "/user/user_self_topics";

        }catch (Exception e){
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/user/focus/subjects")
    @ResponseBody
    public List<Pbar> getPbarFocus(Long userId, @RequestParam(value = "page", defaultValue = "0", required = true) int page){
        try{

            List<Pbar> result = this.pbarService.getAllPbarFocus(userId, page * 10);

            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/user/focus/users")
    @ResponseBody
    public List<User> getUserFocus(Long userId, @RequestParam(value = "page", defaultValue = "0", required = true) int page){
        try{

            Map<String, Object> params = new HashMap<>();
            params.put("page", page);
            params.put("userId", userId);

            List<User> result = this.userFocusMapper.getAllFocus(params);

            return result.size() == 0 ? null : result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/user/focus/fans")
    @ResponseBody
    public List<User> getUserFans(Long userId, @RequestParam(value = "page", defaultValue = "0", required = true) int page){
        try{



            Map<String, Object> params = new HashMap<>();
            params.put("page", page);
            params.put("userId", userId);

            List<User> result = this.userFocusMapper.getAllFans(params);

            return result.size() == 0 ? null : result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center")
    public String managerCenter(Model model){
        User curr_user = CurrentUser.getUser();
        if(curr_user == null){
            return "/404";
        }else{
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("allscore", CurrentUser.getFinalScore(userLevel.getLevel()));
            model.addAttribute("fansNum", userFocusMapper.getFansNum(curr_user.getId()));
            model.addAttribute("flag",1);
            return "/user/manage_center";
        }
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/edit")
    public String managerCenterEdit(Model model){
        User curr_user = CurrentUser.getUser();
        if(curr_user == null){
            return "/404";
        }else{
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("flag",2);
            return "/user/manage_center_edit";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/update")
    @ResponseBody
    public Map<String, Object> managerCenterUpdate(User user){
        Map<String, Object> map = new HashMap<>();
        try{
            user.setAvater("".equals(user.getAvater().trim()) ? null : user.getAvater());
            this.userService.update(user);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/pbar/focus")
    @ResponseBody
    public Map<String, Object> pbarFocus(@RequestParam(value = "pbarId", required = true)Long pbarId){
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        try{
            User user = CurrentUser.getUser();
            Map<String, Object> params = new HashMap<>();
            params.put("userId", user.getId());
            params.put("pbarId", pbarId);
            PbarFocus pbarFocus1 = this.pbarFocusMapper.getPbarFocusByPbarIdAndUserId(params);
            if(pbarFocus1 != null){
                result.put("success", false);
                result.put("msg", "您已经关注过本话题了~");
                return result;
            }
            PbarFocus pbarFocus = new PbarFocus();
            pbarFocus.setUserId(user.getId());
            pbarFocus.setPbarId(pbarId);
            pbarFocus.setCreateTime(new Date());
            this.pbarFocusMapper.save(pbarFocus);
            JmsUpdPbar jmsUpdPbar = new JmsUpdPbar(1, pbarId);
            this.pbarUpdQueueMessageSender.send(jmsUpdPbar);
        }catch (Exception e){
            result.put("success", false);
            result.put("msg", "非常抱歉，后端程序出现未知错误！请刷新页面后重新操作");
            e.printStackTrace();

        }
        return result;
    }
    
    @RequestMapping(value = "/user/pbar/isfocus")
    @ResponseBody
    public boolean isFocus(@RequestParam(value = "pbarId", required = true)Long pbarId){

        try{
            User user = CurrentUser.getUser();
            if(user == null){
                return false;
            }
            Map<String, Object> params = new HashMap<>();
            params.put("userId", user.getId());
            params.put("pbarId", pbarId);
            PbarFocus pbarFocus = this.pbarFocusMapper.getPbarFocusByPbarIdAndUserId(params);
            if(pbarFocus != null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return true;

        }
    }


    @RequestMapping(value = "/user/u6514{userId}/index.html")
    public String getUserDynamic(@PathVariable("userId")Long userId, Model model){
        String url = "/user/user_index";
        try{

            User curr_user = CurrentUser.getUser();
            if(curr_user != null){
                model.addAttribute("user", curr_user);
                if(String.valueOf(curr_user.getId()).equals(String.valueOf(userId))){
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                }else{
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
            }else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }
                UserLevel userLevel = this.userLevelService.getUserLevelByUserId(userId);
                model.addAttribute("level", userLevel);
                model.addAttribute("allscore", CurrentUser.getFinalScore(userLevel.getLevel()));
                model.addAttribute("fansNum", userFocusMapper.getFansNum(curr_user.getId()));
                model.addAttribute("flag", 1);

                return url;




        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping(value = "/user/all_small_manager")
    @ResponseBody
    public  Map<String, Object> getAllSmallManager(@RequestParam(value = "pbarId", required = true) Long pbarId){
        Map<String, Object> result = new HashMap<>();
        if(pbarId == null){
            return null;
        }
        User user = CurrentUser.getUser();
        if(user != null){
            Map<String, Object> params = new HashMap<>();
            params.put("pbarId", pbarId);
            params.put("userId", user.getId());
            Integer status = this.pbarManagerApplyMapper.isApply(params);
            result.put("status", status);
            UserLevel userLevel = this.userLevelService.getUserLevelByUserId(user.getId());
            result.put("userLevel", userLevel);
            result.put("allscore", CurrentUser.getFinalScore(userLevel.getLevel()));
        }
        List<TopicManager> list = this.userService.getAllSmallManagerByPbarId(pbarId);
        result.put("list", list);
        return result;

    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/center/gz")
    @ResponseBody
    public boolean gz(Long userId){

        try{
            if(userId == null){
                return false;
            }
            User curr = CurrentUser.getUser();
            UserFocus userFocus = new UserFocus();
            userFocus.setUserId1(userId);
            userFocus.setUserId2(curr.getId());
            this.userFocusMapper.save(userFocus);

            /** 发送系统消息*/
            JmsSystemMsg jmsSystemMsg = new JmsSystemMsg();
            jmsSystemMsg.setTitle("恭喜您，您有一位新粉丝，您的经验+10");
            jmsSystemMsg.setContent("<a href=\"/u6514"+userId+"/focus/subjects.html\" target=\"_blank\">点击查看详情</a>");
            jmsSystemMsg.setUserId(userId);
            this.systemMsgQueueMessageSender.send(jmsSystemMsg);

            /** 升级相关*/
            upgradeQueueMessageSender.send(new JmsUpgrade(userId, 15));//经验+15

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/center/isgz")
    @ResponseBody
    public boolean isgz(Long userId){
        try{
            if(userId == null){
                return false;
            }
            User curr = CurrentUser.getUser();
            UserFocus userFocus = new UserFocus();
            userFocus.setUserId1(userId);
            userFocus.setUserId2(curr.getId());
            UserFocus userFocus1 = this.userFocusMapper.isgz(userFocus);
            return userFocus1 == null ? false : true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
