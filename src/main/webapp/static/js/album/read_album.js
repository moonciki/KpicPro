$().ready(function(){
    $("#tj").click(function(){
        var userId = $("#userId").val();
        if(userId == "" || userId == null){
            alert("请先登录~");
            return;
        }
        var content = $("#content").val().trim();
        if(content.length > 200 || content == "" || content == null){
            alert("输入的内容不符合要求：不能为空并且长度不超过200个字符~");
            return;
        }
        $("#tj").attr("disabled", "disabled");
        $("#content").attr("disabled", "disabled");
        $("#loading").show();
        var albumId = $("#albumId").val();
        var toUserId = $("#toUserId").val();
        var avater = $("#avater").val();
        var name = $("#userName").val();
        $.post("/album/comment/save", {'toUserId':toUserId, 'content':content, 'albumId':albumId}, function(data){
            if(data){
                var html = "<div class=\"main_content_msg_00\"><div class=\"main_content_msg_01\">"+
                    "<img src=\""+avater+"\" class=\"tx2\"/></div><div class=\"main_content_msg_02\"></div>"+
                    "<div class=\"main_content_msg_03\"><b><span style=\"font-size: 10px;color:#FF0080\">评论者："+
                    "<a href=\"/user/u6514"+userId+"/index.html\" target=\"_blank\">"+name+"</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "时间：<span style=\"color:#64A600\">刚刚</span></span></b><br/>"+content+"</div></div>"
                $("#addC").append(html);
                $("#content").val("");
            }else{
                alert("发生未知错误，请重试~");
            }
        });
        $("#tj").removeAttr("disabled");
        $("#content").removeAttr("disabled");
        $("#loading").hide();
    });
});

jz();

function jz(){
    $("#xl").attr("disabled", "disabled");
    $("#xlz").show();
    var albumId = $("#albumId").val();
    var page = $("#page").val();
    $.post("/all/album/pl",{'albumId':albumId, 'page':page},function(data){
        if(data == "" || data == null){
            $("#xlz").hide();
            $("#pageloading2").fadeIn(800);
            setTimeout(function(){$("#pageloading2").fadeOut(800);},2000);
            return;
        }
        for(key in data){
            var html = "<div class=\"main_content_msg_00\"><div class=\"main_content_msg_01\">";
            if(data[key].avater == "" || data[key].avater == null){
                html+="<img src=\""+data[key].userPic+"?size=70\" class=\"tx2\"/>"
            }else{
                html+="<img src=\""+data[key].avater+"?imageView2/2/w/70/h/70/q/95\" class=\"tx2\"/>"
            }
                html+="</div><div class=\"main_content_msg_02\"></div>"+
                "<div class=\"main_content_msg_03\"><b><span style=\"font-size: 10px;color:#FF0080\">评论者："+
                "<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
                "时间：<span style=\"color:#64A600\">"+getLocalTime(data[key].createTime)+"</span></span></b><br/>"+data[key].content+"</div></div>"
            $("#co_s").append(html);
        }
        $("#xlz").hide();
        $("#xl").removeAttr("disabled");
        $("#page").val(parseInt($("#page").val())+1);
    });
}

function getLocalTime(timer) {
    var d = new Date(timer);
    return (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes());
}