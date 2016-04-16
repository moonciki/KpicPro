package cn.kpic.juwin.utils;

import java.util.UUID;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
public class GetUuid {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
