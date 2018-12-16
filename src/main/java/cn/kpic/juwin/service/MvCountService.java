package cn.kpic.juwin.service;

/**
 * Created by Administrator on 2017/1/27 0027.
 */
public interface MvCountService {

    void playPlus(Integer mvId, Integer count);

    void damakuPlus(Integer mvId, Integer count);

    void favPlus(Integer mvId, Integer count);

    Integer getPlay(Integer mvId);

    Integer getDamaku(Integer mvId);

}
