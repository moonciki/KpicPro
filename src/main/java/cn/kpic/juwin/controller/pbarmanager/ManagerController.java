package cn.kpic.juwin.controller.pbarmanager;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Hit;
import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.PbarManagerApplyVo;
import cn.kpic.juwin.mapper.HitMapper;
import cn.kpic.juwin.mapper.PbarManagerApplyMapper;
import cn.kpic.juwin.service.PbarManagerApplyService;
import cn.kpic.juwin.service.PbarService;
import cn.kpic.juwin.service.UserService;
import cn.kpic.juwin.shiro.redis.RedisCache;
import cn.kpic.juwin.utils.CurrentUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bjsunqinwen on 2016/4/16.
 */
@Controller
public class ManagerController {

    private Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private PbarService pbarService;

    @Autowired
    private UserService userService;

    @Autowired
    private HitMapper hitMapper;

    @Autowired
    private PbarManagerApplyMapper pbarManagerApplyMapper;

    @Autowired
    private PbarManagerApplyService pbarManagerApplyService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/sub4615{pbarId}")
    public String enterPbarManager(@PathVariable("pbarId") Long pbarId,Model model){
        try{
            User curr_user = CurrentUser.getUser();
            String role = this.userService.getRole(curr_user.getId(), pbarId);
            if("3".equals(role)){
                return "/404";
            }
            model.addAttribute("user", curr_user);
            model.addAttribute("role", role);
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 1);
            return "/pbar/manager_pbar_info";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("enter pbar manager error !");
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/tj4615{pbarId}_{year}_{month}")
    public String enterPbarTj(@PathVariable("pbarId") Long pbarId,@PathVariable("year") Integer year, @PathVariable("month") Integer month, Model model){
        try{
            User curr_user = CurrentUser.getUser();
            String role = this.userService.getRole(curr_user.getId(), pbarId);
            if("3".equals(role)){
                return "/404";
            }

            if(year == null || month == null){
                SimpleDateFormat myTimeFormat = new SimpleDateFormat("yyyy-MM");
                String nowStr = myTimeFormat.format(new Date());
                String[] dates = nowStr.split("-");
                year = Integer.parseInt(dates[0]);
                month = Integer.parseInt(dates[1]);
            }

            Map params = new HashMap();
            params.put("pbarId", pbarId);
            params.put("year1",year);
            params.put("month1", month);
            List<Hit> result = this.hitMapper.getAllByPbarIdAndYM(params);
            String days = "[";
            String values = "[";
            for(Hit hit : result){
                days += "'"+hit.getDay1()+"',";
                values += hit.getValue1()+",";
            }
            days+="]";
            values+="]";
            days = days.replace(",]", "]");
            values = values.replace(",]", "]");

            model.addAttribute("days", days);
            model.addAttribute("values", values);
            model.addAttribute("user", curr_user);
            model.addAttribute("role", role);
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 66);

            return "/pbar/manager_pbar_tj";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("enter pbar manager error !");
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/upd4615{pbarId}")
    public String updPbarManager(@PathVariable("pbarId") Long pbarId,Model model){
        try{
            User curr_user = CurrentUser.getUser();
            String role = this.userService.getRole(curr_user.getId(), pbarId);
            if(!"1".equals(role)){
                return "/404";
            }
            model.addAttribute("user", curr_user);
            model.addAttribute("role", role);
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 2);
            return "/pbar/manager_pbar_update";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("update page enter pbar manager error !");
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/upd4615{pbarId}", method= RequestMethod.POST)
    @ResponseBody
    public boolean updPbarManagerForm(@PathVariable("pbarId") Long pbarId,Pbar pbar){
        try{
            User curr_user = CurrentUser.getUser();
            String role = this.userService.getRole(curr_user.getId(), pbarId);
            if(!"1".equals(role)){
                return false;
            }
            pbar.setId(pbarId);
            this.pbarService.update(pbar);
            this.pbarService.clearCache(RedisCacheKey.PBAR_INDEX+pbarId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("update page enter pbar manager error !");
            return false;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/tip4615{pbarId}")
    public String tips(@PathVariable("pbarId") Long pbarId,Model model){
        try{
            User curr_user = CurrentUser.getUser();
            String role = this.userService.getRole(curr_user.getId(), pbarId);
            if("3".equals(role)){
                return "/404";
            }
            model.addAttribute("user", curr_user);
            model.addAttribute("role", role);
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 3);
            return "/pbar/manager_pbar_tips";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("get all tips page error ! pbarId = " + pbarId);
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/subject/manager/apply4615{pbarId}")
    public String showAllApplys(@PathVariable("pbarId") Long pbarId, Model model){
        try{
            User curr_user = CurrentUser.getUser();
            String role = this.userService.getRole(curr_user.getId(), pbarId);
            if(!"1".equals(role)){
                return "/404";
            }
            model.addAttribute("user", curr_user);
            model.addAttribute("role", this.userService.getRole(curr_user.getId(), pbarId));
            model.addAttribute("pbar", this.pbarService.getPbarIndex(pbarId));
            model.addAttribute("flag", 4);
            return "/pbar/manager_pbar_applys";
        }catch (Exception e){
            e.printStackTrace();
            logger.error("get all apply error ! pbarId = " + pbarId);
            return "/404";
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/manager/apply")
    @ResponseBody
    public List<PbarManagerApplyVo> getAllApplys(Long pbarId, @RequestParam(value = "page", defaultValue = "0", required = true)Integer page){
        try{
            User curr_user = CurrentUser.getUser();
            String role = this.userService.getRole(curr_user.getId(), pbarId);
            if(!"1".equals(role)){
                return null;
            }
            if(pbarId == null || pbarId < 0){
                return null;
            }
            Map params = new HashMap();
            params.put("pbarId", pbarId);
            params.put("page", page);
            List<PbarManagerApplyVo> result = this.pbarManagerApplyMapper.getAllApply(params);
            return result.size() == 0 ? null : result;
        }catch (Exception e){
            return null;
        }
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/manager/apply/tg")
    @ResponseBody
    public boolean tg(Long userId, Long pbarId, Long id){
        User curr_user = CurrentUser.getUser();
        String role = this.userService.getRole(curr_user.getId(), pbarId);
        if(!"1".equals(role)){
            return false;
        }
        if(userId == null || pbarId == null || id == null){
            return false;
        }
        try{
            this.pbarManagerApplyService.tg(userId, pbarId, id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/manager/apply/btg")
    @ResponseBody
    public boolean tg(Long id){
        if(id == null){
            return false;
        }
        try{
            this.pbarManagerApplyService.btg(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
