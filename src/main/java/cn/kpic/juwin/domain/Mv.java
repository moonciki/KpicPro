package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * MV
 * Created by Administrator on 2017/1/21 0021.
 */
public class Mv implements java.io.Serializable {

    private Integer id;
    private String title;
    private Integer type;
    private Integer isdel;
    private String cover;
    private String path;
    private String decri;
    private String songer;
    private Integer damaku;
    private Integer fav;
    private Integer play;
    private Long userId;
    private Long topicId;
    private Integer damakuPool;
    private Integer sendlistId;
    private Integer sourceType;//1:QQ,2:wyy,3:baidu,4:xiami
    private String sourcePath;
    private Date createTime;
    private Date updateTime;

    public static final int MUSIC = 0;
    public static final int VIDEO = 1;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDecri() {
        return decri;
    }

    public void setDecri(String decri) {
        this.decri = decri;
    }

    public String getSonger() {
        return songer;
    }

    public void setSonger(String songer) {
        this.songer = songer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSendlistId() {
        return sendlistId;
    }

    public void setSendlistId(Integer sendlistId) {
        this.sendlistId = sendlistId;
    }

    public Integer getFav() {
        return fav;
    }

    public void setFav(Integer fav) {
        this.fav = fav;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
