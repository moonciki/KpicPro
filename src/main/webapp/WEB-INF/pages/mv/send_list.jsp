<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/25 0025
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${sendList.title}</title>
  <meta name="keywords" content="${sendList.title},放送单,咔哔,咔哔社区,音乐,分享" />
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
  <link href="${pageContext.request.contextPath}/static/css/mv/mv.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../common/top.jsp" %>

<div class="top_all">
  <div class="top_div">
    <div class="top_div_01">
      <div class="top_div_01_fm">
        <c:choose>
          <c:when test="${sendList.cover != null}">
            <img src="${sendList.cover}" height="192px" width="192px"/>
          </c:when>
          <c:otherwise>
            <img src="http://7xwibn.com1.z0.glb.clouddn.com/FiOHUq7om2pGuO2ymIEiDNOWel-9" height="192px" width="192px"/>
          </c:otherwise>
        </c:choose>
      </div>
      <span class="top_title">${sendList.title}</span>
      <br/><br/>
        <span class="down_font">
            <span class="down_tb_01 glyphicon glyphicon-expand" title="总播放量" style="font-size: 20px"></span>
            &nbsp;${sendList.play}

            <span class="down_tb_02 glyphicon glyphicon-align-center" title="总弹幕量" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;${sendList.damaku}

            <span class="down_tb_03 glyphicon glyphicon-heart-empty" title="总收藏量" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;${sendList.fav}

            <span class="down_tb_05 glyphicon glyphicon-ban-circle" title="总弹幕池容量" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;${sendList.damakuPool}
        </span>
        <br/><br/>
      <span style="line-height: 1.8;">
        ${sendList.decri}
      </span>
    </div>
    <div class="top_div_02">
      <div class="top_div_02_01">
        <c:choose>
          <c:when test="${sendList.avater == null || sendList.avater == ''}">
            <img src="${sendList.userPic}?size=50" class="avater"/>
          </c:when>
          <c:otherwise>
            <img src="${sendList.avater}?imageView2/2/w/70/h/50/q/95" class="avater"/>
          </c:otherwise>
        </c:choose>
      </div>
      <div class="top_div_02_02">
        <span style="font-size: 18px"><a href="${pageContext.request.contextPath}/user/u6514${sendList.userId}/index.html" target="_blank">${sendList.name}</a></span>
        <c:choose>
          <c:when test="${sendList.sex == 0}">
            <div class="boy"></div>
          </c:when>
          <c:otherwise>
            <div class="girl"></div>
          </c:otherwise>
        </c:choose>
        <br/>
            <span style="font-size: 14px">
                ${sendList.tag}
            </span>
      </div>
    </div>
  </div>
</div>

<div class="zj_div_list">
  <button type="button" class="btn btn-info" onclick="window.open('${pageContext.request.contextPath}/send/mv_add?sl_id=${sendList.id}')">在该放送表下追加音乐</button>
  <button type="button" class="btn btn-warning" onclick="window.open('${pageContext.request.contextPath}/send/list_add')">创建一个放送表</button>
<table class="table table-striped">
  <thead>
  <tr>
    <th>标题</th>
    <th>歌手</th>
    <th>播放数</th>
    <th>弹幕数</th>
    <th>收藏数</th>
    <th>弹幕池</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${mvs}" var="mv">
    <tr>
      <td>
        <a href="${pageContext.request.contextPath}/media/mv${mv.id}" target="_blank">
        ${mv.title}</a>
      </td>
      <td>${mv.songer}</td>
      <td>${mv.play}</td>
      <td>${mv.damaku}</td>
      <td>${mv.fav}</td>
      <td>${mv.damakuPool}</td>
      <td>
        <a href="${pageContext.request.contextPath}/media/mv${mv.id}" target="_blank">
        <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-play-circle"></span>
        播放
        </button>
        </a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</div>

<div id="danmup" style="margin:0 auto;"></div>
<div style="margin-top: 15px"></div>
<div class="zj_div">
  <div class="share_sth">
    <span class="glyphicon glyphicon-share-alt"></span>
    将此放送单分享到&nbsp;&nbsp;
  </div>
  <!-- JiaThis Button BEGIN -->
  <div class="jiathis_style_32x32" style="float: left">
    <a class="jiathis_button_qzone"></a>
    <a class="jiathis_button_tsina"></a>
    <a class="jiathis_button_tqq"></a>
    <a class="jiathis_button_weixin"></a>
    <a class="jiathis_button_duitang"></a>
    <a class="jiathis_button_pinterest"></a>
    <a class="jiathis_button_fav"></a>
  </div>
  <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
  <!-- JiaThis Button END -->
</div>

<%@include file="../common/foot.jsp" %>
</body>
</html>
