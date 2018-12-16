package cn.kpic.juwin.domain.vo;

/**
 * Created by Administrator on 2017/1/20 0020.
 */
public class DamakuVo implements java.io.Serializable{

    private String text;
    private String color;
    private int size;
    private int position;
    private int time;
    private Integer mvId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Integer getMvId() {
        return mvId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
    }
}
