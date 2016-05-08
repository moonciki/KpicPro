package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Emotion;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/7 0007.
 */
public interface EmotionMapper {

    void save(Emotion emotion);

    List<Emotion> getAllByUserId(Map params);

}
