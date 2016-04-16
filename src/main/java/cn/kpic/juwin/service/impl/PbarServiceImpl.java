package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.constant.KpicConstant;
import cn.kpic.juwin.domain.Pbar;
import cn.kpic.juwin.domain.UserPbar;
import cn.kpic.juwin.domain.vo.PbarIndexVo;
import cn.kpic.juwin.domain.vo.TagsVo;
import cn.kpic.juwin.domain.vo.UserPbarVo;
import cn.kpic.juwin.mapper.PbarFocusMapper;
import cn.kpic.juwin.mapper.PbarMapper;
import cn.kpic.juwin.mapper.UserPbarMapper;
import cn.kpic.juwin.service.PbarService;
import com.alibaba.fastjson.JSON;
import com.qiniu.util.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/3/22.
 */
public class PbarServiceImpl implements PbarService {

    private Logger logger = Logger.getLogger(PbarServiceImpl.class);

    @Autowired
    private PbarMapper pbarMapper;

    @Autowired
    private UserPbarMapper userPbarMapper;

    @Autowired
    private PbarFocusMapper pbarFocusMapper;

    @Override
    @Transactional
    public void save(Pbar pbar) {
        this.pbarMapper.insert(pbar);
        UserPbar userPbar = new UserPbar();
        userPbar.setUserId(pbar.getUserId());
        userPbar.setType(1);
        userPbar.setPbarId(pbar.getId());
        userPbar.setCreateTime(new Date());
        this.userPbarMapper.save(userPbar);
    }

    @Override
    public Pbar getPbarById(Long id) {
        return this.pbarMapper.getById(id);
    }

    @Override
    public List<UserPbarVo> getAllSelfPbar(Long userId, int page) {
        try{
            Map<String, Object> params = new HashMap<>();

            params.put("userId", userId);
            params.put("page", page);

            List<UserPbarVo> result = this.pbarMapper.getAllSelfPbar(params);

            return result.size() == 0 ? null : result;
        }catch (Exception e){
            logger.error("get self pbars error ! userId = " + userId + "&page = " + page);
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public PbarIndexVo getPbarIndex(Long id) {

        PbarIndexVo pbarIndexVo = this.pbarMapper.getPbarIndex(id);

        if(pbarIndexVo == null){
            return null;
        }

        pbarIndexVo.setTags(JSON.parseArray(pbarIndexVo.getTag(), TagsVo.class));

        if(StringUtil.isBlank(pbarIndexVo.getBackgroundLogo())){
            pbarIndexVo.setBackgroundLogo(KpicConstant.MOREN_PBAR_BACKGROUND_LOGO);
        }

        return pbarIndexVo;
    }

    @Transactional
    public void update(Pbar pbar){
        this.pbarMapper.update(pbar);
    }

    @Override
    public List<Pbar> getAllPbarFocus(Long userId, int page){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("userId", userId);
        List<Pbar> result = this.pbarFocusMapper.getAllPbarFocus(params);
        return result.size() == 0 ? null : result;
    }
}
