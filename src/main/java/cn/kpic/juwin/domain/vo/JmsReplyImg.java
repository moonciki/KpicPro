package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/3/18.
 */
public class JmsReplyImg implements java.io.Serializable{

    private String content;
    private Long topicPostid;
    private Long replyPostId;

    public JmsReplyImg(String content, Long topicPostid, Long replyPostId) {
        this.content = content;
        this.topicPostid = topicPostid;
        this.replyPostId = replyPostId;
    }

    public String getContent() {
        return content;
    }

    public Long getTopicPostid() {
        return topicPostid;
    }

    public Long getReplyPostId() {
        return replyPostId;
    }

}

