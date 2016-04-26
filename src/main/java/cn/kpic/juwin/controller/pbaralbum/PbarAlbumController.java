package cn.kpic.juwin.controller.pbaralbum;

import cn.kpic.juwin.domain.PbarAlbum;
import cn.kpic.juwin.domain.vo.PbarAlbumList;
import cn.kpic.juwin.service.PbarAlbumService;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/25.
 */
@Controller
public class PbarAlbumController {

    private Logger logger = Logger.getLogger(PbarAlbumController.class);

    @Autowired
    private PbarAlbumService pbarAlbumService;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/album/save")
    @ResponseBody
    public boolean save(PbarAlbum pbarAlbum){
        try{
            pbarAlbum.setCreateTime(new Date());
            this.pbarAlbumService.addPbarAlbum(pbarAlbum);
            return true;
        }catch (Exception e){
            logger.error("add pbar_album error ! pbarId = "+pbarAlbum.getPbarId()+" and albumId = "+pbarAlbum.getAlbumId());
        }
        return false;
    }

    @RequestMapping(value = "/pbar/album/list", method = RequestMethod.POST)
    @ResponseBody
    public List<PbarAlbumList> getAllAlbumListByPbarId(Long pbarId, Integer page){
        try{
            if(pbarId == null || page == null){
                return null;
            }

            List<PbarAlbumList> result = this.pbarAlbumService.getAllAlbumByPbarId(pbarId, page);

            return result;
        }catch (Exception e){
            logger.error("add pbar_album_list error ! pbarId = "+pbarId);
        }
        return null;
    }

}
