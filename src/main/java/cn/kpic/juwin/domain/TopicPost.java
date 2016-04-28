package cn.kpic.juwin.domain;

import java.util.Date;

/**
 * 主题帖
 * Created by bjsunqinwen on 2016/3/9.
 */
public class TopicPost implements java.io.Serializable{

    /** 主题帖恢复*/
    public static final int TOPIC_POST_DEL_CANCEL = 0;
    /** 主题帖删除*/
    public static final int TOPIC_POST_DEL = 1;
    /** 取消置顶*/
    public static final int TOPIC_POST_ISTOP_CANCEL = 0;
    /** 设为置顶贴*/
    public static final int TOPIC_POST_ISTOP = 1;
    /** 取消加精*/
    public static final int TOPIC_POST_ISBOUTIQUE_CANCEL = 0;
    /** 设为精品贴*/
    public static final int TOPIC_POST_ISBOUTIQUE = 1;

    private Long id;

    private String uid;

    private String title;

    private String content;

    private String shortText;

    private Integer replyNum;

    private Integer isTop;

    private Integer isBoutique;

    private Integer isdel;

    private Integer num1;

    private Integer num2;

    private Integer num;

    private Long pbarId;

    private Long userId;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsBoutique() {
        return isBoutique;
    }

    public void setIsBoutique(Integer isBoutique) {
        this.isBoutique = isBoutique;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPbarId() {
        return pbarId;
    }

    public void setPbarId(Long pbarId) {
        this.pbarId = pbarId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
