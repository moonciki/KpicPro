package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/4/8.
 */
public class JmsUpdPbar implements java.io.Serializable {
    /** 0���޸���������1���޸Ĺ�ע��*/
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
