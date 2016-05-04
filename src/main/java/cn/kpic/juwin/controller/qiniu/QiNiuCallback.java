package cn.kpic.juwin.controller.qiniu;

import cn.kpic.juwin.qiniu.img.Base64Coder;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjsunqinwen on 2016/3/9.
 */
@Controller
public class QiNiuCallback {

    private static Logger logger = Logger.getLogger(QiNiuCallback.class);

    @RequestMapping("/qiniuimgcallback")
    @ResponseBody
    public String qiNiuCallback(HttpServletRequest request){
        JSONObject json = new JSONObject();
        try{
            request.setCharacterEncoding("utf-8");
            String upload_ret = request.getParameter("upload_ret");
            JSONObject callback = new JSONObject(Base64Coder.decode(upload_ret));
            if (callback.has("error")) {
                json.put("state", callback.get("error"));
            } else {
                json.put("original", callback.get("name"));
                int width = Integer.parseInt((String)callback.get("w"));
                if(width > 677){
                    json.put("url", callback.get("key")+"?imageView2/2/w/682/q/95");
                }else{
                    json.put("url", callback.get("key"));
                }
                json.put("state", "SUCCESS");
            }
        }catch (Exception e){
            logger.error("from : QiNiuCallBack qiniu post upload img error !\n" + e.getMessage());
            e.printStackTrace();
        }
        return json.toString();
    }

    @RequestMapping("/qiniuimgcallback2")
    @ResponseBody
    public Map<String, Object> qiNiuCallback2(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("ss", "hh");
        map.put("bb", "ll");
        return map;
    }

}
