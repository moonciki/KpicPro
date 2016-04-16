package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/4/7.
 */
public class UserPbarVo {

    private Long id;

    private Long pbarId;

    private String name;

    private String color;

    private String type;

    private int ispass;

    private int role;

    public Long getPbarId() {
        return pbarId;
    }

    public void setPbarId(Long pbarId) {
        this.pbarId = pbarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIspass() {
        return ispass;
    }

    public void setIspass(int ispass) {
        this.ispass = ispass;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
