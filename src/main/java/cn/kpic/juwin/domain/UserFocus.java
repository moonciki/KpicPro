package cn.kpic.juwin.domain;

/**
 * Created by Administrator on 2016/3/27 0027.
 */
public class UserFocus {

    private Long id;
    /** 被关注者*/
    private Long userId1;
    /** 关注者*/
    private Long userId2;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId1(Long userId1) {
        this.userId1 = userId1;
    }

    public Long getUserId1() {
        return userId1;
    }

    public void setUserId2(Long userId2) {
        this.userId2 = userId2;
    }

    public Long getUserId2() {
        return userId2;
    }
}
