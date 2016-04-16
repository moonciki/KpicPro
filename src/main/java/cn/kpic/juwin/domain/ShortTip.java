package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/4/11.
 */
public class ShortTip {

    private Long id;

    private Long userId;

    private Long shortId;

    private String title;

    private String msg;

    private Long pbarId;

    private Date createTime;

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

    public Long getShortId() {
        return shortId;
    }

    public void setShortId(Long shortId) {
        this.shortId = shortId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getPbarId() {
        return pbarId;
    }

    public void setPbarId(Long pbarId) {
        this.pbarId = pbarId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
