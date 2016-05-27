package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
public class JmsUserIntegrityUpd implements java.io.Serializable {

    private Long userId;

    private Integer num;

    private Integer type;//1:增加节操  2:减少节操

    public JmsUserIntegrityUpd(Long userId, Integer num, Integer type){
        this.userId = userId;
        this.num = num;
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getNum() {
        return num;
    }

    public Integer getType() {
        return type;
    }
}
