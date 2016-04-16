package cn.kpic.juwin.domain.vo;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/3 0003.
 */
public class ShortReplyVo {

    private Long shortId;

    private String content;

    private String replyUserName;

    private String replyUserId;

    private Long userId;

    private Long replyId;

    private String userPic;

    private String avater;

    private Date createTime;

    private String userName;

    private Boolean isTip = false;

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setShortId(Long shortId) {
        this.shortId = shortId;
    }

    public Long getShortId() {
        return shortId;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getAvater() {
        return avater;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserPic() {
        return userPic;
    }

    public Boolean getIsTip() {
        return isTip;
    }

    public void setIsTip(Boolean isTip) {
        this.isTip = isTip;
    }
}
