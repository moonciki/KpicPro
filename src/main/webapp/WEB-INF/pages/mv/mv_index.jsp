<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <title>${mv.title}</title>
    <meta name="keywords" content="${mv.title},咔哔,咔哔社区,音乐,分享" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/top_logo.ico" type="image/x-icon" />
    <link href="${pageContext.request.contextPath}/static/css/mv/scojs.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/mv/colpick.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/mv/mv.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mv/main.css">
    <style rel = "stylesheet">
        .label_yun{
            border-radius: 100px;
            background-color: #FFF;
            border: solid 1px #b4b4b4;
            color: #b4b4b4;
            font-weight: normal;
        }
    </style>
</head>
<body>
<%@include file="../common/top.jsp" %>
<div class="top_all">
    <div class="top_div">
    <div class="top_div_01">
        <span class="top_title">${mv.title}</span>
        <br/><br/><br/>
        <span class="down_font">
            <span class="down_tb_01 glyphicon glyphicon-expand" title="播放量" style="font-size: 20px"></span>
            &nbsp;${mv.play}

            <span class="down_tb_02 glyphicon glyphicon-align-center" title="弹幕量" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;${mv.damaku}

            <span class="down_tb_03 glyphicon glyphicon-heart-empty" title="收藏量" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;${mv.fav}

            <span class="down_tb_05 glyphicon glyphicon-ban-circle" title="弹幕池容量" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;${mv.damakuPool}

            <span class="down_tb_06 glyphicon glyphicon-user" title="音乐人" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;${mv.songer}

            <span class="down_tb_04 glyphicon glyphicon-folder-open" title="放送单" style="margin-left:40px; font-size: 20px"></span>
            &nbsp;<a href="${pageContext.request.contextPath}/send/list${mv.slId}" target="_blank">${mv.slTitle}</a>
        </span>
    </div>
    <div class="top_div_02">
         <div class="top_div_02_01">
             <c:choose>
                 <c:when test="${mv.avater == null || mv.avater == ''}">
                     <img src="${mv.userPic}?size=50" class="avater"/>
                 </c:when>
                 <c:otherwise>
                     <img src="${mv.avater}?imageView2/2/w/70/h/50/q/95" class="avater"/>
                 </c:otherwise>
             </c:choose>
         </div>
        <div class="top_div_02_02">
            <span style="font-size: 18px"><a href="${pageContext.request.contextPath}/user/u6514${mv.userId}/index.html" target="_blank">${mv.name}</a></span>
            <c:choose>
                <c:when test="${mv.sex == 0}">
                    <div class="boy"></div>
                </c:when>
                <c:otherwise>
                    <div class="girl"></div>
                </c:otherwise>
            </c:choose>
            <br/>
            <span style="font-size: 14px">
                ${mv.tag}
            </span>
        </div>
    </div>
</div>
</div>

<div id="danmup" style="margin:0 auto;"></div>
<div style="margin-top: 15px"></div>
<div class="zj_div">
    <div class="shoucang">
        <c:choose>
            <c:when test="${user != null}">
                <c:choose>
                    <c:when test="${mv.isFav}">
                        <button type="button" class="btn btn-danger">
                            <span class="glyphicon glyphicon-heart"></span>
                            已收藏
                        </button>
                    </c:when>
                    <c:otherwise>
                        <span id="sc">
                            <button type="button" id="scbtn" class="btn btn-info" onclick="sc();">
                                <span class="glyphicon glyphicon-heart"></span>
                                收藏
                            </button>
                        </span>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <button type="button" class="btn btn-info" onclick="alert('请先登录')">
                    <span class="glyphicon glyphicon-heart"></span>
                    收藏
                </button>
            </c:otherwise>
        </c:choose>


        &nbsp;&nbsp;
            <button type="button" title="进入评论区" onclick="window.open('${pageContext.request.contextPath}/post/reply/tp5416${mv.topicId}')" class="btn btn-success">
                <span class="glyphicon glyphicon-comment"></span>
                评论区
            </button>


        &nbsp;&nbsp;
        <a href="http://www.bilibili.com/html/help.html#d5" target="_blank">
            <button type="button" class="btn btn-warning">
                <span class="glyphicon glyphicon-paperclip"></span>
                弹幕基本法
            </button>
        </a>

        &nbsp;&nbsp;
            <c:choose>
                <c:when test="${mv.sourceType == 1}">
                    <span class="source qq" onclick="window.open('${mv.sourcePath}')" title="点击进入QQ音乐播放页">
                        <img src="${pageContext.request.contextPath}/static/images/qq.jpg" width="20px" height="20px" style="border-radius: 4px"/>
                        <span style="font-size: 20px; font-weight: bold">
                            源：QQ音乐
                        </span>
                    </span>
                </c:when>
                <c:when test="${mv.sourceType == 2}">
                    <span class="source wyy" onclick="window.open('${mv.sourcePath}')" title="点击进入网易云音乐播放页">
                        <img src="${pageContext.request.contextPath}/static/images/wyy.jpg" width="20px" height="20px" style="border-radius: 4px"/>
                        <span style="font-size: 20px; font-weight: bold">
                            源：网易云音乐
                        </span>
                    </span>
                </c:when>
                <c:when test="${mv.sourceType == 3}">
                    <span class="source baidu" onclick="window.open('${mv.sourcePath}')" title="点击进入百度音乐播放页">
                        <img src="${pageContext.request.contextPath}/static/images/baidu.jpg" width="20px" height="20px" style="border-radius: 4px"/>
                        <span style="font-size: 20px; font-weight: bold">
                            源：百度音乐
                        </span>
                    </span>
                </c:when>
                <c:when test="${mv.sourceType == 4}">
                    <span class="source xiami" onclick="window.open('${mv.sourcePath}')" title="点击进入虾米音乐播放页">
                        <img src="${pageContext.request.contextPath}/static/images/xiami.jpg" width="20px" height="20px" style="border-radius: 4px"/>
                        <span style="font-size: 20px; font-weight: bold">
                            源：虾米音乐
                        </span>
                    </span>
                </c:when>
            </c:choose>

    </div>
    <div class="share_sth">
        <span class="glyphicon glyphicon-share-alt"></span>
        分享到&nbsp;&nbsp;
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

<div class="down_all">
<div style="margin-top: 15px"></div>
<div class="zj_div_xia">
    <span style="font-size: 17px;">
    <span class="glyphicon glyphicon-tags" style="color: #ff64a0;"></span>
    &nbsp;标签云：</span>
    <span class="label label-default label_yun">标签01</span>
    <span class="label label-default label_yun">标签02</span>
    <span class="label label-default label_yun">标签03</span>
    <span class="label label-default label_yun">标签04</span>
    <span class="label label-default label_yun">标签05</span>
    <span class="label label-default label_yun">标签06</span>
    <span class="label label-default label_yun">标签07</span>
    <span class="label label-default label_yun">标签08</span>
    <span class="label label-default label_yun">标签09</span>
    <span class="label label-default label_yun">标签10</span>
    <span class="label label-default label_yun">标签11</span>
    <span class="label label-default label_yun">标签12</span>
    <span class="label label-default label_yun">标签13</span>
    <span class="label label-default label_yun">标签14</span>
    <span class="label label-default label_yun">标签15</span>
    <span class="label label-default label_yun">标签16</span>
    <span class="label label-default label_yun">标签17</span>
    <span class="label label-default label_yun">标签18</span>
    <span class="label label-default label_yun">标签19</span>
    <span class="label label-default label_yun">标签20</span>
    <span style="color:#ff7277">标签云功能有空再写啦，一个人搞也是很无奈哒 _(:3 」∠)_</span>
    <hr/>

    <span style="font-size: 17px;">
    <span class="glyphicon glyphicon-info-sign" style="color: #ff64a0;"></span>
    &nbsp;简介：</span>
    <br/>
    <span style="color:#b4b4b4; font-size: 13px; line-height: 1.5">
        ${mv.decri}
    </span>
</div>

<div style="margin-top: 15px"></div>
<div class="zj_div_02">

    <div>
        <hr/>
    <span class="glyphicon glyphicon-headphones" style="color:#ff64a0"></span>
    只有这一首？很不过瘾呢_(:3 」∠)_再看看该放送单下的其他分享吧(´・ω・)ﾉ</div>
    <c:forEach items="${mvs}" var="m">
        <div class="zj_div_02_01">
            <a href="${pageContext.request.contextPath}/media/mv${m.id}">
            <c:choose>
                <c:when test="${m.cover != null and m.cover != ''}">
                    <img src="${m.cover}?imageView2/2/w/215/q/95" style="border-radius: 6px"/>
                </c:when>
                <c:otherwise>
                    <img src="http://7xwibn.com1.z0.glb.clouddn.com/Fo-o_lSgGdcE26EoJmEZuj4DRQAa?imageView2/2/w/215/q/95" style="border-radius: 6px"/>
                </c:otherwise>
            </c:choose>
            </a>
            <br/>
        <span style="line-height: 1.8">
            <a href="${pageContext.request.contextPath}/media/mv${m.id}">
                ${m.title}
            </a>
            <br/>
            <span class="down_tb_01_s glyphicon glyphicon-expand" title="播放量" style="font-size: 12px"></span>
            &nbsp;${m.play}

            <span class="down_tb_02_s glyphicon glyphicon-align-center" title="弹幕量" style="margin-left:10px; font-size: 12px"></span>
            &nbsp;${m.damaku}

            <span class="down_tb_03_s glyphicon glyphicon-heart-empty" title="收藏量" style="margin-left:10px; font-size: 12px"></span>
            &nbsp;${m.fav}

            <span class="down_tb_05_s glyphicon glyphicon-ban-circle" title="弹幕池容量" style="margin-left:10px; font-size: 12px"></span>
            &nbsp;${m.damakuPool}
        </span>
        </div>
    </c:forEach>
</div>
</div>

<input type="hidden" id = "mv_id" value="${mv.id}"/>
<input type="hidden" id = "user_id" value="${user.id}"/>
<div style="display: none">
  <span class="glyphicon" aria-hidden="true">&#xe072</span>
  <span class="glyphicon" aria-hidden="true">&#xe073</span>
  <span class="glyphicon" aria-hidden="true">&#xe242</span>
  <span class="glyphicon" aria-hidden="true">&#xe115</span>
  <span class="glyphicon" aria-hidden="true">&#xe111</span>
  <span class="glyphicon" aria-hidden="true">&#xe096</span>
  <span class="glyphicon" aria-hidden="true">&#xe097</span>
</div>


</body>
<script src="${pageContext.request.contextPath}/static/js/mv/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/mv/jquery.shCircleLoader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/mv/sco.tooltip.js"></script>
<script src="${pageContext.request.contextPath}/static/js/mv/colpick.js"></script>
<script src="${pageContext.request.contextPath}/static/js/mv/jquery.danmu.js"></script>
<c:choose>
    <c:when test="${user != null}">
        <c:choose>
            <c:when test="${user.id == mv.userId}">
                <script src="${pageContext.request.contextPath}/static/js/mv/main2.js"></script>
            </c:when>
            <c:otherwise>
                <script src="${pageContext.request.contextPath}/static/js/mv/main.js"></script>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <script src="${pageContext.request.contextPath}/static/js/mv/main.js"></script>
    </c:otherwise>
</c:choose>
<script src="${pageContext.request.contextPath}/static/js/mv/mv_index.js"></script>
<!--<script src="../dist/js/danmuplayer.min.js"></script>-->
<script>
  $("#danmup").DanmuPlayer({
    src:"${mv.path}",
    height: "480px", //区域的高度
    width: "980px", //区域的宽度
    urlToPostDanmu:"/damaku/save"
  });
  var type = ${mv.type};
  if(type == 0){
      $(".danmu-cover").css("background-image", "url(${mv.cover})");
  }
  var userId = $("#user_id").val();
  if(userId == null || userId == ''){
      $(".send-btn").attr("disabled", "disabled");
      $(".danmu-input").attr("disabled", "disabled");
      $(".danmu-input").attr("placeholder", "请先登录/注册");
  }
  <!--弹幕加载-->
  $.post("/video/damaku",{mvId: ${mv.id}}, function(data){
    $("#danmup .danmu-cover").danmu("addDanmu",data)
  });

</script>

<%@include file="../common/foot.jsp" %>
</body>
</html>
