package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/3/2.
 */
public class Power implements java.io.Serializable{

    private Long id;

    private String name;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
