package cn.kpic.juwin.controller.others;

import cn.kpic.juwin.domain.FeedBack;
import cn.kpic.juwin.mapper.FeedBackMapper;
import cn.kpic.juwin.utils.StringDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
@Controller
public class OthersController {

    @Autowired
    private FeedBackMapper feedBackMapper;

    @RequestMapping(value = "/feedback")
    public String fdb(){
        return "/feedback";
    }

    @RequestMapping(value = "/video_info")
    public String vs(){
        return "/video_share";
    }

    @RequestMapping(value = "/feedback/save")
    @ResponseBody
    public Boolean save(FeedBack feedBack){
        try{
            feedBack.setMsg(StringDeal.getText(feedBack.getMsg()));
            feedBack.setUrl(StringDeal.getText(feedBack.getUrl()));
            feedBack.setCreateTime(new Date());
            this.feedBackMapper.save(feedBack);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/about_us")
    public String aboutUs(){
        return "/about_us";
    }

}
