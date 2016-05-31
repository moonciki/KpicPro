package cn.kpic.juwin.domain.vo;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/3/18.
 */
public class ReplyPostList {

    private Long id;

    private String content;

    private int replyNum;

    private Long userId;

    private Date createTime;

    private String userName;

    private String userPic;

    private String avater;

    private int isjm;

    private Boolean isTip = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Boolean getIsTip() {
        return isTip;
    }

    public void setIsTip(Boolean isTip) {
        this.isTip = isTip;
    }

    public int getIsjm() {
        return isjm;
    }

    public void setIsjm(int isjm) {
        this.isjm = isjm;
    }
}
