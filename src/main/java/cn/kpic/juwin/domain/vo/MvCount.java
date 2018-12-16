package cn.kpic.juwin.domain.vo;

/**
 * Created by Administrator on 2017/1/27 0027.
 */
public class MvCount implements java.io.Serializable {

    private Integer type;//1：播放量，2：收藏数，3：弹幕数

    private Integer mvId;

    private Integer slId;

    private Integer count;

    public static final Integer PLAY = 1;

    public static final Integer FAV = 2;

    public static final Integer DANMU = 3;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMvId() {
        return mvId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
    }

    public Integer getSlId() {
        return slId;
    }

    public void setSlId(Integer slId) {
        this.slId = slId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
