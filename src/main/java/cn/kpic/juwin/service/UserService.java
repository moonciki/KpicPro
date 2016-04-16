package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.TopicManager;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
public interface UserService {
    User getUserById(Long id) throws Exception;
    List<User> getAllUser()throws Exception;
    void addUser(User user) throws Exception;
    boolean jugeLogin(String name, String password)throws Exception;
    void update(User user);

    List<TopicManager> getAllSmallManagerByPbarId(Long id);
}
