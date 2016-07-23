package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Emotion;
import cn.kpic.juwin.domain.vo.EmotionVo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/7 0007.
 */
public interface EmotionService {

    void save(Emotion emotion);

    List<Emotion> getAllByUserId(Long userId, Integer page);

    List<EmotionVo> getAllEms(Integer page);

}
