package cn.kpic.juwin.controller.video;

import cn.kpic.juwin.constant.KpicConstant;
import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.DamakuVo;
import cn.kpic.juwin.domain.vo.MvCount;
import cn.kpic.juwin.domain.vo.MvVo;
import cn.kpic.juwin.domain.vo.SendListVo;
import cn.kpic.juwin.jms.sender.DamakuSaveSender;
import cn.kpic.juwin.jms.sender.MvCountSender;
import cn.kpic.juwin.mapper.MvMapper;
import cn.kpic.juwin.mapper.SendListMapper;
import cn.kpic.juwin.service.DamakuService;
import cn.kpic.juwin.service.MvCountService;
import cn.kpic.juwin.service.MvService;
import cn.kpic.juwin.service.SendListService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import com.qiniu.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
@Controller
public class VideoController {

    @Autowired
    private DamakuService damakuService;

    @Autowired
    private MvService mvService;

    @Autowired
    private DamakuSaveSender damakuSaveSender;

    @Autowired
    private MvCountSender mvCountSender;

    @Autowired
    private SendListMapper sendListMapper;

    @Autowired
    private MvCountService mvCountService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SendListService sendListService;

    @Autowired
    private MvMapper mvMapper;

    @RequestMapping(value = "/media/mv{mvId}")
    public String getMv(@PathVariable(value = "mvId") Integer mvId, Model model){
        if(mvId == null){
            return "404";
        }
        MvVo mv = this.mvService.getById(mvId);
        if(mv.getId() == null || mv.getSendlistId() == null){
            return "404";
        }
        User curr_user = CurrentUser.getUser();
        if(curr_user != null && curr_user.getId() != null){
            mv.setIsFav(this.mvService.isFav(curr_user.getId(), mv.getId()));
        }
        if(mv.getCover() == null){
            mv.setCover(KpicConstant.MV_DEFAULT_COVER);
        }
        model.addAttribute("mv", mv);
        model.addAttribute("user", curr_user);
        model.addAttribute("mvs", this.mvService.getMvsBySlId(mv.getSendlistId(), mvId));

        mvCountService.playPlus(mvId, 1);

        MvCount mvCount = new MvCount();
        mvCount.setMvId(mvId);
        mvCount.setCount(mvCountService.getPlay(mvId));
        mvCount.setType(MvCount.PLAY);
        this.mvCountSender.send(mvCount);

        return "/mv/mv_index";
    }

    /**
     * 弹幕获取接口
     * @return
     */
    @RequestMapping(value = "/video/damaku")
    @ResponseBody
    public List<DamakuVo> getDamaku(Integer mvId){
        MvVo mvVo = mvService.getById(mvId);
        List<DamakuVo> result = this.damakuService.getAllDamakuByMvId(mvId, mvVo.getDamakuPool());
        return result;
    }

    /**
     * 接收弹幕
     */
    @RequestMapping(value = "/damaku/save", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveDamaku(DamakuVo danmu){
        User user = CurrentUser.getUser();
        if(user != null && user.getId() != null){
            Damaku damaku = new Damaku();
            damaku.setTime(danmu.getTime());
            damaku.setText(danmu.getText());
            damaku.setSize(danmu.getSize());
            damaku.setPosition(danmu.getPosition());
            damaku.setColor(danmu.getColor());
            damaku.setCreateTime(new Date());
            damaku.setUserId(0L);
            damaku.setMvId(danmu.getMvId());

            this.damakuSaveSender.send(damaku);

            mvCountService.damakuPlus(danmu.getMvId(), 1);
            MvCount mvCount = new MvCount();
            mvCount.setMvId(danmu.getMvId());
            mvCount.setCount(mvCountService.getDamaku(danmu.getMvId()));
            mvCount.setType(MvCount.DANMU);
            this.mvCountSender.send(mvCount);
        }else{
            return false;
        }
        return true;
    }

    /**
     * 放送表详情页
     */
    @RequestMapping(value = "/send/list{listId}")
    public String sendList(@PathVariable(value = "listId")Integer listId, Model model){
        SendListVo sendList = sendListMapper.getSendListInfo(listId);
        if(sendList == null){
            return "/404";
        }
        List<Mv> mvs = this.sendListMapper.getAllMvsBySlId(listId);
        User curr_user = CurrentUser.getUser();
        model.addAttribute("user", curr_user);
        model.addAttribute("sendList", sendList);
        model.addAttribute("mvs", mvs);
        return "/mv/send_list";
    }

    /**
     * 收藏
     */
    @RequestMapping(value = "/video/saveSc", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveSc(Integer mvId){
        Map<String, Object> result = new HashMap();
        try{
            User user = CurrentUser.getUser();
            if(user != null && user.getId() != null){
                this.mvService.saveUserMv(mvId, user.getId());
                this.redisTemplate.delete(String.format(RedisCacheKey.ISFAVMV, user.getId(), mvId));
                result.put("success", true);
                result.put("msg", "操作成功！");

                mvCountService.favPlus(mvId, 1);
                MvCount mvCount = new MvCount();
                mvCount.setMvId(mvId);
                mvCount.setCount(1);
                mvCount.setType(MvCount.FAV);
                this.mvCountSender.send(mvCount);
            }else{
                result.put("success", false);
                result.put("msg", "账号未登录！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "执行过程中出现迷之错误！");
        }
        return result;
    }

    /**
     * 跳转放送表创建页
     */
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/send/list_add", method = RequestMethod.GET)
    public String sendListAdd(Model model){
        try {
            User user = CurrentUser.getUser();
            model.addAttribute("user", user);
            return "/mv/add_send_list";
        }catch (Exception e){
            return "404";
        }
    }

    /**
     * 持久化放送单
     */
    @RequestMapping(value = "/send/list_save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveSendList(String title, String decri, String cover){
        Map<String, Object> result = new HashMap();
        try{
            if(title != null && title.length() <= 25 && decri != null && decri.length() <= 450){
                User user = CurrentUser.getUser();
                if(user != null && user.getId() != null){
                    SendList sendList = new SendList();
                    sendList.setCover(StringUtils.isNullOrEmpty(cover) ? null : cover);
                    sendList.setCreateTime(new Date());
                    sendList.setDamaku(0);
                    sendList.setDecri(StringDeal.getText(decri));
                    sendList.setTitle(StringDeal.getText(title));
                    sendList.setDamakuPool(0);
                    sendList.setIsdel(0);
                    sendList.setPlay(0);
                    sendList.setFav(0);
                    sendList.setUserId(user.getId());
                    this.sendListService.save(sendList);
                    result.put("code", 200);
                    result.put("msg", "success");
                }else{
                    result.put("code", 403);
                    result.put("msg", "权限不足，必须登录！");
                }
            }else{
                result.put("code", 403);
                result.put("msg", "所填内容不符合要求！！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "fail");
        }

        return result;
    }

    /**
     * 我的放送单
     */
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/send/list_my", method = RequestMethod.GET)
    public String mySendList(Model model){
        try {
            User user = CurrentUser.getUser();
            if(user != null && user.getId() != null){
                model.addAttribute("user", user);
                model.addAttribute("sendList", this.sendListMapper.getMySendList(user.getId()));
                return "/mv/send_list_my";
            }else{
                return "404";
            }
        }catch (Exception e){
            return "404";
        }
    }


    /**
     * 跳转放送表创建页
     */
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/send/mv_add", method = RequestMethod.GET)
    public String sendMvAdd(Integer sl_id, Model model){
        try {
            if(sl_id == null){
                return "404";
            }
            SendListVo sendList = sendListMapper.getSendListInfo(sl_id);
            if(sendList == null){
                return "404";
            }
            model.addAttribute("sl", sendList);
            User user = CurrentUser.getUser();
            model.addAttribute("user", user);
            return "/mv/add_mv";
        }catch (Exception e){
            return "404";
        }
    }

    /**
     * 持久化分享音频
     */
    @RequestMapping(value = "/send/mv_save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveMv(String title,
                                      String decri,
                                      String cover,
                                      Integer sourceType,
                                      String sourcePath,
                                      String path,
                                      String songer,
                                      Integer slId){
        Map<String, Object> result = new HashMap();
        try{
            if(title!=null && title.length() <= 25 && decri != null && decri.length() <=450){
                User user = CurrentUser.getUser();
                if(user != null && user.getId() != null){
                    Mv mv = new Mv();
                    mv.setCover(StringUtils.isNullOrEmpty(cover) ? null : cover);
                    mv.setCreateTime(new Date());
                    mv.setUpdateTime(new Date());
                    mv.setFav(0);
                    mv.setUserId(user.getId());
                    mv.setPlay(0);
                    mv.setIsdel(0);
                    mv.setDamaku(0);
                    mv.setDamakuPool(1000);
                    mv.setDecri(StringDeal.getText(decri));
                    mv.setTitle(StringDeal.getText(title));
                    mv.setPath(path);
                    mv.setSendlistId(slId);
                    mv.setSourcePath(sourcePath);
                    mv.setSourceType(sourceType);
                    mv.setType(Mv.MUSIC);
                    mv.setSonger(StringUtils.isNullOrEmpty(songer) ? null : songer);
                    this.mvService.save(mv);
                    result.put("code", 200);
                    result.put("msg", "success");
                }else{
                    result.put("code", 403);
                    result.put("msg", "权限不足，必须登录！");
                }
            }else{
                result.put("code", 403);
                result.put("msg", "内容不符合要求！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "fail");
        }

        return result;
    }

    /** 关键词搜索*/
    @RequestMapping(value = "/kabi/search/m_kw_{kw}")
    public String Search(@PathVariable("kw") String kw, Model model){
        model.addAttribute("user", CurrentUser.getUser());
        model.addAttribute("kw", kw);
        return "/mv/mv_result";
    }

    /** 关键词搜索接口*/
    @RequestMapping(value = "/kabi/search/m_result")
    @ResponseBody
    public List<MvVo> getResult(String kw, Integer page){
        if(StringUtils.isNullOrEmpty(kw)){
            return null;
        }
        return this.mvMapper.getSearchResult("%"+kw+"%", page*10);
    }

    /** 首页最新放送单列表*/
    @RequestMapping(value = "/new/send_list")
    @ResponseBody
    public List<SendList> getSl(){
        try{
            return this.sendListService.getNewest();
        }catch (Exception e){
            return null;
        }
    }

    /** 首页最新音乐分享*/
    @RequestMapping(value = "/new/mv")
    @ResponseBody
    public List<Mv> getNewMv(){
        try{
            return this.mvService.getNewMv();
        }catch (Exception e){
            return null;
        }
    }

}
