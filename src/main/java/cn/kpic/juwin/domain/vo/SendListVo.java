package cn.kpic.juwin.domain.vo;
import cn.kpic.juwin.domain.SendList;

/**
 * Created by Administrator on 2017/1/26 0026.
 */
public class SendListVo extends SendList {

    private int sex;

    private String userPic;

    private String avater;

    private String name;

    private String tag;

    private Integer fav;

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

    public Integer getFav() {
        return fav;
    }

    public void setFav(Integer fav) {
        this.fav = fav;
    }
}
