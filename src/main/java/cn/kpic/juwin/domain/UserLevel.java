package cn.kpic.juwin.domain;

/**
 * Created by Administrator on 2016/3/27 0027.
 */
public class UserLevel {

    private Long id;
    private Long userId;
    private int level;
    private int score;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
