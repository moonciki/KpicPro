package cn.kpic.juwin.controller.tip;

import cn.kpic.juwin.domain.ReplyTip;
import cn.kpic.juwin.domain.ShortTip;
import cn.kpic.juwin.domain.TopicTip;
import cn.kpic.juwin.service.ReplyTipService;
import cn.kpic.juwin.service.ShortTipService;
import cn.kpic.juwin.service.TopicTipService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
@Controller
public class TipController {

    private Logger logger = Logger.getLogger(TipController.class);

    @Autowired
    private TopicTipService topicTipService;

    @Autowired
    private ReplyTipService replyTipService;

    @Autowired
    private ShortTipService shortTipService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/topic/tip")
    @ResponseBody
    public Map<String, Object> saveTopicTip(Long id, String title, String msg, Long pbarId){
        Map<String, Object> result = new HashMap<>();
        try{
            TopicTip topicTip = new TopicTip();
            topicTip.setCreateTime(new Date());
            topicTip.setPbarId(pbarId);
            topicTip.setMsg(msg);
            topicTip.setTitle(title);
            topicTip.setTopicId(id);
            topicTip.setUserId(CurrentUser.getUser().getId());
            this.topicTipService.save(topicTip);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("save topic_tip error topicId = "+id);
            result.put("success", false);
        }
        return result;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/reply/tip")
    @ResponseBody
    public Map<String, Object> saveReplyTip(Long id, String title, String msg, Long pbarId){
        Map<String, Object> result = new HashMap<>();
        try{
            ReplyTip replyTip = new ReplyTip();
            replyTip.setCreateTime(new Date());
            replyTip.setPbarId(pbarId);
            replyTip.setMsg(msg);
            replyTip.setTitle(title);
            replyTip.setReplyId(id);
            replyTip.setUserId(CurrentUser.getUser().getId());
            this.replyTipService.save(replyTip);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("save reply_tip error replyId = "+id);
            result.put("success", false);
        }
        return result;
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/short/tip")
    @ResponseBody
    public Map<String, Object> saveShortTip(Long id, String title, String msg, Long pbarId){
        Map<String, Object> result = new HashMap<>();
        try{
            ShortTip shortTip = new ShortTip();
            shortTip.setCreateTime(new Date());
            shortTip.setPbarId(pbarId);
            shortTip.setMsg(msg);
            shortTip.setTitle(title);
            shortTip.setShortId(id);
            shortTip.setUserId(CurrentUser.getUser().getId());
            this.shortTipService.save(shortTip);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("save short_tip error shortId = "+id);
            result.put("success", false);
        }
        return result;
    }

}
