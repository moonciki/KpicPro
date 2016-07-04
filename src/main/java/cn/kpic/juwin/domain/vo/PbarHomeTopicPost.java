package cn.kpic.juwin.domain.vo;

import cn.kpic.juwin.domain.TopicImg;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
public class PbarHomeTopicPost {

    private Long id;

    private String uid;

    private String title;

    private String shortText;

    private int replyNum;

    private int isTop;

    private int isBoutique;

    private String userName;

    private Date createTime;

    private Date updateTime;

    private List<TopicImg> img;

    private String userPic;

    private int isBlog;

    private String avater;

    private String pbarName;

    private String color;

    private String num;

    private int isjm;

    private Long userId;

    private Long pbarId;

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

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getIsBoutique() {
        return isBoutique;
    }

    public void setIsBoutique(int isBoutique) {
        this.isBoutique = isBoutique;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<TopicImg> getImg() {
        return img;
    }

    public void setImg(List<TopicImg> img) {
        this.img = img;
    }

    public String getUserPic(){
        return userPic;
    }

    public void setUserPic(String userPic){
        this.userPic = userPic;
    }

    public int getIsBlog() {
        return isBlog;
    }

    public void setIsBlog(int isBlog) {
        this.isBlog = isBlog;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getPbarName() {
        return pbarName;
    }

    public void setPbarName(String pbarName) {
        this.pbarName = pbarName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getIsjm() {
        return isjm;
    }

    public void setIsjm(int isjm) {
        this.isjm = isjm;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPbarId() {
        return pbarId;
    }

    public void setPbarId(Long pbarId) {
        this.pbarId = pbarId;
    }
}
