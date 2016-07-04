<%--
  Created by IntelliJ IDEA.
  User: bjsunqinwen
  Date: 2016/3/16
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${postMSg.title}</title>
  <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/static/css/pbar/pbar_index_blog.css" rel="stylesheet">

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
</head>
<body>
<%@include file="../common/share_media.jsp"%>
<div class="pic_big" id="pl_tip" style="z-index: 1101">
  <div class="pic_big_tip" style="z-index: 1102">
    <input type="hidden" value="" id="tip_id"/>
    <input type="hidden" value="" id="tip_type"/>
    <span style="font-size: 16px;font-family: 微软雅黑"><b>举报该信息</b></span>
    <br/><br/>
    <input type="radio" name="tip_type" checked="checked" value="诈骗信息"> 诈骗信息
    <input style = "margin-left: 100px" type="radio" name="tip_type" value="广告"> 广告<br/>
    <input type="radio" name="tip_type" value="人身攻击"> 人身攻击
    <input style = "margin-left: 100px" type="radio" name="tip_type" value="地域攻击"> 地域攻击<br/>
    <input type="radio" name="tip_type" value="社会有害"> 社会有害
    <input style = "margin-left: 100px" type="radio" name="tip_type" value="广告">淫秽色情<br/>
    <input type="radio" name="tip_type" value="其他"> 其他
    <br/><br/>
    说点什么（选填）：
    <input type="text" class="form-control" id="tip_msg">
    <br/>
    <button type="button" class="btn btn-primary" id="tip_submmit">确定</button>
    <button type="button" class="btn btn-primary" id="tip_close">取消</button>
    <span id="load_area"></span>
  </div>
</div>
<div class="pic_big" id="pl_big">
  <div class="pl_content">
    <div class="pic_big_01_pl" onclick="closePic()" title="关闭"></div>
    <div class="panel panel-default">
      <div class="panel-body">
      <span style="font-family: Arial; font-size: 25px; color:#FF5809"><b>短评</b>&nbsp;&nbsp;
        <span class="glyphicon glyphicon-arrow-right"></span></span>&nbsp;&nbsp;&nbsp;
        <span style="line-height: 2.0;color:#0066CC" id="shortText"></span>
      </div>
    </div>
    <span id="short_loading" style="padding-top: 30px"><center><b>加载中...</b></center><br/><br/></span>
    <span id="short_reply_content">

    </span>
    <center>
      <button id="shortxl" type="button" onclick="short_xl()" class="btn btn-warning"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button><br/>
      <span id="load_xl" style="display: none">加载中...</span>
      <span id="load_xl_nodata" style="display: none">没有更多数据了</span>
    </center>
    <br/><br/>
    <div class="panel panel-default" id="s_reply_area">
      <div class="panel-body">
        <span class="glyphicon glyphicon-comment" style="color:#ffaad5"></span>
        &nbsp;<span style="line-height: 3;font-family: 微软雅黑; color:#00AEAE">我来说一句：</span>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="hidden" id="short_userId"/>
        <input type="hidden" id="reply_id"/>
        <br/>
        <textarea class="form-control" rows="3" id="short_content"></textarea>
        <br/>
        <button type="button" id="tj" onclick="saveShort()" class="btn btn-info">
          <span class="glyphicon glyphicon-send"></span>
          &nbsp;发射~
        </button>&nbsp;&nbsp;<span id="short_fs_loading" style="display: none"> * 发送中...</span>
      </div>
    </div>


  </div>

</div>

<div class="pic_big" id="tp_eif">
  <center>
    <div class="eif">
      <div class="eif_top">表情包-------<a href="${pageContext.request.contextPath}/user/emotion/save" target="_blank">上传表情</a>
        <span class="badge pull-right" style="font-weight:bold;background-color: #FF2D2D;cursor: pointer;font-size: 20px" onclick="eif_close()" title="关闭">×</span>
      </div>

      <div class="eif_content">
                <span id="eif_content">

                </span>
      </div>



      <br/>

      <input type="hidden" id="page_eif" value="0"/>

      <center>
        <button type="button" id="jz" class="btn btn-info">加载更多</button>
        <br/>
        <span id="load" style="display: none">加载中...</span>
        <span id="no_data" style="display: none">没有更多了</span>
      </center>
    </div>
  </center>
</div>


<%@include file="../common/top.jsp" %>
<div class="top_blank"></div>
<div class="main">
  <input type="hidden" id="short_page" value="0"/>

  <%@include file="../common/pbar.jsp" %>

  <div class="mane" style="padding-top:14px;padding-left: 10px">
    <span class="label label-default" style="background-color:${pbar.color};font-family: 微软雅黑;font-size: 20px">文章</span>
    <span style="font-family: 微软雅黑;font-size: 18px; color: #006699">${postMSg.title}</span>
  </div>

  <div class="mane2">
    <div class="pf2"><span class="glyphicon glyphicon-triangle-top" style="font-size: 36px"></span></div><div class="mane2_reply"></div><div class="mane2_reply2"></div>
  </div>
  <c:choose>
  <c:when test="${postMSg.isBoutique == 1}">
  <div class="reply_body jp">
    </c:when>
    <c:otherwise>
    <div class="reply_body">
      </c:otherwise>
      </c:choose>
    <div class="reply_body_main">
      <div class="reply_body_main_01" style="padding-left: 0px">
        <c:choose>
          <c:when test="${postMSg.avater != null}">
            <img src="${postMSg.avater}?imageView2/1/w/89/h/89/q/95" class="userpic"/>
          </c:when>
          <c:otherwise>
            <img src="${postMSg.userPic}?size=89" class="userpic"/>
          </c:otherwise>
        </c:choose>
      </div>
      <c:choose>
        <c:when test="${user.avater != null }">
          <input id="userPic" type="hidden" value="${user.avater}?imageView2/1/w/70/h/70/q/95"/>
          <input id="userPic2" type="hidden" value="${user.avater}?imageView2/1/w/48/h/48/q/95"/>
        </c:when>
        <c:otherwise>
          <input id="userPic" type="hidden" value="${user.userPic}?size=70"/>
          <input id="userPic2" type="hidden" value="${user.userPic}?size=48"/>
        </c:otherwise>
      </c:choose>
      <div class="main01_01_user_reply" style="margin-top: 23px"></div>

      <div class="reply_body_main_02"><div class="pf3"><span class="glyphicon glyphicon-triangle-left"></span></div>

        <div class="reply_body_main_02_01">
          <span class="glyphicon glyphicon-user" style="color: #84C1FF"></span><span style="color: #9D9D9D;font-size: 12px;">&nbsp;&nbsp;文章发表人：
          ${postMSg.userName}
          </span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-time" style="color: #FF79BC"></span><span style="color: #9D9D9D;font-size: 12px;">&nbsp;&nbsp;发布日期：
          <fmt:formatDate value="${postMSg.createTime}" pattern="yyyy 年 MM 月 dd 日"/>
          </span>
          <input type="hidden" id="pbarId" value="${postMSg.pbarId}"/>
          <input type="hidden" id="topicUserId" value="${postMSg.userId}"/>
          <c:choose>
            <c:when test="${postMSg.isTip == true}">
              <span style="color: #BEBEBE; font-size: 12px">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <span class="glyphicon glyphicon-flag"></span>&nbsp;已举报</span>
            </c:when>
            <c:otherwise>
              <span class = "jb_topic${postMSg.id}" style="color: #BEBEBE; cursor:pointer; font-size: 12px" onclick="jubao(${postMSg.id})">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <span class="glyphicon glyphicon-flag"></span>&nbsp;举报</span>
            </c:otherwise>
          </c:choose>

          <c:choose>
            <c:when test="${user != null}">
              <c:if test="${user.id == pbar. userId}">
                <span style="color: #BEBEBE; font-size: 12px">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <c:choose>
                  <c:when test="${postMSg.isBoutique == 1}">
                    <span class="glyphicon glyphicon-pushpin"></span>&nbsp;<span id="qxjp"><span style="cursor: pointer" onclick="qxjp_click(${postMSg.id})">取消精品</span></span></span>
                  </c:when>
                  <c:otherwise>
                    <span class="glyphicon glyphicon-pushpin"></span>&nbsp;<span id="jp"><span style="cursor: pointer" onclick="jp_click(${postMSg.id})">加精</span></span></span>
                  </c:otherwise>
                </c:choose>
                <span style="color: #BEBEBE; font-size: 12px">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <c:choose>
                  <c:when test="${postMSg.isTop == 100}">
                    <span class="glyphicon glyphicon-eject"></span>&nbsp;<span id="qxzd"><span style="cursor: pointer" onclick="qxzd_click(${postMSg.id})">取消置顶</span></span></span>
                  </c:when>
                  <c:otherwise>
                    <span class="glyphicon glyphicon-eject"></span>&nbsp;<span id="zd"><span style="cursor: pointer" onclick="zd_click(${postMSg.id})">置顶</span></span></span>
                  </c:otherwise>
                </c:choose>
              </c:if>
              <c:if test="${role != null and role == 2}">
                <span style="color: #BEBEBE; font-size: 12px">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <c:choose>
                  <c:when test="${postMSg.isBoutique == 1}">
                    <span class="glyphicon glyphicon-pushpin"></span>&nbsp;<span id="qxjp"><span style="cursor: pointer" onclick="qxjp_click(${postMSg.id})">取消精品</span></span></span>
                  </c:when>
                  <c:otherwise>
                    <span class="glyphicon glyphicon-pushpin"></span>&nbsp;<span id="jp"><span style="cursor: pointer" onclick="jp_click(${postMSg.id})">加精</span></span></span>
                  </c:otherwise>
                </c:choose>
                <span style="color: #BEBEBE; font-size: 12px">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <c:choose>
                  <c:when test="${postMSg.isTop == 100}">
                    <span class="glyphicon glyphicon-eject"></span>&nbsp;<span id="qxzd"><span style="cursor: pointer" onclick="qxzd_click(${postMSg.id})">取消置顶</span></span></span>
                  </c:when>
                  <c:otherwise>
                    <span class="glyphicon glyphicon-eject"></span>&nbsp;<span id="zd"><span style="cursor: pointer" onclick="zd_click(${postMSg.id})">置顶</span></span></span>
                  </c:otherwise>
                </c:choose>
              </c:if>
            </c:when>
            <c:otherwise>
            </c:otherwise>
          </c:choose>


          <span class="badge pull-right" style="font-size:14px;background-color: #FF95CA">作者</span>
        </div>
        <div class="reply_body_main_content">
          <span style="font-size: 12px; line-height: 1.8">${postMSg.content}</span>
        </div>
        <div class="reply_body_main_02_01"></div>
      </div>

    </div>
    <div style="text-align: center; line-height: 3.8">

      <span style="font-family: 微软雅黑;font-size: 38px; color: #E0E0E0">————————-—-</span>
      <span class="glyphicon glyphicon-star" style="font-size: 20px;color: #E0E0E0"></span>
      <span style="font-family: 微软雅黑;font-size: 28px; color: #E0E0E0">最新评价</span>
      <span class="glyphicon glyphicon-star" style="font-size: 20px;color: #E0E0E0"></span>
      <span style="font-family: 微软雅黑;font-size: 38px; color: #E0E0E0">-—-————————</span>

    </div>

    <span id = "reply_post_area">
      <div id = "starting" class="reply_body_main">
        <div id = "no-data" style="display: none" class="no_data"></div>
        <div id = "reply_loading" class="loading"></div>
      </div>
    </span>

    <div class="reply_body_main" style="text-align: center">

      <button type="button" id="xl" class="btn btn-success"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;&nbsp;加载更多</button>
      <div style="display: none" id="pageloading"><br/><img src="http://7xtmxr.com1.z0.glb.clouddn.com/static/page_loading.gif"/></div>
      <div style="display: none" id="pageloading2" style="font-size: 14px;"><br/>没有更多了</div>

    </div>
    <c:choose>
      <c:when test="${user != null}">
        <input type="hidden" id = "userId" value="${user.id}"/>
        <input type="hidden" id = "userName" value="${user.name}"/>
      </c:when>
      <c:otherwise></c:otherwise>
    </c:choose>
    <input type="hidden" id = "page" value="0"/>
    <input type="hidden" id = "topicId" value="${postMSg.id}"/>




    <!--发帖子-->
    <div class="main01_01_reply" style="margin-left:70px;padding-bottom:50px;padding-top:50px;padding-left:130px; width: 940px">
<c:choose>
  <c:when test="${user != null}">
    <c:choose>
      <c:when test="${jc <= 14}">
        <center>由于您的节操值低于<span style="color:#CD3333;font-weight: bold">14</span>，当前处于<span style="color:#CD3333;font-weight: bold">禁言</span>状态，无法回复</center>
      </c:when>
      <c:otherwise>
      <div class="bjq_btn2 music_button" id="music_share"></div>
      <div class="bjq_btn2 video_button" id="video_share"></div>
      <div class="bjq_btn2 eif_button" id="eif_share"></div>
      <div class="xiu" style="font-size: 66px; font-weight:bold; font-family: 微软雅黑">咻~ 已发射！</div>
      <div class="reply_loading"><div class="loading" style="padding-top: 120px;font-family: 微软雅黑;">发送中...</div></div>
      <span style="font-size: 15px;font-family: 微软雅黑">回复TA</span>
      <br/>
      <br/>
      <!--style给定宽度可以影响编辑器的最终宽度-->
      <script type="text/plain" id="myEditor" name="content" style="width:722px;height:240px;"></script>
      <br>
      <button type="button" id="saveReply" class="btn btn-info"><span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;发射~</button>
      </c:otherwise>
    </c:choose>

  </c:when>
  <c:otherwise>
    <center>您当前还没有登录哦，<a href="${pageContext.request.contextPath}/index" target="_blank">登录</a>后才能发帖~</center>
  </c:otherwise>
</c:choose>
     </div>
  </div>
</div>

<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/js/reply/reply_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/music/music.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/video/video.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>
