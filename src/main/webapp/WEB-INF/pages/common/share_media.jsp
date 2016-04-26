<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/22
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="pic_big" id="tp_share_music">
  <div class="share_music_main">
    <span class="badge pull-right" title="关闭" onclick="music_share_close()" style="font-weight:bold; cursor: pointer;background-color: #FF5151"><span class="glyphicon glyphicon-remove"></span></span>
    <br/><br/>
    <div class="input-group" id="haha" style="margin: 0 auto;width: 350px">
      <input type="text" id="musicName" style="border: solid 2px #FF5151;" class="form-control">
        <span class="input-group-btn">
            <button id="js" style="background-color: #FF5151; border: solid 1px #FF5151;  font-weight:bold; color:#FFF" class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;检 索</button>
        </span>
    </div>

    <br/>
<span style="color:#FF60AF;display: none" id="no_data">
    <center>
      <span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;非常抱歉，没有找到您想要的歌曲，您可以试着<a href="${pageContext.request.contextPath}/user/make/music">上传</a>
    </center>
</span>

<span style="color:#FF60AF;display: none" id="loading">
    <center>
      <img src="${pageContext.request.contextPath}/static/images/page_loading.gif">
    </center>
</span>

    <div id="result" style="display: none; margin:0 auto;width:350px; height:30px;">
      <span style="color:#FF60AF"><span class="glyphicon glyphicon-music"></span>&nbsp;我们为您找到了以下音源：</span>
    </div>
    <center>
<span id="music_content">



</span>

        <span style="color:#FF60AF;display: none" id="mhs">没有合适的？<span onclick="music_dw()" style="cursor: pointer;">点我</span></span>
        <span id="selfMp3" style="display: none">
        <div class="input-group" style="margin: 0 auto;width: 450px">
            <span class="input-group-addon" style="background-color: #FF5151; border: solid 1px #FF5151;  font-weight:bold; color:#FFF">mp3外链：</span>
            <input type="text" id="mp3wl" style="border: solid 2px #FF5151;" class="form-control">
        </div>
            <br/>
        <center><button type="button" onclick="self_music()" class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>&nbsp;分享</button></center>
        <br/>
            <a href="${pageContext.request.contextPath}/user/list/music" target="_blank">点我去复制mp3外链</a>
        </span>
    </center>
  </div>
</div>


<div class="pic_big" id="tp_share_video">

    <div class="share_music_main">

        <span class="badge pull-right" title="关闭" onclick="video_share_close()" style="font-weight:bold; cursor: pointer;background-color: #FF5151"><span class="glyphicon glyphicon-remove"></span></span>
        <br/><br/>
        <div class="input-group" style="margin: 0 auto;width: 450px">
            <span class="input-group-addon" style="background-color: #FF5151; border: solid 1px #FF5151;  font-weight:bold; color:#FFF">视频FLASH地址：</span>
            <input type="text" id="videoUrl" style="border: solid 2px #FF5151;" class="form-control">
        </div>
        <br/>
        <center><button type="button" onclick="share_video()" class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>&nbsp;分享</button></center>
        <br/>



    </div>

</div>



</body>
</html>
