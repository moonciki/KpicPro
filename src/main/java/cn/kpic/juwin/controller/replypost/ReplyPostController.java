package cn.kpic.juwin.controller.replypost;

import cn.kpic.juwin.domain.ReplyPost;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.PbarIndexVo;
import cn.kpic.juwin.domain.vo.ReplyPostList;
import cn.kpic.juwin.domain.vo.SelfReply;
import cn.kpic.juwin.domain.vo.TopicPostMsg;
import cn.kpic.juwin.service.PbarService;
import cn.kpic.juwin.service.ReplyPostService;
import cn.kpic.juwin.service.TopicPostService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/16.
 */
@Controller
public class ReplyPostController {

    private static Logger logger = Logger.getLogger(ReplyPostController.class);

    @Autowired
    private TopicPostService topicPostService;

    @Autowired
    private ReplyPostService replyPostService;

    @Autowired
    private PbarService pbarService;

    @RequestMapping(value = "/post/reply/tp5416{uuId}")
    public String getAllReplyPost(@PathVariable("uuId") Long uuId, Model model){
        TopicPostMsg topicPostMsg = this.topicPostService.getByUid(uuId);
        PbarIndexVo pbarIndexVo = this.pbarService.getPbarIndex(topicPostMsg.getPbarId());
        if(pbarIndexVo == null){
            return "/404";
        }

        model.addAttribute("user", CurrentUser.getUser());
        model.addAttribute("postMSg", topicPostMsg);
        model.addAttribute("pbar", pbarIndexVo);
        if(topicPostMsg.getIsBlog() != 0){
            return "/reply_index_blog";
        }
        return "/reply/reply_index";
    }

    @RequestMapping(value = "/post/reply/at5416{uuId}")
    public String getAllBlogPost(@PathVariable("uuId") Long uuId, Model model){
        TopicPostMsg topicPostMsg = this.topicPostService.getByUid(uuId);
        PbarIndexVo pbarIndexVo = this.pbarService.getPbarIndex(topicPostMsg.getPbarId());
        if(pbarIndexVo == null){
            return "/404";
        }
        model.addAttribute("user", CurrentUser.getUser());
        model.addAttribute("postMSg", topicPostMsg);
        model.addAttribute("pbar", pbarIndexVo);
        if(topicPostMsg.getIsBlog() != 1){
            return "/reply_index";
        }
        return "/reply/reply_index_blog";
    }

    @RequestMapping(value = "/tuan/post/getallreply")
    @ResponseBody
    public List<ReplyPostList> getAllReply(Long topicId, int page){
        List<ReplyPostList> result = replyPostService.getAllReplyByTopicId(topicId, page * 10);
        return result;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/tuan/reply/save")
    @ResponseBody
    public ReplyPost getReply(ReplyPost replyPost, Long pbarId, Long topicUserId){
        try{
            replyPost = replyPostService.saveReplyPost(replyPost, pbarId, topicUserId);
        }catch (Exception e){
            logger.error("save reply post error ! topicId = " + replyPost.getTopicId() + ", userId = " + replyPost.getUserId());
            e.printStackTrace();
        }
        return replyPost;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/replys")
    public String getAllSelfReply(Model model){
        User curr_user = CurrentUser.getUser();
        if(curr_user != null){
            model.addAttribute("user", curr_user);
            model.addAttribute("flag", 4);
        }else{
            return "/404";
        }
        return "/user/manage_center_reply";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/reply2")
    @ResponseBody
    public List<SelfReply> getAllSelfPost2(@RequestParam(value = "page", required = true, defaultValue = "0")int page,
                                                   @RequestParam(value = "orderBy", required = true, defaultValue = "trp.createTime DESC")String orderBy){
        User curr_user = CurrentUser.getUser();
        if(curr_user != null){
            List<SelfReply> list = this.replyPostService.getAllSelfReply(curr_user.getId(), (page * 10), orderBy);
            return list;
        }else{
            return null;
        }
    }

}
