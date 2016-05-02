package cn.kpic.juwin.controller.blog;

import cn.kpic.juwin.domain.Album;
import cn.kpic.juwin.domain.Blog;
import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.JmsPublishBlog;
import cn.kpic.juwin.jms.sender.PublishBlogQueueMessageSender;
import cn.kpic.juwin.mapper.BlogMapper;
import cn.kpic.juwin.service.BlogService;
import cn.kpic.juwin.utils.CurrentUser;
import cn.kpic.juwin.utils.StringDeal;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/1 0001.
 */
@Controller
public class BlogController {

    private Logger logger = Logger.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private PublishBlogQueueMessageSender publishBlogQueueMessageSender;

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/make/blog")
    public String makeBlog(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            model.addAttribute("flag",30);
            return "/user/make_blog";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/save/blog")
    @ResponseBody
    public Boolean save(String title, String content, String shortContent){
        User curr_user = CurrentUser.getUser();
        try{

            Blog blog = new Blog();

            blog.setCreateTime(new Date());
            blog.setUserId(curr_user.getId());
            blog.setContent(content);
            blog.setIsdel(0);
            blog.setUpdateTime(new Date());
            blog.setTitle(title);
            blog.setShortContent(StringDeal.getText(shortContent));

            this.blogService.saveBlog(blog);

            return true;

        }catch (Exception e){
            logger.error("save blog error! userId = "+curr_user.getId());
            e.printStackTrace();
            return false;
        }
    }



    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/list/blog", method = RequestMethod.GET)
    public String getAllAlbum(Model model){
        User curr_user = CurrentUser.getUser();
        try{
            model.addAttribute("user", curr_user);
            model.addAttribute("flag",29);
            return "/user/blog_list";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/404";
    }

    @RequiresPermissions({"user"})
    @RequestMapping(value = "/user/list/blog", method = RequestMethod.POST)
    @ResponseBody
    public List<Blog> getAllAlbum2(@RequestParam(value = "page", defaultValue = "0",required = false)int page){
        User curr_user = CurrentUser.getUser();
        try{
            Map params = new HashMap();
            params.put("page", page*10);
            params.put("userId", curr_user.getId());
            List<Blog> result = this.blogMapper.gatAllBlog(params);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @RequiresPermissions({"user"})
    @RequestMapping(value = "/pbar/blog/save")
    @ResponseBody
    public boolean publishBlog(Long pbarId, Long blogId){
        try{
            if(pbarId == null || blogId == null){
                return false;
            }
            JmsPublishBlog jmsPublishBlog = new JmsPublishBlog();
            jmsPublishBlog.setPbarId(pbarId);
            jmsPublishBlog.setBlogId(blogId);
            this.publishBlogQueueMessageSender.send(jmsPublishBlog);
            return true;
        }catch (Exception e){
            logger.error("blog published error! pbarId = "+pbarId +"&blogId = "+blogId);
            e.printStackTrace();
            return false;
        }
    }

}
