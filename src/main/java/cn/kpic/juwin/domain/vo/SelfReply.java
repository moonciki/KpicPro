package cn.kpic.juwin.domain.vo;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/2 0002.
 */
public class SelfReply {

    private Long replyId;

    private Long topicId;

    private String title;

    private String shortText;

    private Date createTime;

    private int replyNum;

    private String pbarName;

    private String color;

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getReplyNum() {
        return replyNum;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setPbarName(String pbarName) {
        this.pbarName = pbarName;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getColor() {
        return color;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public String getPbarName() {
        return pbarName;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
