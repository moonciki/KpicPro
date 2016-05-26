package cn.kpic.juwin.controller.pbar;

import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.PbarManagerApply;
import cn.kpic.juwin.domain.Tags;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.PbarIndexVo;
import cn.kpic.juwin.domain.vo.TagsVo;
import cn.kpic.juwin.domain.vo.UploadTokenInfo;
import cn.kpic.juwin.domain.vo.UserPbarVo;
import cn.kpic.juwin.mapper.PbarManagerApplyMapper;
import cn.kpic.juwin.mapper.PbarTypeMapper;
import cn.kpic.juwin.mapper.TagsMapper;
import cn.kpic.juwin.qiniu.kpic2.QiniuService;
import cn.kpic.juwin.service.PbarService;
import cn.kpic.juwin.service.UserService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by bjsunqinwen on 2016/3/22.
 */
@Controller
public class PbarController {

    private Logger logger = Logger.getLogger(PbarController.class);

    @Autowired
    private PbarService pbarService;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private PbarTypeMapper pbarTypeMapper;

    @Autowired
    private PbarManagerApplyMapper pbarManagerApplyMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TagsMapper tagsMapper;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/pbar_add")
    public String add(){
        this.qiniuService.generateUUID();
        return "/pbar/pbar_add";
    }

    /** 进入话题*/
    @RequestMapping(value = "/post/subjects/sub4615{pbarId}")
    public String pbarIndex(@PathVariable("pbarId") Long pbarId,Model model){

        model.addAttribute("user", CurrentUser.getUser());

        PbarIndexVo pbarIndexVo = this.pbarService.getPbarIndex(pbarId);
        if(pbarIndexVo == null){
            return "/404";
        }

        User curr_user = CurrentUser.getUser();
        if(curr_user != null){
            model.addAttribute("role", this.userService.getRole(curr_user.getId(), pbarId));
        }

        model.addAttribute("pbar", pbarIndexVo);

        return "/pbar/pbar_index";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/save")
    @ResponseBody
    public Map<String, Object> savePbar(String name, String msg, String logo, int type, Long userId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        try{
            Pbar pbar = new Pbar();
            pbar.setUserId(userId);
            pbar.setCreate_time(new Date());
            pbar.setLogo(logo);
            pbar.setMsg(StringDeal.getText(msg).trim());
            pbar.setType(type);
            pbar.setName(StringDeal.getText(name).trim());
            pbar.setUpdate_time(new Date());
            this.pbarService.save(pbar);
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("save pbar is error, pbar name is : "+name);
            logger.error(e.getMessage());
        }
        return map;
    }

    /** 获取uptoken*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/upload")
    @ResponseBody
    public Map<String, Object> upload(){
        Map<String, Object> map = new HashMap();

        try {
            UploadTokenInfo uploadTokenInfo = qiniuService.generateUploadToken();
            map.put("success", true);
            map.put("uptoken", uploadTokenInfo.getToken());
        } catch(Throwable t){
            logger.info(t.getMessage(), t);
            map.put("success", false);
            map.put("message", t.getMessage());
        }

        return map;
    }

    /** 获取一个uuid用于重命名key值*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/uuid")
    @ResponseBody
    public Map<String, Object> uuid(){
        Map<String, Object> map = new HashMap();

        try {
            String uuid = "tuan/logo/"+qiniuService.generateUUID() + ".jpg";
            map.put("success", true);
            map.put("uuid", uuid);
        } catch(Throwable t){
            logger.info(t.getMessage(), t);
            map.put("success", false);
        }

        return map;
    }

    /** 上传图片成功后的回调*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/qiniu/callback")
    @ResponseBody
    public Map<String, Object> qiniuCallback(@ModelAttribute QiniuCallBackFormBean formBean, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap();
        logger.info("qiniu callback pbar param: " + JSONObject.toJSONString(request.getParameterMap()));

        try {
            //MaterialVideoInfo materialVideoInfo = materialVideoService.addVideo(formBean);
            map.put("success", true);
            map.put("key", formBean.getKey());
        } catch(Throwable t){
            logger.info(t.getMessage(), t);
            map.put("success", false);
            map.put("message", t.getMessage());
        }

        return map;
    }

    /** 个人话题管理*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/all/user/subject")
    public String getAllSubject(Model model){
        User user = CurrentUser.getUser();
        model.addAttribute("user", user);
        model.addAttribute("flag", 11);
        return "/user/manager_center_pbars";
    }

    /** 获取个人话题列表接口*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/all/user/subjects")
    @ResponseBody
    public List<UserPbarVo> getAllSelfSubject(@RequestParam(value = "userId", required = true)Long userId,
                                              @RequestParam(value = "page", defaultValue = "0", required = true)int page){
        return this.pbarService.getAllSelfPbar(userId, page * 10);
    }

    /** 话题申请*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/subject/apply")
    public String subjectApply(Model model){
        User user = CurrentUser.getUser();
        model.addAttribute("user", user);
        model.addAttribute("flag", 12);
        model.addAttribute("allType", this.pbarTypeMapper.getAllType());
        return "/user/manager_center_pbars_apply";
    }

    /** 申请话题递交*/
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/pbar/save")
    @ResponseBody
    public Map<String, Object> savePbar(Pbar pbar){
        User user = CurrentUser.getUser();
        Map<String, Object> result = new HashMap<>();
        try{
            pbar.setTags(this.tagsJson(pbar.getTags()));
            pbar.setUserId(user.getId());
            pbar.setCreate_time(new Date());
            pbar.setUpdate_time(new Date());
            this.pbarService.save(pbar);
            result.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("pbar save error ! name = "+pbar.getName());
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/pbar/save/sq")
    @ResponseBody
    public synchronized Map<String, Object> saveSq(Long userId, Long pbarId, String msg){
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        if(userId == null || pbarId == null || StringUtil.isBlank(msg)){
            result.put("msg", "参数错误！");
            return result;
        }
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            params.put("pbarId", pbarId);
            Integer status = this.pbarManagerApplyMapper.isApply(params);

            if(status != null){
                result.put("msg", "您已经申请过了！");
                return result;
            }

            PbarManagerApply pbarManagerApply = new PbarManagerApply();

            pbarManagerApply.setUserId(userId);
            pbarManagerApply.setMsg(msg);
            pbarManagerApply.setPbarId(pbarId);
            pbarManagerApply.setCreateTime(new Date());
            pbarManagerApply.setStatus(0);
            this.pbarManagerApplyMapper.save(pbarManagerApply);

            result.put("success", true);

        }catch (Exception e){
            logger.error("pbar manager sq save error ! pbarId = "+pbarId+" and userId = "+userId);
            e.printStackTrace();
        }
        return result;
    }

    private String tagsJson(String tags){
        String[] str = tags.split("&");
        List<TagsVo> result = new ArrayList<>();
        for(int i = 0; i < str.length; i++){
            String[] str2 = str[i].split(":");
            TagsVo tagsVo = new TagsVo();
            tagsVo.setName(str2[0]);
            tagsVo.setColor(str2[1]);
            result.add(tagsVo);
        }
        return JSON.toJSONString(result);
    }

    /** 关键词搜索*/
    @RequestMapping(value = "/kabi/search/kw_{kw}")
    public String Search(@PathVariable("kw") String kw, Model model){
        model.addAttribute("user", CurrentUser.getUser());
        model.addAttribute("kw", kw);
        return "/pbar/pbar_result";
    }

    /** 关键词搜索接口*/
    @RequestMapping(value = "/kabi/search/result")
    @ResponseBody
    public List<PbarIndexVo> getResult(String kw, Integer page){
        if(StringUtils.isBlank(kw)){
            return null;
        }
        return this.pbarService.getSearchResult(kw, page*10);
    }
}
