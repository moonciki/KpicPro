package cn.kpic.juwin.service.cache;

import cn.kpic.juwin.domain.PbarAlbum;
import cn.kpic.juwin.domain.vo.PbarAlbumList;
import cn.kpic.juwin.mapper.PbarAlbumMapper;
import cn.kpic.juwin.service.PbarAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/4/25.
 */
@Service
public class PbarAlbumServiceImpl implements PbarAlbumService{

    @Autowired
    private PbarAlbumMapper pbarAlbumMapper;


    @Override
    @Transactional
    public void addPbarAlbum(PbarAlbum pbarAlbum) {
        this.pbarAlbumMapper.save(pbarAlbum);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.pbarAlbumMapper.del(id);
    }

    @Override
    public List<PbarAlbumList> getAllAlbumByPbarId(Long pbarId, Integer page) {

        Map params = new HashMap();

        params.put("pbarId", pbarId);

        params.put("page", page * 10);

        List<PbarAlbumList> result = this.pbarAlbumMapper.getAllAlbumByPbarId(params);

        return result.size() == 0 ? null : result;
    }
}
