package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/4/8.
 */
public class JmsUpdPbar implements java.io.Serializable {
    /** 0：修改帖子数；1：修改关注数*/
    private int type;
    private Long pbarId;
    private Long userId;

    public JmsUpdPbar(int type, Long pbarId, Long userId){
        this.type = type;
        this.pbarId = pbarId;
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public Long getPbarId() {
        return pbarId;
    }

    public Long getUserId() {
        return userId;
    }
}
