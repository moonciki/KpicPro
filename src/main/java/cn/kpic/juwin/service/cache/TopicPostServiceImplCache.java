package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.TopicImg;
import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.vo.PbarHomeTopicPost;
import cn.kpic.juwin.domain.vo.Xlh;
import cn.kpic.juwin.service.impl.TopicPostServiceImpl;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
@Service
public class TopicPostServiceImplCache extends TopicPostServiceImpl{

    private static Logger logger = Logger.getLogger(TopicPostServiceImplCache.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 给每个团子的首页加前十页的缓存
     * @param pbarId
     * @param page
     * @return
     */
    @Override
    public List<PbarHomeTopicPost> getAllTopicByPbarId(Long pbarId, int page) {
        String key = RedisCacheKey.PBAR_PAGE + pbarId;
        String hashKey = key + "_" + page;
        String json;
        List<PbarHomeTopicPost> pbarHomeTopicPosts = null;
        if(page > 100){
            pbarHomeTopicPosts = super.getAllTopicByPbarId(pbarId, page);
            return pbarHomeTopicPosts;
        }
        /** 说明缓存不存在*/
        if(!redisTemplate.hasKey(key)){
            if(!redisTemplate.boundHashOps(key).hasKey(hashKey)){
                return this.saveKey(key, hashKey, pbarId, page);
            }
        }else{
            if(!redisTemplate.boundHashOps(key).hasKey(hashKey)){
                return this.saveKey(key, hashKey, pbarId, page);
            }
        }
        json = (String)redisTemplate.boundHashOps(key).get(hashKey);
        Xlh xlh = JSON.parseObject(json, Xlh.class);
        pbarHomeTopicPosts = xlh.getList();
        logger.info("get all topic post !");
        return pbarHomeTopicPosts;
    }

    @Override
    public TopicPost addTopicPost(String title, String content, String shortContent, Long pbarId, Long userId) {
        /** 清除某话题前十页缓存*/
        redisTemplate.delete(RedisCacheKey.PBAR_PAGE + pbarId);
        return super.addTopicPost(title, content, shortContent, pbarId, userId);
    }

    private List<PbarHomeTopicPost> saveKey(String key, String hashKey, Long pbarId, int page){
        List<PbarHomeTopicPost> pbarHomeTopicPosts = super.getAllTopicByPbarId(pbarId, page);
        Xlh xlh = new Xlh();
        xlh.setList(pbarHomeTopicPosts);
        String json = JSON.toJSONString(xlh);
        /** 存入哈希结构缓存*/
        redisTemplate.boundHashOps(key).put(hashKey, json);
        return pbarHomeTopicPosts;
    }


}
