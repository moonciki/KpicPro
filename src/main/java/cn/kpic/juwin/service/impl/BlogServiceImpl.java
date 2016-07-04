package cn.kpic.juwin.service.impl;

import cn.kpic.juwin.domain.Blog;
import cn.kpic.juwin.mapper.BlogMapper;
import cn.kpic.juwin.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/5/1 0001.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void saveBlog(Blog blog) {
        this.blogMapper.save(blog);
    }

    @Override
    public void delBlog(Long id) {
        this.blogMapper.del(id);
    }
}
