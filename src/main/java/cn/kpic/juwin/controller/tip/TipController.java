package cn.kpic.juwin.controller.tip;

import cn.kpic.juwin.domain.ReplyTip;
import cn.kpic.juwin.domain.ShortTip;
import cn.kpic.juwin.domain.TopicTip;
import cn.kpic.juwin.domain.vo.ReplyTips;
import cn.kpic.juwin.domain.vo.TopicTips;
import cn.kpic.juwin.mapper.ReplyTipMapper;
import cn.kpic.juwin.mapper.ShortTipMapper;
import cn.kpic.juwin.mapper.TopicTipMapper;
import cn.kpic.juwin.service.ReplyTipService;
import cn.kpic.juwin.service.ShortTipService;
import cn.kpic.juwin.service.TopicPostService;
import cn.kpic.juwin.service.TopicTipService;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private TopicTipMapper topicTipMapper;

    @Autowired
    private ReplyTipMapper replyTipMapper;

    @Autowired
    private ShortTipMapper shortTipMapper;

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

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/tip", method = RequestMethod.POST)
    @ResponseBody
    public List<TopicTips> getAllTopicTips(Long pbarId, @RequestParam(value = "page", defaultValue = "0", required = true)Integer page){
        if(pbarId == null || page == null){
            return null;
        }
        Map params = new HashMap();
        params.put("pbarId", pbarId);
        params.put("page", page * 10);
        List<TopicTips> result = this.topicTipMapper.getAllTopicTips(params);
        return result.size() == 0 ? null : result;
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/deltopic", method = RequestMethod.POST)
    @ResponseBody
    public boolean delTopic(Long topicId){
        if(topicId == null){
            return false;
        }else{
            try{
                this.topicTipService.delTopic(topicId);
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/igntopic", method = RequestMethod.POST)
    @ResponseBody
    public boolean ignTopic(Long topicId){
        if(topicId == null){
            return false;
        }else{
            try{
                this.topicTipMapper.delAllTips(topicId);
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/reply/manager/tip")
    @ResponseBody
    public List<ReplyTips> getAllReplyTips(Long pbarId, @RequestParam(value = "page", defaultValue = "0", required = true)Integer page){
        if(pbarId == null || page == null){
            return null;
        }
        Map params = new HashMap();
        params.put("pbarId", pbarId);
        params.put("page", page * 10);
        List<ReplyTips> result = this.replyTipMapper.getAllReplyTips(params);
        return result.size() == 0 ? null : result;
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/reply/manager/del", method = RequestMethod.POST)
    @ResponseBody
    public boolean delReply(Long replyId){
        if(replyId == null){
            return false;
        }else{
            try{
                this.replyTipService.delReply(replyId);
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/reply/manager/ign", method = RequestMethod.POST)
    @ResponseBody
    public boolean ignReply(Long replyId){
        if(replyId == null){
            return false;
        }else{
            try{
                this.replyTipMapper.delAllReplyTips(replyId);
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }
}
