<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/2 0002
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>如何分享视频到咔哔？</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <style type="text/css">
    /* Custom Styles */
    ul.nav-tabs{
      width: 140px;
      margin-top: 20px;
      border-radius: 4px;
      border: 1px solid #ddd;
      box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
    }
    ul.nav-tabs li{
      margin: 0;
      border-top: 1px solid #ddd;
    }
    ul.nav-tabs li:first-child{
      border-top: none;
    }
    ul.nav-tabs li a{
      margin: 0;
      padding: 8px 16px;
      border-radius: 0;
    }
    ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{
      color: #fff;
      background: #ff81b1;
      border: 1px solid #ff81b1;
    }
    ul.nav-tabs li:first-child a{
      border-radius: 4px 4px 0 0;
    }
    ul.nav-tabs li:last-child a{
      border-radius: 0 0 4px 4px;
    }
    ul.nav-tabs.affix{
      top: 30px; /* Set the top position of pinned element */
    }
  </style>
</head>
<body data-spy="scroll" data-target="#myScrollspy" style="font-family: 'Microsoft Yahei'">
<div class="container">
  <div class="jumbotron">
    <h2>主要视频站视频分享方式</h2>
  </div>


  <div class="row">
    <div class="col-xs-3" id="myScrollspy">
      <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="125">
        <li><a href="#section-1">哔哩哔哩</a></li>
        <li><a href="#section-2">AcFun</a></li>
        <li><a href="#section-3">优酷</a></li>
        <li><a href="#section-4">爱奇艺</a></li>
        <li><a href="#section-5">腾讯</a></li>
        <li><a href="#section-6">土豆</a></li>
      </ul>
    </div>
    <div class="col-xs-9">
      <h2 id="section-1">哔哩哔哩弹幕视频分享方式</h2>
      <p>每个播放器下面的分享按钮，点开复制其flash播放器地址粘贴到本站指定位置即可，如下图所示：
        <br/><img src="http://7xwibn.com1.z0.glb.clouddn.com/static/blibli.jpg"/>
      </p>
      <hr>
      <h2 id="section-2">AcFun弹幕视频分享方式</h2>
      <p>点开播放器下面的分享按钮，会弹出一个分享框，然后复制flash地址即可
        <br/>
        <img src="http://7xwibn.com1.z0.glb.clouddn.com/static/ac1.jpg"/>
        <br/>
        <img src="http://7xwibn.com1.z0.glb.clouddn.com/static/ac2.jpg"/>

      </p>
      <hr/>
      <h2 id="section-3">优酷视频分享方式</h2>
      <p>点开播放器下发分享按钮，复制flash地址即可，如下图：<br/>
        <img src="http://7xwibn.com1.z0.glb.clouddn.com/static/youku.jpg"/>

      </p>
      <hr/>
      <h2 id="section-4">爱奇艺视频分享方式</h2>
      <p>分享方法同上，直接上图：<br/>
        <img src="http://7xwibn.com1.z0.glb.clouddn.com/static/271.jpg"/>

      </p>
      <hr/>
      <h2 id="section-5">腾讯视频分享方式</h2>
      <p>分享方式同上，如图所示：<br/>
        <img src="http://7xwibn.com1.z0.glb.clouddn.com/static/tx.jpg"/>

      </p>
      <hr/>
      <h2 id="section-6">土豆视频分享方式</h2>
      <p>分享方式同上，如图所示：<br/>
        <img src="http://7xwibn.com1.z0.glb.clouddn.com/static/tudou.jpg"/>

      </p>

    </div>
  </div>
</div>
</body>
</html>
