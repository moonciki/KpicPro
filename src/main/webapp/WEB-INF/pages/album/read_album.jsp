<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/21
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${album.title}</title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/album/read_album.css" rel="stylesheet"/>
</head>
<body>
<%@include file="../common/top.jsp" %>

  <div class="main">
    <span style="font-size: 25px;font-weight: bold"><span class="glyphicon glyphicon-cloud" style="color:#ff7575"></span>&nbsp;咔哔云图集</span>
    <span style="font-size: 25px">-----${album.title}</span>
    <br/><br/>
    <div class="main_content">

      <center><img src="http://7xs5lv.com1.z0.glb.clouddn.com/tuan/logo/dea22893c6f4436e9d61bcff2b3c6b71.jpg?imageView2/2/w/775/q/75" class="img"/></center>


      <div class="main_content_msg">
        <span class="glyphicon glyphicon-tag" style="color:#FFC1E0"></span>
        &nbsp;&nbsp;发的发的是广东省个梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈地方个梵蒂冈地方发的发的是广东省个梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈地方个梵蒂冈地方发的发的是广东省个梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈梵蒂冈地方个梵蒂冈地方
      </div>

      <div class="main_content_top">
        <center>
          <button type="button" class="btn btn-success">
            <span class="glyphicon glyphicon-menu-left"></span>
            上一张
          </button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span style="color:#E0E0E0;font-size: 18px">当前第
            <span style="font-weight: bold;color:#FF9797; font-style: italic">01</span>
            /
            <span style="font-weight: bold;color:#FF9797; font-style: italic">30</span>
            张</span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <button type="button" class="btn btn-success">
            下一张
            <span class="glyphicon glyphicon-menu-right"></span>
          </button>

        </center>
      </div>



    </div>
  </div>

</body>
</html>
