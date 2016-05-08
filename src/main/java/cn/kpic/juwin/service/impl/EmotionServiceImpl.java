package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Emotion;
import cn.kpic.juwin.mapper.EmotionMapper;
import cn.kpic.juwin.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/7 0007.
 */
@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    @Override
    @Transactional
    public void save(Emotion emotion) {
        this.emotionMapper.save(emotion);
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
}
