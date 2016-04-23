package cn.kpic.juwin.utils;

import cn.kpic.juwin.domain.vo.Songs;
import cn.kpic.juwin.http.HttpRequest;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjsunqinwen on 2016/4/22.
 */
public class NetEaseMusicUtils {

    public static List<Songs> getSearchResult(String key_word) throws Exception{
        if(key_word == "" || key_word == null){
            return null;
        }
        String new_word = URLEncoder.encode(key_word,"utf8");
        //∑¢ÀÕ POST «Î«Û
        String sr= HttpRequest.sendPost("http://music.163.com/api/search/pc", "s=" + new_word + "&offset=0&limit=5&type=1");

        String code = sr.substring(sr.indexOf("\"code\":") + 7, sr.length() - 1);
        if("200".equals(code)){
            String songCount = sr.substring(sr.indexOf("\"songCount\":")+12, sr.length()-13);

            List<Songs> lists = new ArrayList<>();

            if(Integer.parseInt(songCount) > 0){
                String[] jp = sr.split("\"mp3Url\":\"");
                for(int i = 0; i < jp.length; i++){
                    if(i==0){
                        continue;
                    }
                    Songs songs = new Songs();
                    songs.setMp3Url(jp[i].substring(0,jp[i].indexOf(".mp3") + 4));
                    lists.add(songs);
                }


                return lists.size() == 0 ? null : lists;


            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}
