package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Mv;
import cn.kpic.juwin.domain.vo.MvVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public interface MvMapper {

    Integer save(Mv mv);

    MvVo getById(Integer id);//删除不包含在内

    List<Mv> getMvsBySlId(@Param("slId")Integer slId, @Param("mvId")Integer mvId);

    void plusCount(@Param("type")Integer type, @Param("count")Integer count, @Param("mvId")Integer mvId);

    void updateTopicId(@Param("topicId")Long topicId, @Param("mvId")Integer mvId);

    List<MvVo> getSearchResult(@Param("kw")String kw, @Param("page")Integer page);

    List<Mv> getNewMv();
}
