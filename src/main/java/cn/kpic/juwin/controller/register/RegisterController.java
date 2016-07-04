package cn.kpic.juwin.controller.register;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.mapper.UserMapper;
import cn.kpic.juwin.service.UserService;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/rg/check_name")
    @ResponseBody
    public Boolean checkName(String name){
        try{
            Long id = this.userMapper.checkName(name);
            return id == null ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/rg/check_num")
    @ResponseBody
    public Boolean checkNum(Long num){
        try{
            Long id = this.userMapper.checkNum(num);
            return id == null ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/rg/save")
    @ResponseBody
    public Boolean save(User user){
        try{
            user.setName(StringDeal.getText(user.getName()));
            user.setBirth(StringDeal.getText(user.getBirth()));
            user.setAddress(StringDeal.getText(user.getAddress()));
            user.setTag(StringDeal.getText(user.getTag()));
            if(user == null){
                return false;
            }
            user.setCreateTime(new Date());
            user.setIsdel(0);
            user.setIsjm(0);
            user.setIsIndex(0);
            this.userService.save(user);


            UsernamePasswordToken token = new UsernamePasswordToken(user.getNum()+"", user.getPassword());
            token.setRememberMe(true);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);


            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
