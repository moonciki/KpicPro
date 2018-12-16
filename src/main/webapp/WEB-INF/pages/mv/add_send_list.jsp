<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>放送表创建</title>
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
    我的放送表>>放送表创建
  </div>
  <div class="bd_unit">
    <div class="bd_unit_1">
      放送表标题（必填）：
    </div>
    <div class="bd_unit_2">
      <input type="text" class="form-control" id="title" placeholder="请输入标题，不能超过25个字符">
    </div>
  </div>

  <div class="bd_unit">
    <div class="bd_unit_1">
      放送表描述（必填）：
    </div>
    <div class="bd_unit_2">
      <textarea type="text" class="form-control" id="decri" cols="2" rows="5" placeholder="请输入描述，字数不能超过450个字符"></textarea>
    </div>
  </div>

  <div class="bd_unit" style="margin-top: 20px">
    <div class="bd_unit_1">
      放送表封面（选填）：
    </div>
    <div class="bd_unit_2">
      <button type="button" id="up_cover" class="btn btn-info">
        <span class="glyphicon glyphicon-open"></span>
        上传封面
      </button>
      &nbsp;
      (为了保证美观，请上传<span style="color: red">200px × 200px</span>，且大小不超过<span style="color: red">25kb</span>的图片)
      <br/>
      <input type="hidden" id="cover"/>
      <div id="cover_area" class="cover">

      </div>
    </div>
  </div>

  <div class="bd_unit" style="margin-top: 20px">
    <div class="bd_unit_1"></div>
    <div class="bd_unit_2">
      <button type="button" id="submit" class="btn btn-success">
        <span class="glyphicon glyphicon-send"></span>
        提交
      </button>
    </div>
  </div>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/qiniu.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mv/add_send_list.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>