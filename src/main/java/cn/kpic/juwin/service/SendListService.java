package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.SendList;

import java.util.List;

/**
 * Created by Administrator on 2017/1/29 0029.
 */
public interface SendListService {

    Integer save(SendList sendList);

    List<SendList> getNewest();

}
