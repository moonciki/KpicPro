package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Home;
import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.PbarType;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.AlbumVo;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.UserVo;
import cn.kpic.juwin.domain.vo.Xlh;
import cn.kpic.juwin.service.impl.IndexServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
@Service
public class IndexServiceImplCache extends IndexServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<PbarType> getAllTypes() {
        String key = RedisCacheKey.PBAR_TYPE_INDEX;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<PbarType> result = super.getAllTypes();
            if(result == null){
                return null;
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<PbarType> final_result = xlh.getList();
        return final_result == null ? null : final_result;
    }

    @Override
    public List<AlbumVo> getAllAlbums() {
        String key = RedisCacheKey.ALBUM_INDEX;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<AlbumVo> result = super.getAllAlbums();
            if(result == null){
                return null;
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<AlbumVo> final_result = xlh.getList();
        return final_result == null ? null : final_result;
    }

    @Override
    public List<UserVo> getAllUsers() {
        String key = RedisCacheKey.USER_INDEX_HOME;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<UserVo> result = super.getAllUsers();
            if(result == null){
                return null;
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<UserVo> final_result = xlh.getList();
        return final_result == null ? null : final_result;
    }

    @Override
    public List<PbarHomeTopicPost> getAllPost(Integer page) {
        String key = RedisCacheKey.POST_INDEX_HOME;
        String hashKey = key + "_" + page;
        String json;
        List<PbarHomeTopicPost> pbarHomeTopicPosts = null;
        if(page > 100){
            pbarHomeTopicPosts = super.getAllPost(page);
            return pbarHomeTopicPosts;
        }
        /** 说明缓存不存在*/
        if(!redisTemplate.hasKey(key)){
            if(!redisTemplate.boundHashOps(key).hasKey(hashKey)){
                return this.saveKey(key, hashKey, page);
            }
        }else{
            if(!redisTemplate.boundHashOps(key).hasKey(hashKey)){
                return this.saveKey(key, hashKey, page);
            }
        }
        json = (String)redisTemplate.boundHashOps(key).get(hashKey);
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        pbarHomeTopicPosts = xlh.getList();
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
        return pbarHomeTopicPosts;
    }

    @Override
    public List<Home> getAllImg() {
        String key = RedisCacheKey.IMG_INDEX_HOME;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<Home> result = super.getAllImg();
            if(result == null){
                return null;
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<Home> final_result = xlh.getList();
        return final_result == null ? null : final_result;
    }

    @Override
    public List<UserVo> getAllWarnUsers() {
        String key = RedisCacheKey.USER_WARN_HOME;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<UserVo> result = super.getAllWarnUsers();
            if(result == null){
                return null;
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<UserVo> final_result = xlh.getList();
        return final_result == null ? null : final_result;
    }

    @Override
    public List<Pbar> getAllPbars() {
        String key = RedisCacheKey.PBAR_INDEX_HOME;
        String json;
        if(!this.redisTemplate.hasKey(key)){
            List<Pbar> result = super.getAllPbars();
            if(result == null){
                return null;
            }
            Xlh xlh = new Xlh();
            xlh.setList(result);
            String str = JSON.toJSONString(xlh);
            this.redisTemplate.boundValueOps(key).set(str);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
        json = (String)redisTemplate.boundValueOps(key).get();
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        List<Pbar> final_result = xlh.getList();
        return final_result == null ? null : final_result;
    }

    private List<PbarHomeTopicPost> saveKey(String key, String hashKey, int page){
        List<PbarHomeTopicPost> pbarHomeTopicPosts = super.getAllPost(page);
        Xlh xlh = new Xlh();
        xlh.setList(pbarHomeTopicPosts);
        String json = JSON.toJSONString(xlh);
        /** 存入哈希结构缓存*/
        redisTemplate.boundHashOps(key).put(hashKey, json);
        return pbarHomeTopicPosts;
    }

}
