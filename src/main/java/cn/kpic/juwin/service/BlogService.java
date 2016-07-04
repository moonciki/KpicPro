package cn.kpic.juwin.service;

import cn.kpic.juwin.domain.Blog;
import cn.kpic.juwin.domain.vo.BlogVo;

/**
 * Created by Administrator on 2016/5/1 0001.
 */
public interface BlogService {

    void saveBlog(Blog blog);

    void delBlog(Long id);

}
