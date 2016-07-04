package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * 用户节操值
 * Created by bjsunqinwen on 2016/5/27.
 */
public class UserIntegrity {

    private Long id;

    private Long userId;

    private Integer num;

    private Date updateTime;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
