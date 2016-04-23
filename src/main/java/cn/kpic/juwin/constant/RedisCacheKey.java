package cn.kpic.juwin.constant;

/**
 * Created by bjsunqinwen on 2016/3/15.
 */
public class RedisCacheKey {

    /** 用于缓存每个话题下的前十页帖子信息缓存，用户发帖一次失效一次*/
    public final static String PBAR_PAGE = "pbar:pbar_page";
    /** 用于缓存用户未读信息*/
    public final static String USER_NEWS = "user:news";
    /** 用于缓存话题首页话题信息，每隔 2 天失效一次*/
    public final static String PBAR_INDEX = "pbar:index";
    /** 用于缓存话题管理页面对当前用户身份的缓存：大管理员&小管理员,每 1 天失效一次*/
    public final static String PBAR_USER_ROLE = "pbar:manager:role";

}
