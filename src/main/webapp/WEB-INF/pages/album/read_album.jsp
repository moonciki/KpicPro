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
  <title>${album.title}</title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/album/read_album.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.6.js"></script>
  </head>
<body>
<%@include file="../common/top.jsp" %>

  <div class="main">
    <div class="main_js">
      <div class="main_js_1">
        <span style="font-size: 25px;font-weight: bold"><span class="glyphicon glyphicon-cloud" style="color:#ff7575"></span>&nbsp;${album.title}</span>
        <br/>
        <span style="font-size: 14px;line-height: 1.5">${album.msg}</span>
        <br/>
        <span style="font-size: 12px; color: grey;line-height: 2.5"><span class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;制作日期：<span style="color:green"><b><fmt:formatDate value="${album.createTime}" pattern="yyyy-MM-dd"/></b></span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span class="glyphicon glyphicon-picture"></span>&nbsp;&nbsp;图片：<span style="color:red"><b>${album.picNum}</b></span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span class="glyphicon glyphicon-music"></span>&nbsp;&nbsp;是否有背景音乐：<span style="color:orange"><b>
          <c:choose>
            <c:when test="${album.music == '' || album.music == null}">
              否
            </c:when>
            <c:otherwise>
              是
            </c:otherwise>
          </c:choose>
          </b></span>
          <br></span>
      </div>
      <div class="main_js_2">
        <div class = "main_js_21">
          <c:choose>
            <c:when test="${album.avater == null || album.avater == ''}">
              <img src="${album.userPic}?size=50" class="tx"/>
              <input type="hidden" name="avater" id="avater" value="${album.userPic}?size=70"/>
            </c:when>
            <c:otherwise>
              <img src="${album.avater}?imageView2/2/w/70/h/50/q/95" class="tx"/>
              <input type="hidden" name="avater" id="avater" value="${album.avater}?imageView2/2/w/70/h/70/q/95"/>
            </c:otherwise>
          </c:choose>
        </div>

        <input type="hidden" name="toUserId" id="toUserId" value="${album.userId}"/>
        <input type="hidden" name="albumId" id="albumId" value="${album.id}"/>
        <input type="hidden" name="userId" id="userId" value="${user.id}"/>
        <input type="hidden" name="userName" id="userName" value="${user.name}"/>
        <input type="hidden" name="page" id="page" value="0"/>

        <div class="main_js_22">
          <span style="line-height: 1.5">
            <a href="${pageContext.request.contextPath}/user/u6514${album.userId}/index.html" target="_blank">${album.userName}</a>
            <c:choose>
              <c:when test="${album.sex == 0}">
                <span class="boy">♂</span>
              </c:when>
              <c:otherwise>
                <span class="girl">♀</span>
              </c:otherwise>
            </c:choose>
          </span>
          <br/>
          <span style="line-height: 1.8; color:gray;font-size: 14px">${album.tag}</span>
        </div>
      </div>
    </div>

    <div class="main_content">
    <c:if test="${album.music != '' && album.music != null}">
      <span style="line-height: 2.0; color:#BEBEBE;margin-left: 144px"><span class="glyphicon glyphicon-arrow-down"></span> 此图集包含背景音乐，请点击播放，搭配图集一同欣赏</span><br/>

    </c:if>
      <center>
        <c:if test="${album.music != '' && album.music != null}">

          <audio style="margin-bottom:10px;width: 795px;background-color: #FFD9EC;border-radius: 6px" controls=true>
          <source src="${album.music}"/></audio>
        </c:if>
      <img src="${album.imageUrl}?imageView2/2/w/775/q/75" class="img"/></center>


      <div class="main_content_msg">
        <span style="font-size: 38px;">———————-—-</span>
        <span class="glyphicon glyphicon-star" style="font-size: 20px"></span>
        <span style="font-size: 28px; color: #E0E0E0">正文</span>
        <span class="glyphicon glyphicon-star" style="font-size: 20px"></span>
        <span style="font-size: 38px">-—-——————</span>
      </div>

      <c:if test="${pics != null}">
        <c:forEach items="${pics}" var="pic">
          <div class="main_content_msg">
            <span class="glyphicon glyphicon-leaf" style="color:yellowgreen"></span>&nbsp;&nbsp;${pic.msg}
          </div>
          <center><img class="img" src="${pic.imgUrl}?imageView2/2/w/775/q/75"/></center>
        </c:forEach>
      </c:if>


      <div class="main_content_msg">
        <span style="font-size: 38px;">———————-—-</span>
        <span class="glyphicon glyphicon-star" style="font-size: 20px"></span>
        <span style="font-size: 28px; color: #E0E0E0">留言</span>
        <span class="glyphicon glyphicon-star" style="font-size: 20px"></span>
        <span style="font-size: 38px">-—-——————</span>
      </div>


      <span id="co_s"></span>
      <span id="addC"></span>
      <br/><br/>
      <div class="main_content_msg_00">
        <center>
          <button type="button" id = "xl" onclick="jz()" class="btn btn-success">加载更多</button>
          <br/>
          <span id="xlz" style="display: none">加载中..</span>
          <span id="pageloading2" style="display: none">没有更多了</span>
        </center>
        <br/><br/>
        <span class="glyphicon glyphicon-pencil" style="color:yellowgreen"></span>
        &nbsp;&nbsp;发表短评
        <br/>
        <textarea class="form-control" id="content" style="background-color: #BEBEBE" rows="3"></textarea>
        <br/>
        <button type="button" id = "tj" class="btn btn-primary">提交</button>
        &nbsp;&nbsp;<span style="display: none" id="loading">发送中...</span>
      </div>

    </div>
  </div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/album/read_album.js"></script>
</body>
</html>
