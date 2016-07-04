<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/21
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${blog.title}</title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/album/read_album.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.6.js"></script>
</head>
<body>
<%@include file="../common/top.jsp" %>

<div class="main">
  <div class="main_js">
    <div class="main_js_1">
      <span style="font-size: 25px;font-weight: bold"><span class="glyphicon glyphicon-book" style="color:#ff7575"></span>&nbsp;${blog.title}</span>
      <br/>
      <span class="glyphicon glyphicon-exclamation-sign" style="color:	#ffb241"></span>&nbsp;&nbsp;<span style="font-size: 14px;line-height: 1.5">原创文章版权归作者所有，未经作者允许请勿转载。转载文章转载时请标明出处(づ￣3￣)づ╭❤～</span>
      <br/>
        <span style="font-size: 12px; color: grey;line-height: 2.5"><span class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;撰写日期：<span style="color:#ff8bb2"><b><fmt:formatDate value="${blog.createTime}" pattern="yyyy-MM-dd"/></b></span>
          </span>
          <br></span>
    </div>
    <div class="main_js_2">
      <div class = "main_js_21">
        <c:choose>
          <c:when test="${blog.avater == null || blog.avater == ''}">
            <img src="${blog.userPic}?size=50" class="tx"/>
            <input type="hidden" name="avater" id="avater" value="${blog.userPic}?size=70"/>
          </c:when>
          <c:otherwise>
            <img src="${blog.avater}?imageView2/2/w/70/h/50/q/95" class="tx"/>
            <input type="hidden" name="avater" id="avater" value="${blog.avater}?imageView2/2/w/70/h/70/q/95"/>
          </c:otherwise>
        </c:choose>
      </div>

      <div class="main_js_22">
          <span style="line-height: 1.5">
            <a href="${pageContext.request.contextPath}/user/u6514${blog.userId}/index.html" target="_blank">${blog.name}</a>
            <c:choose>
              <c:when test="${blog.sex == 0}">
                <div class="boy"></div>
              </c:when>
              <c:otherwise>
                <div class="girl"></div>
              </c:otherwise>
            </c:choose>
          </span>
        <br/>
        <span style="line-height: 1.8; color:gray;font-size: 14px">${blog.tag}</span>
      </div>
    </div>
  </div>

  <div class="main_content" style="background-color: #FFF; border: 1px solid #CCCCCC">
    <div class="reply_body_main_content">
      <span style="font-size: 12px; line-height: 1.8">${blog.content}</span>
    </div>
  </div>
</div>

<%@include file="../common/foot.jsp" %>
</body>
</html>
