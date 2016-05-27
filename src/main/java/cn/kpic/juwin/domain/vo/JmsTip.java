package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
public class JmsTip implements java.io.Serializable {

    private Long id;

    private Integer type;

    public JmsTip(Long id, Integer type){
        this.id = id;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public Long getId() {
        return id;
    }
}
