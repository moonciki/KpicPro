package cn.kpic.juwin.shiro.realm;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.mapper.UserMapper;
import cn.kpic.juwin.mapper.UserPowerMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/28 0028.
 */
@Transactional
public class ShiroDbRealm extends AuthorizingRealm{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPowerMapper userPowerMapper;

    /***
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        User user = (User) pc.fromRealm(getName()).iterator().next();
        if (user != null) {
            List<String> pers = userPowerMapper.getAllPowerByUserId(user.getId());
            if (pers != null && !pers.isEmpty()) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                for (String each : pers) {
                    //将权限资源添加到用户信息中
                    info.addStringPermission(each);
                }
                return info;
            }
        }
        return null;
    }
    /***
     * 获取认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) at;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if (username != null && !"".equals(username)) {
            User user = userMapper.findUserByName(username);
            if (user != null) {
                return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            }
        }
        return null;
    }

}
