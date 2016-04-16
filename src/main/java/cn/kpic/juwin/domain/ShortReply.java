package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/3 0003.
 */
public class ShortReply {

    private Long id;

    private String content;

    private Long replyId;

    private Long userId;

    private String replyUserName;

    private Long replyUserId;

    private int isdel;

    private Date createTime;

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getContent() {
        return content;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public int getIsdel() {
        return isdel;
    }
}
