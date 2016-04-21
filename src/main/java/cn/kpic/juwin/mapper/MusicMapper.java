package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Music;

import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/20.
 */
public interface MusicMapper {

    void save(Music music);

    List<Music> gatAllMusic(Map params);

}
