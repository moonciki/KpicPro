package cn.kpic.juwin.controller.shortreply;

import cn.kpic.juwin.domain.ReplyPost;
import cn.kpic.juwin.domain.ShortReply;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.ShortReplyVo;
import cn.kpic.juwin.domain.vo.ShortReplyVo2;
import cn.kpic.juwin.service.ReplyPostService;
import cn.kpic.juwin.service.ShortReplyService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/3 0003.
 */
@Controller
public class ShortReplyController {

    private Logger logger = Logger.getLogger(ShortReplyController.class);

    @Autowired
    private ShortReplyService shortReplyService;

    @Autowired
    private ReplyPostService replyPostService;

    @RequestMapping(value = "/all/short/reply")
    @ResponseBody
    public Map<String, Object> getAllShortByReplyId(Long replyId, int page){
        Map<String, Object> result = new HashMap<>();
        try{
            List<ShortReplyVo> list = shortReplyService.getShortByReplyId(replyId, page);
            ReplyPost replyPost = this.replyPostService.getShortTextById(replyId);
            result.put("success", true);
            result.put("shortText", replyPost.getShortText());
            result.put("reply_userId", replyPost.getUserId());
            result.put("data", list);
            return result;
        }catch (Exception e){
            logger.error("get all short by replyId error ! replyId = "+replyId);
            e.printStackTrace();
            result.put("success", false);
            return result;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/short/save")
    @ResponseBody
    public ShortReply save(ShortReply shortReply, Long pbarId){
        try{

            User curr_user = CurrentUser.getUser();
            if(curr_user == null){
                throw new RuntimeException();
            }

            shortReply.setContent(this.At(shortReply.getContent(), shortReply.getReplyUserId()));
            /**
             * 给replyUserId发消息
             */
            shortReply.setUserId(curr_user.getId());
            shortReply.setCreateTime(new Date());

            this.shortReplyService.insert(shortReply, pbarId);

            return shortReply;
        }catch (Exception e){
            logger.error("save short reply error ! replyId = "+shortReply.getReplyId());
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/all/shortreplys")
    @ResponseBody
    public List<ShortReplyVo2> getAllSelfShortReply(@RequestParam(value = "page", defaultValue = "0",required = false)int page){
        User curr_user = CurrentUser.getUser();
        List<ShortReplyVo2> replyVo2s = this.shortReplyService.getShortByUserId(curr_user.getId(), page * 10);
        return replyVo2s;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/shortreplys")
    public String getAllSelfShortReply(Model model){
        User curr_user = CurrentUser.getUser();
        model.addAttribute("user", curr_user);
        model.addAttribute("flag", 5);
        return "/user/manager_center_short_reply";
    }

    public static String At(String content, Long userId){
        if(content.contains("@")) {
            String result = StringDeal.getText(content).trim();
            String name = result.substring(result.indexOf('@'), result.indexOf(':'));
            String newName = "<a href=\"/user/" + userId + "/index.html\" target=\"_blank\">" + name + "</a>";
            if(name.length() > 15){
                newName = "<a href=\"/user/" + userId + "/index.html\" target=\"_blank\">id为"+userId+"的越界用户名</a>";
            }
            return result.replace(name, newName);
        }else{
            return content;
        }
    }

}
