<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>我的放送单</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/mv/add_send_list.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="add_main">
  <div class="top">
    我的放送单
  </div>
  <button type="button" class="btn btn-info" onclick="window.open('${pageContext.request.contextPath}/send/list_add')">
    <span class="glyphicon glyphicon-plus-sign"></span>
    创建一个放送表
  </button><br/><br/>
  <c:forEach items="${sendList}" var="sl">
    <div class="list_unit">
      <div class="list_unit_1">
        <c:choose>
          <c:when test="${sl.cover == null}">
            <img src="http://7xwibn.com1.z0.glb.clouddn.com/FiOHUq7om2pGuO2ymIEiDNOWel-9" style="border-radius: 6px"/>
          </c:when>
          <c:otherwise>
            <img src="${sl.cover}?imageView2/2/w/200/h/200/q/95" style="border-radius: 6px" width="200" height="200"/>
          </c:otherwise>
        </c:choose>
      </div>
      <div class="list_unit_2">
        <a href="${pageContext.request.contextPath}list${sl.id}" style="font-size: 18px; font-weight: bold" target="_blank">
          ${sl.title}
        </a>
        <br/>
        <span style="font-size: 13px">
          ${sl.decri}
        </span>
        <br/><br/>
        <button type="button" class="btn btn-primary" onclick="window.open('${pageContext.request.contextPath}/send/mv_add?sl_id=${sl.id}')">
          <span class="glyphicon glyphicon-music"></span>
          在该放送单下追加同系列音乐
        </button>
      </div>
    </div>
  </c:forEach>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mv/add_send_list.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>