package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Mv;
import cn.kpic.juwin.domain.SendList;
import cn.kpic.juwin.domain.vo.SendListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/1/26 0026.
 */
public interface SendListMapper {

    Integer save(SendList sendList);

    SendListVo getSendListInfo(Integer id);

    List<Mv> getAllMvsBySlId(Integer id);

    void plusCount(@Param("type")Integer type, @Param("count")Integer count, @Param("slId")Integer slId);

    List<SendList> getMySendList(@Param("userId") Long userId);

    List<SendList> getNewest();
}
