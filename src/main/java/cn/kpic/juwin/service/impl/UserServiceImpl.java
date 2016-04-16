package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.TopicManager;
import cn.kpic.juwin.mapper.MsgMapper;
import cn.kpic.juwin.mapper.UserMapper;
import cn.kpic.juwin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User getUserById(Long id) throws Exception{
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> getAllUser() throws Exception {
        redisTemplate.boundValueOps("sqw").set("hh");
        return userMapper.getAllUser();
    }

    @Override
    public void addUser(User user) throws Exception {
        /*Msg msg = new Msg();
        msg.setTitle("sqw");
        msgMapper.addMsg(msg);
        userMapper.addUser(user);
        throw new RuntimeException("asd");*/
    }

    @Override
    public boolean jugeLogin(String name, String password) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        User user = userMapper.jugeUser(params);
        return user == null ? false : true;
    }

    @Override
    public void update(User user) {
        this.userMapper.update(user);
    }

    @Override
    public List<TopicManager> getAllSmallManagerByPbarId(Long id) {
        List<TopicManager> result = this.userMapper.getAllSmallManagerByPbarId(id);
        return result.size() == 0 ? null : result;
    }


}
