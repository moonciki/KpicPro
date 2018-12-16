package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.*;
import cn.kpic.juwin.domain.vo.TopicManager;
import cn.kpic.juwin.domain.vo.UserVo;
import cn.kpic.juwin.geetest.GeetestLib;
import cn.kpic.juwin.mapper.*;
import cn.kpic.juwin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLevelMapper userLevelMapper;

    @Autowired
    private UserIntegrityMapper userIntegrityMapper;

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private PbarMapper pbarMapper;

    @Autowired
    private UserPowerMapper userPowerMapper;

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
    public boolean jugeLogin(Long num, String password) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("num", num);
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
        Map<String, Object> params = new HashMap<String, Object>();
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

    @Override
    @Transactional
    public void save(User user) {
        this.userMapper.save(user);
        Long id = user.getId();
        List<Msg> msgs = new ArrayList<Msg>();
        Msg msg = new Msg();
        msg.setUserId(id);
        msg.setNum(0);
        msg.setType(0);
        msgs.add(msg);
        Msg msg2 = new Msg();
        msg2.setUserId(id);
        msg2.setNum(0);
        msg2.setType(1);
        msgs.add(msg2);
        this.msgMapper.addMsg(msgs);

        UserLevel userLevel = new UserLevel();
        userLevel.setUserId(id);
        userLevel.setLevel(0);
        userLevel.setScore(0);
        this.userLevelMapper.save(userLevel);

        UserIntegrity userIntegrity = new UserIntegrity();
        userIntegrity.setUserId(id);
        userIntegrity.setNum(70);
        userIntegrity.setUpdateTime(new Date());
        this.userIntegrityMapper.save(userIntegrity);

        UserPower userPower = new UserPower();
        userPower.setUserId(id);
        userPower.setPowerId(1L);
        this.userPowerMapper.save(userPower);
    }

    //这里根据传来的极验验证所需的三个参数，对其进行二次验证
    @Override
    public boolean geetestVerify(String challenge, String validate, String seccode){
        if(StringUtils.isEmpty(challenge) || StringUtils.isEmpty(validate) || StringUtils.isEmpty(seccode)){
            return false;
        }
        //初始化极验验证器
        GeetestLib gtSdk = GeetestLib.getGeetestLib();
        //从session中获取gt-server状态
        int gtServerStatusCode = Integer.parseInt(redisTemplate.boundValueOps(gtSdk.gtServerStatusSessionKey).get()+"");
        int result = 0;
        if(gtServerStatusCode == 1){//表示极验服务器正常
            result = gtSdk.enhencedValidateRequest(challenge, validate, seccode);
        }else{//极验服务器异常
            result = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }
        return result == GeetestLib.success_code ? true : false;
    }
}
