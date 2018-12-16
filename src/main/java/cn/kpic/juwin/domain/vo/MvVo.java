package cn.kpic.juwin.domain.vo;

import cn.kpic.juwin.domain.Mv;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/26 0026.
 */
public class MvVo extends Mv implements java.io.Serializable{

    private int sex;

    private String userPic;

    private String avater;

    private String name;

    private String tag;

    private Boolean isFav;

    private String slTitle;

    private Integer slId;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }

    public String getSlTitle() {
        return slTitle;
    }

    public void setSlTitle(String slTitle) {
        this.slTitle = slTitle;
    }

    public Integer getSlId() {
        return slId;
    }

    public void setSlId(Integer slId) {
        this.slId = slId;
    }
}