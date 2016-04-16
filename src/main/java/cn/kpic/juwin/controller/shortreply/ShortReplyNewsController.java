package cn.kpic.juwin.controller.shortreply;

import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.TopicOrReplyInfoVo;
import cn.kpic.juwin.mapper.ReplyPostMapper;
import cn.kpic.juwin.mapper.TopicPostMapper;
import cn.kpic.juwin.service.MsgService;
import cn.kpic.juwin.service.ShortReplyService;
import cn.kpic.juwin.utils.CurrentUser;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/6.
 */
@Controller
public class ShortReplyNewsController {

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Autowired
    private ReplyPostMapper replyPostMapper;

    @Autowired
    private ShortReplyService shortReplyService;

    @Autowired
    private MsgService msgService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/ckxq/info")
    @ResponseBody
    public Map<String, Object> getTopicAndReply(@RequestParam(value = "replyId")Long replyId){
        TopicOrReplyInfoVo topicOrReplyInfoVo1 = replyPostMapper.getByIdOfNews(replyId);
        TopicOrReplyInfoVo topicOrReplyInfoVo2 = new TopicOrReplyInfoVo();
        if(topicOrReplyInfoVo1 != null){
            topicOrReplyInfoVo2 = topicPostMapper.getByPbarIdOfNews(topicOrReplyInfoVo1.getTopicId());
        }

        Map<String, Object> result = new HashMap<>();

        result.put("topic", topicOrReplyInfoVo2);
        result.put("reply", topicOrReplyInfoVo1);
        result.put("topicId", topicOrReplyInfoVo1.getTopicId());

        return result;

    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/ckxq/short/reply")
    @ResponseBody
    public List<TopicOrReplyInfoVo> getShortContentByReplyId(@RequestParam(value = "replyId")Long replyId,
                                                             @RequestParam(value = "page", defaultValue = "0",required = false)int page){
        return this.shortReplyService.getShortByReplyIdOfNews(replyId, page * 10);
    }

}
