<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/27 0027
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${user.name}的管理中心</title>

  <link href="${pageContext.request.contextPath}/static/css/user/manage_center.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/user/album_list.css" rel="stylesheet">
</head>
<body>
<%@include file="../common/top.jsp" %>
<input type="hidden" id="ddddd" value="${user.id}"/>
<input type="hidden" id="jc" value="${jc}"/>
<div class="main">
  <%@include file="../common/manage_center_left.jsp"%>
  <div class="pic_big fb_pbar">
    <div class="pl_content">
      <span class="badge pull-right" id="closeh" title="关闭" style="background-color: #FF5151; cursor: pointer;"><span class="glyphicon glyphicon-remove"></span></span>
      <input type="hidden" value="0" id="gzht_page"/>
      <input type="hidden" value="" id="album_id"/>
      <div class="well"><span class="glyphicon glyphicon-send" style="color:#FF95CA"></span>&nbsp;&nbsp;将<b>“<span id="point_title"></span>”</b>发布到：</div>

        <span id="self_pbar">

      </span>

      <center><button type="button" onclick="jz(true)" class="btn btn-info" id="btn_jz1"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
        <br/><span id="jzz1" style="display: none">加载中..</span><span id="my1" style="display: none">没有更多了</span>
      </center>

    </div>
  </div>

  <div class="pic_big" id="tp_share_music">
    <div class="share_music_main">
      <span class="badge pull-right" title="关闭" onclick="music_share_close()" style="font-weight:bold; cursor: pointer;background-color: #FF5151"><span class="glyphicon glyphicon-remove"></span></span>
      <br/><center><b>音源：</b><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/wyy.png?imageView2/1/w/20/h/20/q/100">&nbsp;<a href="http://music.163.com/" target="_blank">网易云音乐</a></center><br/>
      <div class="input-group" id="haha" style="margin: 0 auto;width: 350px">
        <input type="text" id="musicName" style="border: solid 2px #FF5151;" class="form-control">
        <span class="input-group-btn">
            <button id="js" style="background-color: #FF5151; border: solid 1px #FF5151;  font-weight:bold; color:#FFF" class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;检 索</button>
        </span>
      </div>

      <br/>
<span style="color:#FF60AF;display: none" id="m_no_data">
    <center>
      <span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;非常抱歉，没有找到您想要的歌曲，您可以试着<a href="${pageContext.request.contextPath}/user/make/music" target="_blank">上传</a>
    </center>
</span>

<span style="color:#FF60AF;display: none" id="loading">
    <center>
      <img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/page_loading.gif">
    </center>
</span>

      <div id="result" style="display: none; margin:0 auto;width:350px; height:30px;">
        <span style="color:#FF60AF"><span class="glyphicon glyphicon-music"></span>&nbsp;我们为您找到了以下音源：</span>
      </div>
      <center>

<span id="music_content">
</span>
        <input type="hidden" id="tjId" value=""/>
        <span style="color:#FF60AF;display: none" id="mhs">没有合适的？<span onclick="music_dw()" style="cursor: pointer;">点我</span></span>
        <span id="selfMp3" style="display: none">
        <div class="input-group" style="margin: 0 auto;width: 450px">
          <span class="input-group-addon" style="background-color: #FF5151; border: solid 1px #FF5151;  font-weight:bold; color:#FFF">mp3外链：</span>
          <input type="text" id="mp3wl" style="border: solid 2px #FF5151;" class="form-control">
        </div>
            <br/>
        <center><button type="button" onclick="self_music()" class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>&nbsp;设置</button></center>
        <br/>
            <a href="${pageContext.request.contextPath}/user/list/music" target="_blank">点我去复制mp3外链</a>
        </span>
      </center>
    </div>
  </div>

  <div class="main_02">
    <div class="main_02_title">
      <span id="content_title">
        <span style="color:#FF79BC" class="glyphicon glyphicon-cog"></span>
        用户管理中心
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#FF95CA" class="glyphicon glyphicon-picture"></span>
        云图集管理
        &nbsp;&nbsp;<span style="color:#8C8C00" class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;
        <span style="color:#84C1FF" class="glyphicon glyphicon-tag"></span>
        我的云图集
      </span>
    </div>
    <input id="page" value="0" type="hidden"/>
    <div class="main_02_content">


      <span id="album_list"></span>

      <center>
        <button type="button" id="jz" class="btn btn-info">加载更多</button>
        <br/>
        <span id="load" style="display: none">加载中...</span>
        <span id="no_data" style="display: none">没有更多了</span>
      </center>

    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user/album_list.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
