package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Mv;
import cn.kpic.juwin.domain.vo.MvVo;

import java.util.List;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public interface MvService {

    void save(Mv mv);

    MvVo getById(Integer id);//删除不包含在内

    List<Mv> getMvsBySlId(Integer slId, Integer mvId);

    boolean isFav(Long userId, Integer mvId);

    Integer saveUserMv(Integer mvId, Long userId);

    List<Mv> getNewMv();
}
