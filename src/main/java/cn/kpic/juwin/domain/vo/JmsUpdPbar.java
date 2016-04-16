package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/4/8.
 */
public class JmsUpdPbar implements java.io.Serializable {
    /** 0：修改帖子数；1：修改关注数*/
    private int type;
    private Long pbarId;

    public JmsUpdPbar(int type, Long pbarId){
        this.type = type;
        this.pbarId = pbarId;
    }

    public int getType() {
        return type;
    }

    public Long getPbarId() {
        return pbarId;
    }

}
