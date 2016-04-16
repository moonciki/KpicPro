package cn.kpic.juwin.utils;

import org.jsoup.Jsoup;

/**
 * Created by bjsunqinwen on 2016/3/10.
 */
public class StringDeal {
    public static String shutString(String str, int limit){
        if(str.length() > limit){
            str = str.substring(0, limit);
        }
        return str;
    }

    public static String removeQuestionMark(String str){
        if(str.contains("?")){
            str = str.substring(0, str.indexOf("?"));
        }
        return str;
    }

    public static String getText(String str){
        return Jsoup.parse(str).text();
    }
}
