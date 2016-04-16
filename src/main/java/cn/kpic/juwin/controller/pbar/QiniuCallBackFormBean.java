package cn.kpic.juwin.controller.pbar;

/**
 * Created by bjsunqinwen on 2016/3/23.
 */
public class QiniuCallBackFormBean {

    private String key;

    private String hash;

    private String name;

    private long size;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
