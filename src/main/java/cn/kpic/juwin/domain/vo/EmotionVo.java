package cn.kpic.juwin.domain.vo;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class EmotionVo {

    private Integer id;

    private String title;

    private String url;

    private Long userId;

    private String userName;

    private Boolean isStored = false;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean isStored() {
        return isStored;
    }

    public void setIsStored(Boolean isStored) {
        this.isStored = isStored;
    }
}
