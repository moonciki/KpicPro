package cn.kpic.juwin.jms.listener;

import cn.kpic.juwin.constant.RedisCacheKey;
import cn.kpic.juwin.domain.Blog;
import cn.kpic.juwin.domain.TopicPost;
import cn.kpic.juwin.domain.vo.JmsPublishBlog;
import cn.kpic.juwin.mapper.BlogMapper;
import cn.kpic.juwin.mapper.TopicPostMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/1 0001.
 */
@Service
@Transactional
public class PublishBlogQueueMessageListener implements MessageListener{

    private Logger logger = Logger.getLogger(PublishBlogQueueMessageListener.class);

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage)message;
        try{
            JmsPublishBlog jmsPublishBlog = (JmsPublishBlog)objectMessage.getObject();
            if(jmsPublishBlog != null){

                Blog blog = this.blogMapper.gatBlog(jmsPublishBlog.getBlogId());

                if(blog != null){

                    Date timer = new Date();
                    TopicPost topicPost = new TopicPost();
                    topicPost.setTitle(blog.getTitle());
                    topicPost.setContent(blog.getContent());
                    topicPost.setCreateTime(timer);
                    topicPost.setPbarId(jmsPublishBlog.getPbarId());
                    topicPost.setShortText(blog.getShortContent());
                    topicPost.setUpdateTime(timer);
                    topicPost.setUserId(blog.getUserId());
                    topicPost.setIsBlog(1);
                    topicPost.setIsBoutique(0);
                    topicPostMapper.save(topicPost);

                    /** Çå»º´æ*/
                    String key = RedisCacheKey.PBAR_PAGE + jmsPublishBlog.getPbarId();
                    redisTemplate.delete(key);

                }



            }
        }catch (JMSException e){
            logger.error("forJMS : update pbar somthing error ! "+e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            logger.error("forJMS : update pbar somthing error ! ");
            e.printStackTrace();
        }
    }
}
