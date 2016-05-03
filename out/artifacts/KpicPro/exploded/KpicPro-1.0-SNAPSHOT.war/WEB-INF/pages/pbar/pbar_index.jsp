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
    <title>话题--${pbar.name}</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/pbar/pbar_index.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
</head>
<body>

<div class="pic_big" id="tp_video">
    <div class="pic_big_01" style="background-color: black; display: none">
        <span id="pic_video"></span><div class="pic_big_01_01" title="关闭" onclick="clearAll()"></div>
    </div>
    <div class="pic_big_02">
        <span id="pic_pic"></span>
    </div>
    <span id="music_bf"></span>
</div>

<div class="pic_big" id="pl_sx">
    <div class="pic_big_tip">
        <span style="font-size: 16px;font-family: 微软雅黑"><b>私信发送</b></span>
        <br/><br/>
        <span style="color:#0055aa;font-weight: bold">想对TA说点什么？</span>
        <textarea id="sx_msg" class="form-control" rows="5"></textarea>
        <br/>
        <button type="button" class="btn btn-primary" id="sx_submit">发送</button>
        <button type="button" class="btn btn-primary" id="sx_close">取消</button>
        &nbsp;&nbsp;<span id="sx_load" style="display: none; color:#9D9D9D; font-size: 14px"><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg" style="width: 30px; height: 30px"/>&nbsp;提交中..</span>
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
                        <span class="glyphicon glyphicon-list-alt" style="color: #D9B300;"></span>
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
                    <li style="cursor:pointer;">
                        <span class="glyphicon glyphicon-tags" style="color: #AD5A5A"></span>
                        &nbsp;&nbsp;团成员
                        <span class="lines"></span>
                    </li>
                </ul>
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

            <div style="display: none" id="pageloading"><br/><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/page_loading.gif"/></div>
            <div style="display: none" id="pageloading2" style="font-size: 14px;"><br/>没有更多了</div></div>
            <div style="display: none">
                <input id="pbarId" type="hidden" value="${pbar.id}"/>
                <input id="userId" type="hidden" value="${user.id}"/>
                <input id="sx_userId" type="hidden" value=""/>
                <input id="userName" type="hidden" value="${user.name}"/>
                <c:choose>
                    <c:when test="${user.avater != null }">
                        <input id="userPic" type="hidden" value="${user.avater}?imageView2/1/w/70/h/70/q/95"/>
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
                <div class="xiu" style="font-size: 66px; font-weight:bold; font-family: 微软雅黑">咻~ 已发射！</div>
                <div class="reply_loading"><div class="loading" style="margin-top:50px;padding-top: 120px;font-family: 微软雅黑;">发送中...</div></div>
                <span style="font-size: 15px;font-family: 微软雅黑"><span class="glyphicon glyphicon-pencil" style="color:#FF79BC"></span>&nbsp;&nbsp;发表主题帖</span>
                <br/><br/>
                <input type="text" class="form-control" style="width: 700px" id="title" placeholder="请输入标题">
                <br/>
                    <!--style给定宽度可以影响编辑器的最终宽度-->
                    <script type="text/plain" id="myEditor" name="content" style="width:700px;height:240px;"></script>
                <br>
                <button type="button" id="savePost" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;发射~</button>

                <button type="button" id="music_share" class="btn btn-success"><span class="glyphicon glyphicon-music"></span>&nbsp;&nbsp;音频分享</button>

                <button type="button" id="video_share" class="btn btn-success"><span class="glyphicon glyphicon-film"></span>&nbsp;&nbsp;视频分享</button>
            </div>





            <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/pbar/pbar_index.js"></script>

        </div>
        <div class="main02">
            <div class="main02_01">

                <c:choose>
                    <c:when test="${user == null}">
                        未登录
                    </c:when>
                    <c:otherwise>
                        <div class="main02_01_01">
                            <c:choose>
                                <c:when test="${user.avater != null }">
                                    <img src="${user.avater}?imageView2/1/w/100/h/100/q/95" class="userbigpic"/>
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

                            </div>
                            <div class="main02_01_02_02">
                                <span style="color:#FFC1E0">————————————————</span><br/>
                        <span >

                            <span class="glyphicon glyphicon-check" style="color:#FF8000"></span>
                                级别：<b><span id="user_level_add"></span> 级</b>
                            &nbsp;&nbsp;&nbsp;
                            <span class="glyphicon glyphicon-heart" style="color:#ff7575"></span>
                                粉丝：<b>200000</b>
                        </span>
                                <span style="color:#FFC1E0">————————————————</span><br/>
                                <c:choose>
                                    <c:when test="${role != null and role == 1}">
                                        <span class="glyphicon glyphicon-record" style="color:#FF79BC"></span>
                                        身份：<span class="badge">大管理员</span>
                                    </c:when>
                                    <c:when test="${role != null and role == 2}">
                                        <span class="glyphicon glyphicon-record" style="color:#FF79BC"></span>
                                        身份：<span class="badge">小管理员</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-record" style="color:#FF79BC"></span>
                                        身份：<span class="badge">普通人</span>
                                    </c:otherwise>
                                </c:choose>
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
                        <span style="color:#8E8E8E; font-size: 14px;font-weight:bold;font-family: 微软雅黑"><span class="glyphicon glyphicon-user" style="color:${pbar.color};"></span>&nbsp;本话题下的的小管理猿们</span>
                        <span></span>
                    </div>
                </div>

                <span id="manager_small_area">
                    <div class="panel panel-default" id="no-manager" style="display: none">
                        <div class="panel-body">
                            <span style="color:#FF79BC; font-size: 10px;font-family: 微软雅黑"><span class="glyphicon glyphicon-info-sign" style="color:${pbar.color};"></span>&nbsp;本话题下还没有小管理猿 ￣ε￣</span>
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
                            <textarea id="tip_msg" class="form-control" rows="5"></textarea>
                            <br/>
                            <button type="button" class="btn btn-primary" id="tip_submit">发送</button>
                            <button type="button" class="btn btn-primary" id="tip_close">取消</button>
                            &nbsp;&nbsp;<span id="jb_load" style="display: none; color:#9D9D9D; font-size: 14px"><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg" style="width: 30px; height: 30px"/>&nbsp;申请提交中..</span>
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
</body>
</html>
