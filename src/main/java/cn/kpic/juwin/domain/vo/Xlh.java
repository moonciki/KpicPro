package cn.kpic.juwin.domain.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/3/19 0019.
 * 该类仅仅为了fastjson的序列化而写（真·尼玛蛋疼）
 */
public class Xlh implements java.io.Serializable{

    private List list;

    public List getList(){
        return list;
    }

    public void setList(List list){
        this.list = list;
    }

}
