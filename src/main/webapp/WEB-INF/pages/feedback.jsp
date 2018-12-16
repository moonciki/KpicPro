<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/29 0029
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>咔哔--信息反馈</title>
  <meta name="keywords" content="信息反馈,咔哔,咔哔圈子" />
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/pbar.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet"/>
</head>
<body>
  <div class="main">
    <center>
    <span id="zt">
      <span style="font-size: 30px;">信息反馈</span>
        <br/><br/>
      <span style="margin-left:70px;float: left">
      反馈类型：<input type="radio" name="fk_type" id="jy" value="0" checked> 对网站的建议
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="radio" name="fk_type" id="jb" value="1"> 站内举报
    </span>
        <br/><br/>
      <span id="jy_jb" style="display: none">
        <input type="text" class="form-control" style="height:45px;width: 500px" id="url" placeholder="请输入您要举报的信息所在页面的链接，不能为空">
      </span>
      <br/>
        <textarea id="msg" style="width: 500px" class="form-control" placeholder="请输入建议内容（或举报原因），不能为空，最少5个字符，最多500个字符" rows="3"></textarea>
      <br/>
        <button type="button" id="fs" class="btn btn-info btn-lg btn-block" style="margin-left:-5px;width: 505px">
          提 交
        </button>
    </span>

      <span id="success" style="display: none;color:#ff81b1; font-size: 30px">
        <br/><br/><br/><br/>
        信息已送达↖(^ω^)↗
        <br/>
        <br/>
        <button type="button" onclick="window.close()" class="btn btn-default" style="border: 2px solid #ff7276;color:#ff7276;font-weight: bold"><span class="glyphicon glyphicon-remove-sign"></span> 关闭该页</button>
      </span>
    </center>
  </div>
  <%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/feedback.js"></script>
</body>
</html>
