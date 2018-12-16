package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * 弹幕
 * Created by Administrator on 2017/1/21 0021.
 */
public class Damaku implements java.io.Serializable {

    private Integer id;

    private String text;
    private String color;
    private int size;
    private int position;
    private int time;

    private Long userId;
    private Integer mvId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMvId() {
        return mvId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
