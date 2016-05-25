package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Pic;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/20.
 */
public interface PicMapper {

    void save(Pic pic);

    void saves(List<Pic> pic);

    List<Pic> getAllPics(Long id);

}
