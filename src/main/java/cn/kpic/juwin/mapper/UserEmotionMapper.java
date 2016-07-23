package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.UserEmotion;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public interface UserEmotionMapper {

    void save(UserEmotion userEmotion);

    void del(Long id);

    List<Integer> getEmIdsByUserIdAndEmIds(Map<String, Object> params);

}
