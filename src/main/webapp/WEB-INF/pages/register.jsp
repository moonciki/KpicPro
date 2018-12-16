<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>用户注册</title>
    <meta name="keywords" content="注册,咔哔,咔哔社区" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet"/>
</head>
<body>
<!-- 引入封装了failback的接口--initGeetest -->
<script src="http://static.geetest.com/static/tools/gt.js"></script>
<div class="main">
  <center><span style="font-size: 30px;">注&nbsp;&nbsp;&nbsp;&nbsp;册</span>
    <br/>
    <input type="text" class="form-control" style="margin-top: 50px;height:45px;width: 500px" id="num" placeholder="必填，请输入您的QQ号或者手机号或者学号(请务必要真实填写)">
      <span id="num_load" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>警告！</strong>QQ号或者电话号码或者学号为空或者输入不合法。
          </div>
        </span>
    <span id="num_load2" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>抱歉！</strong>该QQ号或者电话号码或者学号已被注册，请更换~
          </div>
        </span>
    <br/>
    <input type="password" class="form-control" style="height:45px;width: 500px" id="pwd" placeholder="必填，请输入您的密码(不少于6个字符，不超过20个字符)">
      <span id="pwd_load" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>警告！</strong>密码为空或者密码不合法（过长或过短）。
          </div>
        </span>
    <br/>
    <input type="password" class="form-control" style="height:45px;width: 500px" id="pwd2" placeholder="请再次输入一遍您的密码">
      <span id="pwd_load2" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>警告！</strong>确认密码为空或者密码不合法（过长或过短）。
          </div>
        </span>
    <span id="pwd_load3" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>错误！</strong>确认密码与上面所填密码不一致。
          </div>
        </span>
    <br/>
    <input type="text" class="form-control" style="height:45px;width: 500px" id="name" placeholder="请输入您的昵称(必填，请不要超过6个字符)">
      <span id="name_load" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>警告！</strong>昵称输入不合法。
          </div>
        </span>
    <span id="name_load2" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>抱歉！</strong>该昵称已被占用，请换个其他的昵称~。
          </div>
        </span>
    <br/>

    <span style="margin-left:70px;float: left">
      性别：<input type="radio" name="sex" value="0" checked> 男
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="radio" name="sex" value="1"> 女
    </span>
    <br/><br/>

    <input type="hidden" value="" id="birth">


    <input type="hidden" value="" id="tag">


    <input type="hidden" value="" id="address">


    头像（目前为您提供默认头像，级别升至两级以上，可以换自定义头像）
    <br/>

    <div onclick="huoqu_pic()" title="这个不好看，点击更换" style="cursor:pointer;margin-top:20px;margin-bottom:20px;width: 102px; height: 102px; border: 1px solid #ff68ae;" id="userPic"></div>
    <input type="hidden" value="" id="userPic_c"/>

    <div class="yzm">
        <div id="embed-captcha"></div>
        <p id="wait" class="show" style="color:red">* 正在加载验证码......</p>
        <p id="notice" class="hide" style="color:red">* 请先拖动验证码到相应位置</p>
    </div>
    <button type="button" id="register" class="btn btn-info btn-lg btn-block" style="margin-left:-5px;width: 505px">
      注册
    </button>
      <span id="loading" style="display: none">
          <br/>
           <img src="http://7xwibn.com1.z0.glb.clouddn.com/static/loading.jpg" height="35px"/>&nbsp;&nbsp;提交中，请耐心等待处理结果~
      </span>
    <br/>
  </center>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/register.js"></script>
</body>
</html>
