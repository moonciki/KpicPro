package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Msg;
import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.TopicManager;
import cn.kpic.juwin.domain.vo.UserVo;
import cn.kpic.juwin.mapper.MsgMapper;
import cn.kpic.juwin.mapper.PbarMapper;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private PbarMapper pbarMapper;

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
    @Transactional
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
    @Transactional
    public void update(User user) {
        this.userMapper.update(user);
    }

    @Override
    public List<TopicManager> getAllSmallManagerByPbarId(Long id) {
        List<TopicManager> result = this.userMapper.getAllSmallManagerByPbarId(id);
        return result.size() == 0 ? null : result;
    }

    @Override
    public boolean isSmallManager(Long userId, Long pbarId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("pbarId", pbarId);
        List<Long> result = this.userMapper.isSmallManager(params);
        if(result.size() == 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String getRole(Long userId, Long pbarId) {
        Pbar pbar = this.pbarMapper.getById(pbarId);
        if(pbar != null && pbar.getIspass() == 1){
            /** 大管理员的情况*/
            if(String.valueOf(pbar.getUserId()).equals(String.valueOf(userId))){
                return "1";
            }else{
                /** 小管理员的情况*/
                if(this.isSmallManager(userId, pbarId)){
                    return "2";
                }else{
                    return "3";
                }
            }
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public List<UserVo> getAllPbarUsers(Long pbarId, Integer page) {
        Map params = new HashMap();
        params.put("pbarId", pbarId);
        params.put("page", page);
        List<UserVo> result = this.userMapper.getAllPbarUsers(params);
        return result.size() == 0 ? null : result;
    }

    @Override
    public void delSmallManager(Long id, Long userId, Long pbarId) {
        this.userMapper.delSmallManager(id);
        String key = RedisCacheKey.PBAR_USER_ROLE + "_p" + pbarId + "_u" + userId;
        this.redisTemplate.delete(key);
    }
}
