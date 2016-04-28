package cn.kpic.juwin.controller.systemmsg;

import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.SystemMsg;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.mapper.MsgMapper;
import cn.kpic.juwin.service.MsgService;
import cn.kpic.juwin.service.SystemMsgService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/27.
 */
@Controller
public class SystemMsgController {

    private Logger logger = Logger.getLogger(SystemMsgController.class);

    @Autowired
    private SystemMsgService systemMsgService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private MsgMapper msgMapper;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/all/sysmsg")
    public String getAllSystemMsg(Model model){
        try{
            User curr_user = CurrentUser.getUser();
            model.addAttribute("user", curr_user);
            model.addAttribute("flag", 7);
            Msg msg = new Msg();
            msg.setUserId(curr_user.getId());
            msg.setType(1);
            model.addAttribute("num", msgMapper.notReadByType(msg));
            this.msgService.clear(msg);
            return "/user/all_sys_msg";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("enter user manager all system msg error !");
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/self/sysmsgs")
    @ResponseBody
    public List<SystemMsg> getAllSelfMsg(@RequestParam(value = "page", required = true, defaultValue = "0")Integer page){
        try{
            User user = CurrentUser.getUser();
            return this.systemMsgService.getAllSelfSystemMsg(user.getId(), page * 10);
        }catch (Exception e){
            return null;
        }
    }

}
