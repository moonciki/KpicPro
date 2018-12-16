<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>创建分享</title>
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
        我的放送单>>放送单创建>>添加分享---->${sl.title}
    </div>
    <div class="bd_unit">
        <div class="bd_unit_1" style="color: #ff5514">
            分享标题（必填）：
        </div>
        <div class="bd_unit_2">
            <input type="text" class="form-control" id="title" placeholder="请输入标题，不能超过25个字符">
        </div>
    </div>

    <div class="bd_unit">
        <div class="bd_unit_1">
            音乐人（选填）：
        </div>
        <div class="bd_unit_2">
            <input type="text" class="form-control" id="songer" placeholder="请输入音乐人姓名或昵称，不能超过25个字符">
        </div>
    </div>

    <div class="bd_unit">
        <div class="bd_unit_1" style="color:red">
            分享链接说明(重要)：
        </div>
        <div class="bd_unit_2" style="color:#194fff">
            请在下面的框中输入你要分享的音频的源网站播放页地址，例如：<br/>
            网易云音乐：http://music.163.com/#/song?id=27571001<br/>
            QQ音乐：https://y.qq.com/portal/song/004In5mQ1XLDOc.html<br/>
            百度音乐：http://y.baidu.com/song/32261 或者 http://music.baidu.com/song/32261<br/>
            虾米音乐：http://www.xiami.com/song/mQEP75659e1
        </div>
    </div>

    <input type="hidden" id="slId" value="${sl.id}"/>
    <input type="hidden" id="path" value=""/>
    <input type="hidden" id="sourceType" value=""/>
    <div class="bd_unit" style="margin-top: 20px">
        <div class="bd_unit_1" style="color: #ff5514">
            分享源网址（必填）：
        </div>
        <div class="bd_unit_2">
            <input type="text" class="form-control" id="sourcePath" placeholder="请输入音乐网站播放页地址，目前支持：网易云音乐、百度音乐、虾米音乐、QQ音乐">
        </div>
    </div>

    <div class="bd_unit">
        <div class="bd_unit_1" style="color: #ff5514">
            分享描述（必填）：
        </div>
        <div class="bd_unit_2">
            <textarea type="text" class="form-control" id="decri" cols="2" rows="5" placeholder="请输入描述，字数不能超过450个字符"></textarea>
        </div>
    </div>

    <div class="bd_unit" style="margin-top: 20px">
        <div class="bd_unit_1">
            播放器背景（选填）：
        </div>
        <div class="bd_unit_2">
            <button type="button" id="up_cover" class="btn btn-info">
                <span class="glyphicon glyphicon-open"></span>
                上传背景
            </button>
            &nbsp;
            (为了保证美观，请上传<span style="color: red">980px × 475px</span>，且大小不超过<span style="color: red">10MB</span>的图片)
            <br/>
            <input type="hidden" id="cover"/>
            <div id="cover_area" class="cover2">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mv/add_mv.js"></script>
<%@include file="../common/foot.jsp" %>
</body>
</html>