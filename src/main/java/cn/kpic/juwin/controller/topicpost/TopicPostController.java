package cn.kpic.juwin.controller.topicpost;

import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.service.TopicPostService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
@Controller
public class TopicPostController {

    @Autowired
    private TopicPostService topicPostService;

    @RequestMapping(value = "/tuan/all")
    @ResponseBody
    public List<PbarHomeTopicPost> getAllTopicPost(@RequestParam(value = "pbarId", required = true)Long pbarId,
                                                   @RequestParam(value = "page", required = true, defaultValue = "0")int page,
                                                   @RequestParam(value = "isBlog", required = false) String isBlog,
                                                   @RequestParam(value = "isBoutique", required = false) String isBoutique){
        try{
            List<PbarHomeTopicPost> list;
            if(StringUtils.isBlank(isBlog) && StringUtils.isBlank(isBoutique)){
                list = topicPostService.getAllTopicByPbarId(pbarId, (page*10));
            }else if(!StringUtils.isBlank(isBlog)){
                list = topicPostService.getAllTopicOrBlogByPbarId(pbarId, (page*10), Integer.parseInt(isBlog));
            }else if(!StringUtils.isBlank(isBoutique)){
                list = topicPostService.getAllJpTopicByPbarId(pbarId, (page*10), Integer.parseInt(isBoutique));
            }else{
                list = null;
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/tuan/post/save")
    @ResponseBody
    public TopicPost saveTopicPost(String title, String content, String shortContent,
                                   @RequestParam(value = "pbarId", required = true)Long pbarId,
                                   @RequestParam(value = "userId", required = true)Long userId){
        try{
            if(StringUtils.isBlank(title) || StringUtils.isBlank(content)){
                return null;
            }
            return topicPostService.addTopicPost(title, content, StringDeal.getText(shortContent), pbarId, userId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/management/center/topics")
    public String getAllSelfPost(Model model){
        User curr_user = CurrentUser.getUser();
        if(curr_user != null){
            model.addAttribute("user", curr_user);
            model.addAttribute("flag", 3);
        }else{
            return "/404";
        }
        return "/user/manage_center_topic";
    }

    @RequestMapping(value = "/user/management/center/topics2")
    @ResponseBody
    public List<PbarHomeTopicPost> getAllSelfPost2(Long userId, @RequestParam(value = "isBlog", required = true, defaultValue = "0")int isBlog,
                                                   @RequestParam(value = "page", required = true, defaultValue = "0")int page,
                                                   @RequestParam(value = "orderBy", required = true, defaultValue = "ttp.updateTime DESC")String orderBy){

        List<PbarHomeTopicPost> list = this.topicPostService.getAllTopicPostByUid(userId, (page*10), isBlog, orderBy);
        return list;

    }

}
