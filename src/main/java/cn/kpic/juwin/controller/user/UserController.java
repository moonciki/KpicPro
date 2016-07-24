package cn.kpic.juwin.controller.user;

import cn.kpic.juwin.controller.code.CodeController;
import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.*;
import cn.kpic.juwin.jms.sender.PbarUpdQueueMessageSender;
import cn.kpic.juwin.jms.sender.SystemMsgQueueMessageSender;
import cn.kpic.juwin.jms.sender.UpgradeQueueMessageSender;
import cn.kpic.juwin.mapper.PbarFocusMapper;
import cn.kpic.juwin.mapper.PbarManagerApplyMapper;
import cn.kpic.juwin.mapper.UserFocusMapper;
import cn.kpic.juwin.service.PbarService;
import cn.kpic.juwin.service.UserIntegrityService;
import cn.kpic.juwin.service.UserLevelService;
import cn.kpic.juwin.service.UserService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
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
import javax.servlet.http.HttpSession;
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

    @Autowired
    private UserIntegrityService userIntegrityService;

    private Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/user/getuser")
    @ResponseBody
    public User getUserById(Model model) {
        User user = new User();
        try {
            user = userService.getUserById(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping(value = "/user/all")
    @ResponseBody
    public List<User> getAllUser(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "page", defaultValue = "1", required = false) int page, Model model) {
        List<User> list = new ArrayList<User>();
        try {
            list = userService.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @RequestMapping(value = "/list")
    public String getUserList(Model model) {
        return "/list";
    }

    @RequestMapping(value = "/qxbg")
    public String qxbg(Model model) {
        return "/qxbg";
    }

    @RequestMapping(value = "/sw")
    @ResponseBody
    public String sw(Model model) {
        User user = new User();
        user.setName("ddddd");
        user.setAge(18);
        try {
            this.userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sd";
    }

    @RequestMapping(value = "/index")
    public String login(Model model) {
        User user = CurrentUser.getUser();
        model.addAttribute("user",user);
        if (user != null) {
            return "redirect:/";
        }
        return "/index";
    }

    @RequestMapping(value = "/register")
    public String register(Model model) {
        User user = CurrentUser.getUser();
        model.addAttribute("user",user);
        if (user != null) {
            return "redirect:/";
        }
        return "/register";
    }

    @RequestMapping(value = "/login/code")
    @ResponseBody
    public Map login() {
        Map result = new HashMap();
        try{
            result.put("num",(new Random().nextInt(9999999)%(9999999-1000000+1) + 1000000));
            result.put("success", true);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("num",null);
            result.put("success", false);
            return result;
        }
    }

    @RequestMapping(value = "/register/pic")
    @ResponseBody
    public Map pic(){
        Map result = new HashMap();
        try{
            result.put("pic", UUID.randomUUID().toString().replace("-", ""));
            result.put("success", true);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("pic", null);
            result.put("success", false);
            return result;
        }
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Boolean logout() {
        try{
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                subject.logout();
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping(value = "/logout2")
    @ResponseBody
    public Map<String, Object> logout2() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                subject.logout();
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/jugelogin")
    @ResponseBody
    public Map<String, Object> jugeLogin(Long num, String pwd, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("success", userService.jugeLogin(num, pwd));
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/realogin")
    @ResponseBody
    public int realogin(HttpSession httpSession, Long num, String pwd, String code) {
        try {
            String real_code = (String)httpSession.getAttribute(CodeController.CAPTCHA_SESSION_ATTR_NAME);
            if(!real_code.equalsIgnoreCase(code)){
                return 3;
            }
            httpSession.invalidate();
            UsernamePasswordToken token = new UsernamePasswordToken(num+"", pwd);
            token.setRememberMe(true);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    @RequiresPermissions({"user", "add"})
    @RequestMapping(value = "/operate/add")
    public String add() {
        try {
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            return "redirect:/403";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pass";
    }

    @RequiresPermissions({"user", "del"})
    @RequestMapping(value = "/operate/add2")
    public String del() {
        try {
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            return "redirect:/403";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pass";
    }

    @RequestMapping(value = "/operate/qx")
    @RequiresPermissions("user")
    public String qx() {
        return "/qx";
    }

    @RequestMapping(value = "/403")
    public String page403() {
        return "/403";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/info.html")
    public String getUserInfo(Model model) {

        try {

            User curr_user = CurrentUser.getUser();
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("allscore", CurrentUser.getFinalScore(userLevel.getLevel()));
            model.addAttribute("fansNum", userFocusMapper.getFansNum(curr_user.getId()));
            model.addAttribute("flag", 1);

            return "/user/user_self_info";

        } catch (Exception e) {
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/u6514{userId}/focus/subjects.html")
    public String getPbarFocus(@PathVariable("userId") Long userId, Model model) {
        try {

            User curr_user = CurrentUser.getUser();
            if (curr_user != null) {
                model.addAttribute("user", curr_user);
                if (String.valueOf(curr_user.getId()).equals(String.valueOf(userId))) {
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                } else {
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
                model.addAttribute("jc", this.userIntegrityService.getByUserId(userId));
            } else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }

            model.addAttribute("flag", 2);

            return "/user/user_self_subjects";

        } catch (Exception e) {
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/u6514{userId}/history/topic.html")
    public String historyTopic(@PathVariable("userId") Long userId, Model model) {
        try {

            User curr_user = CurrentUser.getUser();
            if (curr_user != null) {
                model.addAttribute("user", curr_user);
                if (String.valueOf(curr_user.getId()).equals(String.valueOf(userId))) {
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                } else {
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
                model.addAttribute("jc", this.userIntegrityService.getByUserId(userId));
            } else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }
            model.addAttribute("flag", 4);

            return "/user/user_self_topics";

        } catch (Exception e) {
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/u6514{userId}/history/article.html")
    public String historyArticle(@PathVariable("userId") Long userId, Model model) {
        try {

            User curr_user = CurrentUser.getUser();
            if (curr_user != null) {
                model.addAttribute("user", curr_user);
                if (String.valueOf(curr_user.getId()).equals(String.valueOf(userId))) {
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                } else {
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
                model.addAttribute("jc", this.userIntegrityService.getByUserId(userId));
            } else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }
            model.addAttribute("flag", 5);

            return "/user/user_self_articles";

        } catch (Exception e) {
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/u6514{userId}/history/album.html")
    public String historyAlbum(@PathVariable("userId") Long userId, Model model) {
        try {

            User curr_user = CurrentUser.getUser();
            if (curr_user != null) {
                model.addAttribute("user", curr_user);
                if (String.valueOf(curr_user.getId()).equals(String.valueOf(userId))) {
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                } else {
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
                model.addAttribute("jc", this.userIntegrityService.getByUserId(userId));
            } else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }
            model.addAttribute("flag", 6);

            return "/user/user_self_albums";

        } catch (Exception e) {
            e.printStackTrace();
            return "/404";
        }
    }

    @RequestMapping(value = "/user/focus/subjects")
    @ResponseBody
    public List<Pbar> getPbarFocus(Long userId, @RequestParam(value = "page", defaultValue = "0", required = true) int page) {
        try {
            List<Pbar> result = this.pbarService.getAllPbarFocus(userId, page * 10);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/user/focus/users")
    @ResponseBody
    public List<User> getUserFocus(Long userId, @RequestParam(value = "page", defaultValue = "0", required = true) int page) {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("page", page);
            params.put("userId", userId);

            List<User> result = this.userFocusMapper.getAllFocus(params);

            return result.size() == 0 ? null : result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/user/focus/fans")
    @ResponseBody
    public List<User> getUserFans(Long userId, @RequestParam(value = "page", defaultValue = "0", required = true) int page) {
        try {


            Map<String, Object> params = new HashMap<String, Object>();
            params.put("page", page);
            params.put("userId", userId);

            List<User> result = this.userFocusMapper.getAllFans(params);

            return result.size() == 0 ? null : result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center")
    public String managerCenter(Model model) {
        User curr_user = CurrentUser.getUser();
        if (curr_user == null) {
            return "/404";
        } else {
            UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
            model.addAttribute("user", curr_user);
            model.addAttribute("level", userLevel);
            model.addAttribute("allscore", CurrentUser.getFinalScore(userLevel.getLevel()));
            model.addAttribute("fansNum", userFocusMapper.getFansNum(curr_user.getId()));
            model.addAttribute("flag", 1);
            return "/user/manage_center";
        }
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/edit")
    public String managerCenterEdit(Model model) {
        try{
            User curr_user = CurrentUser.getUser();
            if (curr_user == null) {
                return "/404";
            } else {
                UserLevel userLevel = userLevelService.getUserLevelByUserId(curr_user.getId());
                model.addAttribute("user", this.userService.getUserById(curr_user.getId()));
                model.addAttribute("level", userLevel);
                model.addAttribute("flag", 2);
                return "/user/manage_center_edit";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/update")
    @ResponseBody
    public Map<String, Object> managerCenterUpdate(User user) {
        user.setName(StringDeal.getText(user.getName()));
        user.setBirth(StringDeal.getText(user.getBirth()));
        user.setAddress(StringDeal.getText(user.getAddress()));
        user.setTag(StringDeal.getText(user.getTag()));
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            user.setAvater("".equals(user.getAvater()) ? null : user.getAvater());
            this.userService.update(user);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/pbar/focus")
    @ResponseBody
    public Map<String, Object> pbarFocus(@RequestParam(value = "pbarId", required = true) Long pbarId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        try {
            User user = CurrentUser.getUser();
            PbarFocus pbarFocus = new PbarFocus();
            pbarFocus.setUserId(user.getId());
            pbarFocus.setPbarId(pbarId);
            pbarFocus.setCreateTime(new Date());
            this.pbarFocusMapper.save(pbarFocus);
            JmsUpdPbar jmsUpdPbar = new JmsUpdPbar(1, pbarId, null);
            this.pbarUpdQueueMessageSender.send(jmsUpdPbar);
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "非常抱歉，后端程序出现未知错误！请刷新页面后重新操作");
            e.printStackTrace();

        }
        return result;
    }

    @RequestMapping(value = "/user/pbar/isfocus")
    @ResponseBody
    public boolean isFocus(@RequestParam(value = "pbarId", required = true) Long pbarId) {

        try {
            User user = CurrentUser.getUser();
            if (user == null) {
                return false;
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userId", user.getId());
            params.put("pbarId", pbarId);
            PbarFocus pbarFocus = this.pbarFocusMapper.getPbarFocusByPbarIdAndUserId(params);
            if (pbarFocus != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;

        }
    }


    @RequestMapping(value = "/user/u6514{userId}/index.html")
    public String getUserDynamic(@PathVariable("userId") Long userId, Model model) {
        String url = "/user/user_index";
        try {

            User curr_user = CurrentUser.getUser();
            if (curr_user != null) {
                model.addAttribute("user", curr_user);
                if (String.valueOf(curr_user.getId()).equals(String.valueOf(userId))) {
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", true);
                } else {
                    curr_user = this.userService.getUserById(userId);
                    model.addAttribute("user2", curr_user);
                    model.addAttribute("is_login", false);
                }
            } else {
                model.addAttribute("is_login", false);
                curr_user = this.userService.getUserById(userId);
                model.addAttribute("user2", curr_user);
            }
            UserLevel userLevel = this.userLevelService.getUserLevelByUserId(userId);
            model.addAttribute("level", userLevel);
            model.addAttribute("jc", this.userIntegrityService.getByUserId(userId));
            model.addAttribute("allscore", CurrentUser.getFinalScore(userLevel.getLevel()));
            model.addAttribute("fansNum", userFocusMapper.getFansNum(curr_user.getId()));
            model.addAttribute("flag", 1);

            return url;


        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping(value = "/user/all_small_manager")
    @ResponseBody
    public Map<String, Object> getAllSmallManager(@RequestParam(value = "pbarId", required = true) Long pbarId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (pbarId == null) {
            return null;
        }
        User user = CurrentUser.getUser();
        if (user != null) {
            Map<String, Object> params = new HashMap<String, Object>();
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
    public boolean gz(Long userId) {

        try {
            if (userId == null) {
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
            jmsSystemMsg.setContent("<a href=\"/u6514" + userId + "/focus/subjects.html\" target=\"_blank\">点击查看详情</a>");
            jmsSystemMsg.setUserId(userId);
            this.systemMsgQueueMessageSender.send(jmsSystemMsg);

            /** 升级相关*/
            upgradeQueueMessageSender.send(new JmsUpgrade(userId, 15));//经验+15

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/center/isgz")
    @ResponseBody
    public boolean isgz(Long userId) {
        try {
            if (userId == null) {
                return false;
            }
            User curr = CurrentUser.getUser();
            UserFocus userFocus = new UserFocus();
            userFocus.setUserId1(userId);
            userFocus.setUserId2(curr.getId());
            UserFocus userFocus1 = this.userFocusMapper.isgz(userFocus);
            return userFocus1 == null ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/qx/focus_p", method = RequestMethod.POST)
    @ResponseBody
    public Boolean managerCenterFocusPbar(Long pbarId) {
        User curr_user = CurrentUser.getUser();
        Boolean flag = false;
        try {
            if (pbarId != null) {
                this.pbarService.delFocusService(curr_user.getId(), pbarId);
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            logger.error("del pbar_focus error ! pbarId = " + pbarId + "   userId = " + curr_user.getId());
            e.printStackTrace();
        }
        return flag;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/qx/focus_u", method = RequestMethod.POST)
    @ResponseBody
    public Boolean managerCenterFocusUser(Long userId) {
        User curr_user = CurrentUser.getUser();
        Boolean flag = false;
        try {
            if (userId != null) {
                Map params = new HashMap();
                params.put("userId1", userId);
                params.put("userId2", curr_user.getId());
                this.userFocusMapper.delUserFocus(params);
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            logger.error("del user_focus error ! userId1 = " + userId + "   userId2 = " + curr_user.getId());
            e.printStackTrace();
        }
        return flag;
    }

    @RequestMapping(value = "/user/pbar/manager", method = RequestMethod.POST)
    @ResponseBody
    public User getPbarManager(Long userId) {
        try {
            if (userId == null) {
                return null;
            }
            User user = this.userService.getUserById(userId);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/user/pbar/users")
    @ResponseBody
    public List<UserVo> getAllPbarUsers(Long pbarId, Integer page) {
        if (pbarId == null || page == null) {
            return null;
        }
        try {
            List<UserVo> result = this.userService.getAllPbarUsers(pbarId, page * 10);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

