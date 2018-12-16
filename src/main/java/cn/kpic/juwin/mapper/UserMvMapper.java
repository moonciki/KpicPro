package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.UserMv;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/1/27 0027.
 */
public interface UserMvMapper {

    Integer save(UserMv userMv);

    Integer isFav(@Param("userId")Long userId, @Param("mvId")Integer mvId);

    void del(Integer userId, Integer mvId);

}
