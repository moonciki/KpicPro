<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>用户登录</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/pbar.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet"/>
</head>
<body>
<div class="main">
  <center><span style="font-size: 30px;">登&nbsp;&nbsp;&nbsp;&nbsp;录</span>
    <br/>
    <input type="text" class="form-control" style="margin-top: 50px;height:45px;width: 500px" id="num" placeholder="请输入您的QQ号/手机号/学号">
      <span id="num_load" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>警告！</strong>QQ号或者电话号码或者学号为空或者输入不合法。
          </div>
        </span>
    <br/>
    <input type="password" class="form-control" style="height:45px;width: 500px" id="pwd" placeholder="请输入您的密码">
      <span id="pwd_load" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>警告！</strong>密码为空或者密码不合法（过长或过短）。
          </div>
        </span>
    <br/>
    <input type="text" class="form-control" style="display:inline;height:45px;width: 290px" id="code" placeholder="请输入验证码">
   <span disabled="disabled" style="display:inline;">
        <img src="${pageContext.request.contextPath}/images/captcha.jpeg" width="200px" height="40px" style="border-radius: 6px" alt=""/>
    </span>
      <span id="code_load" style="display: none">
          <div class="alert alert-warning" style="width: 500px">
            <strong>警告！</strong>验证码不能为空！
          </div>
        </span>
    <br/><br/>
    <button type="button" id="login" class="btn btn-info btn-lg btn-block" style="margin-left:-5px;width: 505px">
      登 录
    </button>
    <br/>
    <center>
      <span id="err" style="display: none">
          <div class="alert alert-warning">
            <strong>错误！</strong>密码不正确或者账号不存在！
          </div>
        </span>
      <span id="c_err" style="display: none">
          <div class="alert alert-warning">
            <strong>错误！</strong>验证码错误！
          </div>
        </span>
    </center>
  </center>
</div>
<%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/login.js"></script>
</body>
</html>
