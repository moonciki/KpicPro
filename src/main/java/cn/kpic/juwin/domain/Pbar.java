package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/3/22.
 * 圈子实体类
 */
public class Pbar {

    private Long id;

    private String name;

    private String msg;

    private String tags;

    private Integer type;

    private String logo;

    private String background_logo;

    private Integer isdel;

    private Integer topic_num;

    private Integer focus_num;

    private Long userId;

    private Integer is_index;

    private String color;

    /** 0：正在审核中  1：审核通过  2：关闭  3：封禁*/
    private Integer ispass;

    private Date create_time;

    private Date update_time;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackground_logo() {
        return background_logo;
    }

    public void setBackground_logo(String background_logo) {
        this.background_logo = background_logo;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getTopic_num() {
        return topic_num;
    }

    public void setTopic_num(Integer topic_num) {
        this.topic_num = topic_num;
    }

    public Integer getFocus_num() {
        return focus_num;
    }

    public void setFocus_num(Integer focus_num) {
        this.focus_num = focus_num;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIs_index() {
        return is_index;
    }

    public void setIs_index(Integer is_index) {
        this.is_index = is_index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIspass() {
        return ispass;
    }

    public void setIspass(Integer ispass) {
        this.ispass = ispass;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
