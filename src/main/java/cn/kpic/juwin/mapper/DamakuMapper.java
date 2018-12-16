package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Damaku;
import cn.kpic.juwin.domain.vo.DamakuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hahajing on 2017/1/21 0021.
 */
public interface DamakuMapper {

    void save(Damaku damaku);

    List<DamakuVo> getAllDamakuByMvId(@Param("mvId")Integer mvId, @Param("size")Integer size);

}
