package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.Album;
import cn.kpic.juwin.domain.Blog;
import cn.kpic.juwin.domain.vo.BlogVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/1 0001.
 */
public interface BlogMapper {

    void save(Blog blog);

    List<Blog> gatAllBlog(Map params);

    Blog gatBlog(Long id);

    BlogVo readBlog(Long id);

    void del(Long id);

}
