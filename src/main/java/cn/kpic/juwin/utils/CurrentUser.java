package cn.kpic.juwin.utils;

import cn.kpic.juwin.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by bjsunqinwen on 2016/3/21.
 */
public class CurrentUser {

    public static User getUser(){
        User currentUser = null;
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            Object object = subject.getPrincipal();
            if(object != null && object instanceof User){
                currentUser = (User)object;
            }
        }
        return currentUser;
    }

    public static int getFinalScore(int level){
        if(level == 0){
            return 15;
        }else{
            return level * 15 * 2;
        }
    }

}
