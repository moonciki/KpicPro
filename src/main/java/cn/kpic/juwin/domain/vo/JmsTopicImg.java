package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/3/10.
 * 利用jms消息异步更新帖子图片信息记录
 * 由于经过队列工具传输，因此需要序列化
 */
public class JmsTopicImg implements java.io.Serializable{

    private String content;
    private Long topicPostid;

    public JmsTopicImg(String content, Long topicPostid){

        this.content = content;
        this.topicPostid = topicPostid;

    }

    public String getContent() {
        return content;
    }

    public Long getTopicPostid() {
        return topicPostid;
    }
}
