package cn.kpic.juwin.domain.vo;

import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/8.
 */
public class PbarIndexVo {

    private Long id;

    private String name;

    private int topicNum;

    private String tag;

    private List<TagsVo> tags;

    private Long typeId;

    private String type;

    private String msg;

    private int focusNum;

    private Long userId;

    private String backgroundLogo;

    private String logo;

    private String color;

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

    public int getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }

    public List<TagsVo> getTags() {
        return tags;
    }

    public void setTags(List<TagsVo> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFocusNum() {
        return focusNum;
    }

    public void setFocusNum(int focusNum) {
        this.focusNum = focusNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBackgroundLogo() {
        return backgroundLogo;
    }

    public void setBackgroundLogo(String backgroundLogo) {
        this.backgroundLogo = backgroundLogo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
