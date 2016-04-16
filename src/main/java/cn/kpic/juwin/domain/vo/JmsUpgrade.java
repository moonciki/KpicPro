package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/3/28.
 */
public class JmsUpgrade implements java.io.Serializable{

    private Long userId;
    private int addscore;

    public JmsUpgrade(Long userId, int addscore){
        this.userId = userId;
        this.addscore = addscore;
    }

    public Long getUserId() {
        return userId;
    }

    public int getAddscore() {
        return addscore;
    }

}
