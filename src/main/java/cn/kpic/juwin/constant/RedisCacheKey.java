package cn.kpic.juwin.constant;

/**
 * 缓存的key常量
 * Created by bjsunqinwen on 2016/3/15.
 */
public class RedisCacheKey {

    /** 用于缓存每个圈子下的前十页帖子信息缓存，用户发帖一次失效一次*/
    public final static String PBAR_PAGE = "pbar:pbar_page";
    /** 用于缓存用户未读信息*/
    public final static String USER_NEWS = "user:news";
    /** 用于缓存圈子首页圈子信息，每隔 2 天失效一次*/
    public final static String PBAR_INDEX = "pbar:index";
    /** 用于缓存圈子管理页面对当前用户身份的缓存：大管理员&小管理员,每 1 天失效一次*/
    public final static String PBAR_USER_ROLE = "pbar:manager:role";
    /** 用于缓存用户节操值，每一周失效一次*/
    public final static String USER_JC_NUM = "user:jiecao";
    /** 用于缓存每个圈子的点击量，和定时任务相结合，每天定时更新点击量*/
    public final static String PBAR_HIT="pbar:pbar_hit";


    /** 首页缓存开始，首页各部分缓存失效时间均为1天*/
    /** 用于缓存首页圈子类型*/
    public final static String PBAR_TYPE_INDEX = "home:type_index";
    /** 用于缓存首页精彩图集*/
    public final static String ALBUM_INDEX = "home:albums";
    /** 用于缓存首页精彩圈子*/
    public final static String PBAR_INDEX_HOME = "home:pbars";
    /** 用于缓存首页热门文章、帖子*/
    public final static String POST_INDEX_HOME = "home:post";
    /** 用于缓存首页轮播图*/
    public final static String IMG_INDEX_HOME = "home:img";
    /** 用于缓存首页牛人榜*/
    public final static String USER_INDEX_HOME = "home:users";
    /** 警告榜*/
    public final static String USER_WARN_HOME = "home:warns";

}
