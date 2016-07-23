package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Emotion;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.UserEmotion;
import cn.kpic.juwin.domain.vo.EmotionVo;
import cn.kpic.juwin.mapper.EmotionMapper;
import cn.kpic.juwin.mapper.UserEmotionMapper;
import cn.kpic.juwin.service.EmotionService;
import cn.kpic.juwin.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2016/5/7 0007.
 */
@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    @Autowired
    private UserEmotionMapper userEmotionMapper;

    @Override
    @Transactional
    public void save(Emotion emotion) {

        this.emotionMapper.save(emotion);

        UserEmotion userEmotion = new UserEmotion();
        userEmotion.setEmotionId(emotion.getId());
        userEmotion.setUserId(emotion.getUserId());
        userEmotion.setCreateTime(new Date());
        this.userEmotionMapper.save(userEmotion);
    }

    @Override
    public List<Emotion> getAllByUserId(Long userId, Integer page) {
        if(userId == null || page == null){
            return null;
        }
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("page", page);
        List<Emotion> result = this.emotionMapper.getAllByUserId(params);
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<EmotionVo> getAllEms(Integer page) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", page * 10);
        List<EmotionVo> lists = this.emotionMapper.getAllEmotions(params);
        User user = CurrentUser.getUser();
        if(user != null){
            if(lists.size() != 0){
                List<Integer> storeIds = new ArrayList<Integer>();
                for(EmotionVo emotionVo : lists){
                    storeIds.add(emotionVo.getId());
                }

                Map<String, Object> params2 = new HashMap<String, Object>();
                params2.put("userId", user.getId());
                params2.put("storeIds", storeIds);
                List<Integer> ids = this.userEmotionMapper.getEmIdsByUserIdAndEmIds(params2);

                if(ids.size() != 0){
                    for(EmotionVo emotionVo : lists){
                        if(ids.contains(emotionVo.getId())){
                            emotionVo.setIsStored(true);
                        }
                    }
                }
            }else{
                return null;
            }
        }
        return lists.size() == 0 ? null : lists;
    }
}
