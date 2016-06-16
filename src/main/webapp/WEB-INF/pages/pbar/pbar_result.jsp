<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/5/26
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>“${kw}”的搜索结果</title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/pbar/pbar_result.css" rel="stylesheet">
</head>
<body onload="key()">
  <%@include file="../common/top.jsp" %>
  <div class="s_main">
    <center>
      <span style="font-size: 30px; color:#46A3FF;font-weight: bold">圈子搜索</span>
      <input type="text" class="form-control" id="ym_kw"
             placeholder="输入你感兴趣的圈子" value="${kw}" style="margin-left:20px;border:2px #ADADAD solid;width:460px;height:35px;display:inline;">
      <button type="button" id="ym_s" class="btn btn-info" style="margin-top:-3px;background-color:#46A3FF;border:1px solid #46A3FF;display: inline"><span class="glyphicon glyphicon-search"></span>&nbsp;搜索</button>
      <button type="button" id="cjht" class="btn btn-info" style="margin-top:-3px;background-color:#46A3FF;border:1px solid #46A3FF;display: inline"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;创建圈子</button>
    </center>
  </div>
  <input type="hidden" value="${user.id}" id="userId"/>
  <input id="page" type="hidden" value="0"/>
  <input id="kw" type="hidden" value="${kw}"/>
  <div class="main" id="main">
    <span id="s_result">
      <span id="no_data_1" style="display:none;font-size: 35px;color:#FF95CA"><center><br/><br/><br/>非常抱歉，没有找到您想要的结果</center></span>
    </span>
    <div style="clear: both"></div>
    <br/><br/>
    <center>
      <button type="button" id="jz" onclick="jz(true)" class="btn btn-info" style="margin-top:-3px;background-color:#FF95CA;border:1px solid #FF95CA;display: inline"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;查看更多</button>
      <br>
      <span id="jzz" style="display: none;">加载中...</span>
      <span id="no_data_2" style="display:none;">没有更多了</span>
    </center>
  </div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pbar/pbar_result.js"></script>
</body>
</html>
