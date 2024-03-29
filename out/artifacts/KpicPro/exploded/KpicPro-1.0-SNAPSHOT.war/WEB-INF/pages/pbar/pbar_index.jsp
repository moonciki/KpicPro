<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/2/28 0028
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>圈子--${pbar.name}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/pbar/pbar_index.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
</head>
<body>
<input type="hidden" value="${pbar.userId}" id="pbar_user_id"/>
<div class="pic_big" id="tp_video">
    <div class="pic_big_01" style="background-color: black; display: none">
        <span id="pic_video"></span><div class="pic_big_01_01" title="关闭" onclick="clearAll()"></div>
    </div>
    <div class="pic_big_02">
        <span id="pic_pic"></span>
    </div>
    <span id="music_bf"></span>
</div>

<div class="pic_big" id="tp_eif">
    <center>
        <div class="eif">
            <div class="eif_top">表情包-------<a href="${pageContext.request.contextPath}/user/emotion/save" target="_blank">上传表情</a>
                <span class="badge pull-right" style="font-weight:bold;background-color: #FF2D2D;cursor: pointer;font-size: 20px" onclick="eif_close()" title="关闭">×</span>
            </div>

            <div class="eif_content">
                <span id="eif_content">

                </span>
            </div>



            <br/>

            <input type="hidden" id="page_eif" value="0"/>

            <center>
                <button type="button" id="jz" class="btn btn-info">加载更多</button>
                <br/>
                <span id="load" style="display: none">加载中...</span>
                <span id="no_data" style="display: none">没有更多了</span>
            </center>
        </div>
    </center>
</div>
<input type="hidden" id="jc" value="${jc}"/>
<div class="pic_big" id="pl_sx">
    <div class="pic_big_tip">
        <span style="font-size: 16px;font-family: 微软雅黑"><b>私信发送</b></span>
        <br/><br/>
        <span style="color:#0055aa;font-weight: bold">想对TA说点什么？</span>
        <textarea id="sx_msg" class="form-control" placeholder="请输入私信内容，不能为空，且不超过100个字符~" rows="5"></textarea>
        <br/>
        <button type="button" class="btn btn-primary" id="sx_submit">发送</button>
        <button type="button" class="btn btn-primary" id="sx_close">取消</button>
        &nbsp;&nbsp;<span id="sx_load" style="display: none; color:#9D9D9D; font-size: 14px"><img src="http://7xwibn.com1.z0.glb.clouddn.com/static/loading.jpg" style="width: 30px; height: 30px"/>&nbsp;提交中..</span>
    </div>
</div>
<%@include file="../common/top.jsp" %>
<div class="top_blank"></div>
<div class="main">

<%@include file="../common/pbar.jsp" %>
    
    <%@include file="../common/share_media.jsp"%>

<div class="mane">
    <div class="topbar">
        <div class="wraper">

            <div class="nav">
                <ul class="parent" style="font-family: 微软雅黑;font-size: 16px">
                    <li style="cursor:pointer;" class="current" id="home">
                        <span style="color:#00BB00" class="glyphicon glyphicon-home"></span>
                        &nbsp;&nbsp;首页
                        <span class="lines"></span>
                    </li>
                    <li style="cursor:pointer;" id="onlyPost">
                        <span class="glyphicon glyphicon-th-list" style="color:#FF5809"></span>
                        &nbsp;&nbsp;只看帖子
                        <span class="lines"></span>
                    </li>
                    <li style="cursor:pointer;" id="onlyBlog">
                        <span class="glyphicon glyphicon-book" style="color: #D9B300;"></span>
                        &nbsp;&nbsp;只看文章
                        <span class="lines"></span>
                    </li>
                    <li style="cursor:pointer;" id="jp">
                        <span class="glyphicon glyphicon-star-empty" style="color: #FF60AF;font-size: 18px"></span>
                        &nbsp;&nbsp;精品
                        <span class="lines"></span>
                    </li>
                    <li style="cursor:pointer;" id = "ytj">
                        <span class="glyphicon glyphicon-picture" style="color: #2894FF"></span>
                        &nbsp;&nbsp;云图集
                        <span class="lines"></span>
                    </li>
                    <li style="cursor:pointer;" id = "tcy">
                        <span class="glyphicon glyphicon-tags" style="color: #AD5A5A"></span>
                        &nbsp;&nbsp;圈子成员
                        <span class="lines"></span>
                    </li>
                </ul>
                <!-- JiaThis Button BEGIN -->
                <span style="font-weight: bold;color:#FF359A;font-size: 14px;">分享到：</span>
                <div class="jiathis_style" style="display: inline-block;margin-top: 20px">
                    <a class="jiathis_button_qzone" href="http://www.baidu.com"></a>
                    <a class="jiathis_button_tsina"></a>
                    <a class="jiathis_button_weixin"></a>
                    <a class="jiathis_button_tieba"></a>
                </div>
                <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
                <!-- JiaThis Button END -->
            </div>
        </div>
    </div>
</div>

    <div class="main">
        <div class="main01">

            <span id = "topic_post_area">
                <div class="main01_01_no_data">
                    <br/><br/><br/>
                    <div class="loading"></div>
                </div>
            </span>
            <div class="main01_bw" style="padding-top: 25px;text-align: center">
                <button type="button" id="xl" class="btn btn-success"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
                <button type="button" style="display: none" id="ytj_xl" class="btn btn-danger"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
                <button type="button" style="display: none" id="tcy_xl" class="btn btn-danger"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>

            <div style="display: none" id="pageloading"><br/><img src="http://7xwibn.com1.z0.glb.clouddn.com/static/page_loading.gif"/></div>
            <div style="display: none" id="pageloading2" style="font-size: 14px;"><br/>没有更多了</div></div>
            <div style="display: none">
                <input id="pbarId" type="hidden" value="${pbar.id}"/>
                <input id="userId" type="hidden" value="${user.id}"/>
                <input id="sx_userId" type="hidden" value=""/>
                <input id="userName" type="hidden" value="${user.name}"/>
                <c:choose>
                    <c:when test="${user.avater != null }">
                        <input id="userPic" type="hidden" value="${user.avater}?imageView2/1/w/70/h/70/interlace/0/q/95"/>
                    </c:when>
                    <c:otherwise>
                        <input id="userPic" type="hidden" value="${user.userPic}?size=70"/>
                    </c:otherwise>
                </c:choose>
                <input id="isBlog" type="hidden" value="">
                <input id="page" type="hidden" value="0">
                <input id="isBoutique" type="hidden" value=""/>
            </div>

            <!--发帖子-->
            <div class="main01_01_reply" style="padding: 50px">
                <c:choose>
                    <c:when test="${user != null}">
                        <c:choose>
                            <c:when test="${jc <= 28}">
                                <center>由于您的节操值低于<span style="color:#CD3333;font-weight: bold">28</span>，当前处于<span style="color:#CD3333;font-weight: bold">禁言</span>状态，无法发表主题帖</center>
                            </c:when>
                            <c:otherwise>
                                <div class="bjq_btn music_button" id="music_share"></div>
                                <div class="bjq_btn video_button" id="video_share"></div>
                                <div class="bjq_btn eif_button" id="eif_share"></div>

                                <div class="xiu" style="font-size: 66px; font-weight:bold; font-family: 微软雅黑">咻~ 已发射！</div>
                                <div class="reply_loading"><div class="loading" style="margin-top:50px;padding-top: 120px;font-family: 微软雅黑;">发送中...</div></div>
                                <span style="font-size: 15px;font-family: 微软雅黑"><span class="glyphicon glyphicon-pencil" style="color:#FF79BC"></span>&nbsp;&nbsp;发表主题帖</span>
                                <br/><br/>
                                <input type="text" class="form-control" style="width: 722px" id="title" placeholder="请输入标题，不能为空，且不少于5个字符，不超过36个字符">
                                <br/>
                                <!--style给定宽度可以影响编辑器的最终宽度-->
                                <script type="text/plain" id="myEditor" name="content" style="width:722px;height:240px;"></script>
                                <br>
                                <button type="button" id="savePost" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;发射~</button>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <center>您当前还没有登录哦，<a href="${pageContext.request.contextPath}/index" target="_blank">登录</a>后才能发帖~</center>
                    </c:otherwise>
                </c:choose>
            </div>





            <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/pbar/pbar_index.js"></script>

        </div>
        <div class="main02">
            <div class="main02_01" id = "dl_head">

                <c:choose>
                    <c:when test="${user == null}">
                        <script type="text/javascript">
                            $("#dl_head").hide();
                        </script>
                    </c:when>
                    <c:otherwise>
                        <div class="main02_01_01">
                            <c:choose>
                                <c:when test="${user.avater != null }">
                                    <img src="${user.avater}?imageView2/1/w/100/h/100/interlace/0/q/95" class="userbigpic"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="${user.userPic}?size=100" class="userbigpic"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="main02_01_02" style="font-family: 微软雅黑; color:#8E8E8E">
                            <div class="main02_01_02_01">

                                <span class="glyphicon glyphicon-user" style="color:#00CACA"></span>
                                昵称：<b>${user.name}</b>
                                &nbsp;&nbsp;&nbsp;
                                <span class="glyphicon glyphicon-check" style="color:#FF8000"></span>
                                级别：<b><span id="user_level_add"></span> 级</b>

                            </div>
                            <div class="main02_01_02_02">
                                <span style="color:#FFC1E0">————————————————</span><br/>
                                <c:choose>
                                    <c:when test="${role != null and role == 1}">
                                        <span class="glyphicon glyphicon-record" style="color:#FF79BC"></span>
                                        身份：<span class="badge">大管理猿</span>
                                    </c:when>
                                    <c:when test="${role != null and role == 2}">
                                        <span class="glyphicon glyphicon-record" style="color:#FF79BC"></span>
                                        身份：<span class="badge">小管理猿</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-record" style="color:#FF79BC"></span>
                                        身份：<span class="badge">非管理猿</span>
                                    </c:otherwise>
                                </c:choose>
                                &nbsp;&nbsp;
                                <button type="button" onclick="window.open('/user/u6514${user.id}/index.html')" class="btn btn-default btn-xs" style="border: 1px solid #ff81b1; color:#ff81b1"><span class="glyphicon glyphicon-home"></span> 个人首页</button>

                                <br/><br/>
                                <span class="glyphicon glyphicon-signal" style="color:#FF79BC"></span>&nbsp;<b>升级信息</b>
                                <br/>

                                <div class="panel panel-default" style="width: 93%">
                                    <div class="panel-body">
                                        <span id="user_level"></span>
                                    </div>
                                </div>
                                </div>

                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="main02_02">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <span style="color:#8E8E8E; font-size: 14px;font-weight:bold;font-family: 微软雅黑"><span class="glyphicon glyphicon-user" style="color:${pbar.color};"></span>&nbsp;本圈子所属人（大管理猿）</span>
                        <span></span>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <span id="big_manager"></span>
                    </div>
                </div>
            </div>
            <div class="main02_02">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <span style="color:#8E8E8E; font-size: 14px;font-weight:bold;font-family: 微软雅黑"><span class="glyphicon glyphicon-user" style="color:${pbar.color};"></span>&nbsp;本圈子下的的小管理猿们</span>
                        <span></span>
                    </div>
                </div>

                <span id="manager_small_area">
                    <div class="panel panel-default" id="no-manager" style="display: none">
                        <div class="panel-body">
                            <span style="color:#FF79BC; font-size: 10px;font-family: 微软雅黑"><span class="glyphicon glyphicon-info-sign" style="color:${pbar.color};"></span>&nbsp;本圈子下还没有小管理猿 ￣ε￣</span>
                        </div>
                    </div>
                </span>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <span id="small_manager_num"></span>
                    </div>
                </div>

                <c:choose>
                    <c:when test="${user.id == pbar.userId}">

                    </c:when>
                    <c:otherwise>
                <div class="panel panel-default" id="sq-manager" style="display: none">

                    <div class="panel-body" style="text-align: center">
                        <span id="sqan"><button type="button" onclick="sq()" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span>&nbsp;申请管理猿</button></span>
                        <span id="shzan" style="display: none"><button type="button" class="btn btn-warning"><span class="glyphicon glyphicon-hourglass"></span>&nbsp;正在审核中</button></span>
                    </div>


                    <div class="pic_big" id="pl_tip">
                        <div class="pic_big_tip">
                            <span style="font-size: 16px;font-family: 微软雅黑"><b>管理猿申请</b></span>
                            <br/><br/>
                            <span style="color:#0055aa;font-weight: bold">说点什么：</span>
                            <textarea id="tip_msg" class="form-control" placeholder="请输入申请内容，说说自己为什么想当这个圈子的管理员，内容不能为空，且不超过100个字符~" rows="5"></textarea>
                            <br/>
                            <button type="button" class="btn btn-primary" id="tip_submit">发送</button>
                            <button type="button" class="btn btn-primary" id="tip_close">取消</button>
                            &nbsp;&nbsp;<span id="jb_load" style="display: none; color:#9D9D9D; font-size: 14px"><img src="http://7xwibn.com1.z0.glb.clouddn.com/static/loading.jpg" style="width: 30px; height: 30px"/>&nbsp;申请提交中..</span>
                        </div>
                    </div>

                </div>
                </c:otherwise>
                </c:choose>


            </div>
            <div class="main02_02">

            </div>
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/music/music.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/video/video.js"></script>
</div>
<%@include file="../common/foot.jsp" %>
</body>
</html>
