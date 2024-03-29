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
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/album/read_album.css" rel="stylesheet"/>
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
  <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
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
              </c:when>
            <c:otherwise>
              <img src="${album.avater}?imageView2/2/w/70/h/50/q/95" class="tx"/>
             </c:otherwise>
          </c:choose>
        </div>
        <c:choose>
          <c:when test="${user.avater == null || user.avater == ''}">
            <input type="hidden" name="avater" id="avater" value="${user.userPic}?size=70"/>
          </c:when>
          <c:otherwise>
           <input type="hidden" name="avater" id="avater" value="${user.avater}?imageView2/2/w/70/h/70/q/95"/>
          </c:otherwise>
        </c:choose>
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
                <div class="boy"></div>
              </c:when>
              <c:otherwise>
                <div class="girl"></div>
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
      </center>

      <div id="myCarousel" class="carousel slide" style="width: 795px; margin: 0 auto;">

<c:if test="${pics != null}">
  <ol class="carousel-indicators" style="width: 575px;">
    <li data-target="#myCarousel" data-slide-to="0" style="margin-left: -111px"></li>
  <c:forEach items="${pics}" var="pic" varStatus="status">
      <li data-target="#myCarousel" data-slide-to="${status.count}"></li>
  </c:forEach>
  </ol>
  <div class="carousel-inner">
    <div class="item active">
      <img src="${album.imageUrl}?imageView2/2/w/795/q/95"/>
      <div class="carousel-caption btm">图集封面</div>
    </div>
  <c:forEach items="${pics}" var="pic" varStatus="status">
        <!-- 轮播（Carousel）项目 -->


          <div class="item">
            <img src="${pic.imgUrl}?imageView2/2/w/795/q/95"/>
            <div class="carousel-caption btm">${pic.msg}</div>
          </div>

  </c:forEach>
  </div>
</c:if>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel"
           data-slide="prev" title="前翻">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel"
           data-slide="next" title="后翻">&rsaquo;</a>

      </div>

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
<%@include file="../common/foot.jsp" %>
</body>
</html>
