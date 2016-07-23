<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/4/28
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>咔哔圈子-每个人的兴趣乡</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/home.css" rel="stylesheet"/>
  <link href="${pageContext.request.contextPath}/static/css/home/style.css" rel="stylesheet"/>
</head>
<body>
<%@include file="./common/top.jsp" %>

<div class="pic_big" id="tp_video">
  <div class="pic_big_01" style="background-color: black; display: none">
    <span id="pic_video"></span><div class="pic_big_01_01" title="关闭" onclick="clearAll()"></div>
  </div>
  <div class="pic_big_02">
    <span id="pic_pic"></span>
  </div>
  <span id="music_bf"></span>
</div>

<div class="main">
  <div class="section-focus-pic" id="section-focus-pic">
    <div class="pages" data-scro="list">
      <ul>
        <c:forEach items="${imgs}" var="img" varStatus="s">
          <c:choose>
            <c:when test="${s.count == 1}">
              <li class="item" style="left: 0px">
            </c:when>
            <c:otherwise>
              <li class="item">
            </c:otherwise>
          </c:choose>

            <a href="${img.url}" target="_blank"><img src="${img.picUrl}" title="${img.msg}" class="lb_img" width="1100" height="260"></a>
            <h3><a href="${img.url}" target="_blank" style="color:#FFF;text-decoration:none;">${img.title}</a></h3>
            <div></div>
          </li>
        </c:forEach>
      </ul>
    </div>
    <div class="controler" data-scro="controler">
      <c:forEach items="${js}" var="i">
        <c:choose>
          <c:when test="${i == 1}">
            <b class="down">${i}</b>
          </c:when>
          <c:otherwise>
            <b>${i}</b>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </div>
    <div class="controler2" data-scro="controler2">
      <a href="javascript:;" class="prev"><i></i></a>
      <a href="javascript:;" class="next"><i></i></a>
    </div>
  </div>

  <div class="hot_type">
    <span class="font_tag">
      <span class="glyphicon glyphicon-tags" style="color:#ff789a"></span>
      &nbsp;精彩分类
    </span>
    <span class="font_tag_small">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;据说这些分类下的圈子很精彩哦(｡•̀◡-)✧</span>
    <div>

      <span id="types">

      </span>
    </div>

  </div>

  <div class="hot_type">
    <span class="font_tag">
      <span class="glyphicon glyphicon-th-list" style="color:#ff789a"></span>
      &nbsp;热门圈子
    </span>
    <span class="font_tag_small">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-fire" style="color:#ff615f"></span>&nbsp;&nbsp;我不会告诉你现在这些圈子最热(╯3╰)</span>
    <div>
      <div style="width:1100px;height: 35px;border-bottom:solid 1px #d2d2d2; ">
      <div class="hot_pbar_top" id="rm" onclick="all_pbars()">
        <center>
          热门圈子
          <br/>
          <span></span>
        </center>
      </div>
      <div class="hot_pbar_top" onclick="all_new_pbars()" id="zx">
        <center>
          最新圈子
          <br/>
          <span></span>
        </center>
      </div>
        <div style="float: right;">
          <button type="button" class="btn btn-default btn-sm" style="border: 1px solid #ff809e;color:#ff809e" onclick="sq()">
            <span class="glyphicon glyphicon-plus"></span>
            &nbsp;申请新的圈子
          </button>
        </div>
      </div>

      <div style="width:1100px;height: auto;min-height: 1px;margin-top: 10px">

        <span id="pbars"></span>

      </div>

    </div>
  </div>

  <div class="hot_type">
    <span class="font_tag">
      <span class="glyphicon glyphicon-picture" style="color:#ff789a"></span>
      &nbsp;优秀图集
    </span>
    <span class="font_tag_small">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;下面是站内选出的优秀图集，会不定时更新，欢迎关注哦(๑´ㅂ`๑)</span>
    <div>

      <span id="albums"></span>

    </div>
  </div>

  <input type="hidden" id="userId" value="${user.id}"/>
  <div class="hot_type">
    <span class="font_tag">
      <span class="glyphicon glyphicon-indent-left" style="color:#ff789a"></span>
      &nbsp;精彩内容
    </span>
    <span class="font_tag_small">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;下面是站内选出的精彩文章和帖子，会不定时更新，欢迎关注哦(๑´ㅂ`๑)</span>

    <span style="float: right">
    <span class="font_tag">
      <span class="glyphicon glyphicon-tower" style="color:#ff789a"></span>
      &nbsp;牛人榜
    </span>
    <span class="font_tag_small">&nbsp;&nbsp;&nbsp;-快来围观大牛们~</span>
    </span>

    <div>

      <div class="main01">

        <span id="posts"></span>
        <center>
          <button type="button" id="jz" class="btn btn-default" onclick="jz()" style="border: solid 1px #ff6795;color:#ff6795;margin-top: 20px">
            <span class="glyphicon glyphicon-plus"></span> 加载更多
          </button>
          <span id="no_data" style="display: none;color:#ff8ea2;font-size: 14px"><center>没有更多了</center></span>
        </center>
        <input type="hidden" value="0" id="page"/>

      </div>
      <div class="main02">

          <span id="users"></span>

        </div>
      <div class="main02">
        <span class="font_tag">
      <span class="glyphicon glyphicon-exclamation-sign" style="color:#ff789a"></span>
      &nbsp;小黑屋
    </span>
        <span class="font_tag_small">&nbsp;&nbsp;-啊咧？节操告急~</span>
        <br/>
        <span id = "warns"></span>

      </div>
      </div>

    </div>
  </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ShareLook.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/home.js"></script>
<%@include file="./common/foot.jsp" %>
</body>
</html>
