package cn.kpic.juwin.domain;

/**
 * Created by bjsunqinwen on 2016/3/2.
 */
public class UserPower implements java.io.Serializable{

    private Long id;

    private Long userId;

    private Long powerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }
}
