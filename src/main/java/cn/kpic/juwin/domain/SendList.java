package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/26 0026.
 */
public class SendList implements java.io.Serializable{

    private Integer id;

    private String title;

    private String cover;

    private String decri;

    private Integer damaku;

    private Integer play;

    private Integer fav;

    private Integer damakuPool;

    private Long userId;

    private Integer isdel;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDecri() {
        return decri;
    }

    public void setDecri(String decri) {
        this.decri = decri;
    }

    public Integer getDamaku() {
        return damaku;
    }

    public void setDamaku(Integer damaku) {
        this.damaku = damaku;
    }

    public Integer getPlay() {
        return play;
    }

    public void setPlay(Integer play) {
        this.play = play;
    }

    public Integer getDamakuPool() {
        return damakuPool;
    }

    public void setDamakuPool(Integer damakuPool) {
        this.damakuPool = damakuPool;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getFav() {
        return fav;
    }

    public void setFav(Integer fav) {
        this.fav = fav;
    }
}
