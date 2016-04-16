package cn.kpic.juwin.domain;

import org.apache.commons.mail.Email;

import java.util.Date;

/**
 * Created by bjsunqinwen on 2016/2/23.
 */
public class User implements java.io.Serializable{
    private Long id;
    private String name;
    private String email;
    private String password;
    private int age;
    private String userPic;
    private Date createTime;
    private int postNum;
    private int isdel;
    private String address;
    private String tag;
    private String avater;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPic(){
        return userPic;
    }

    public void setUserPic(String userPic){
        this.userPic = userPic;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public int getIsdel() {
        return isdel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }
}
