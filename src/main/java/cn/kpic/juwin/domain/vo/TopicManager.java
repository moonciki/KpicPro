package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/4/12.
 */
public class TopicManager {

    private Long id;

    private String name;

    private String userPic;

    private String avater;

    private int level;

    private int score;

    private int isjm;

    private Long thisId;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIsjm() {
        return isjm;
    }

    public void setIsjm(int isjm) {
        this.isjm = isjm;
    }

    public Long getThisId() {
        return thisId;
    }

    public void setThisId(Long thisId) {
        this.thisId = thisId;
    }
}
