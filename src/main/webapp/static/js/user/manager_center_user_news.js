
$().ready(function(){
    jz(false, 0);
});

function more(){
    var page = $("#page").val();
    jz(true,page);
}

function jz(isjz, page){

    var news_num = $("#news_num").val();

    if(isjz){
        $("#jz_load").show();
    }

    $.post("/user/all/user/get/news",{'page':page}, function(data){

        if(data == "" || data == null){
            if(!isjz){
                $(".loading3").hide();
                $("#topic_post_area").append("然而并没有什么数据..");
            }else{
                $("#jz_load").hide();
                $("#pageloading2").fadeIn(800);
                setTimeout(function(){$("#pageloading2").fadeOut(800);},2000);
            }
        }else{
            if(!isjz){
                $(".loading3").hide();
            }else{
                $("#jz_load").hide();
            }
            var flag = parseInt(page)*10;
            for(key in data){
                flag ++;
                var addhtml = "";
                if(flag <= news_num){
                    addhtml += "<div class=\"panel panel-default\" style='border: 1px #FFB5B5 solid; background-color: #FFECEC'>"
                }else{
                    addhtml += "<div class=\"panel panel-default\">"
                }
                addhtml+="<div class=\"panel-body\" style='line-height: 2.0'>"+
                    "<span class=\"badge pull-right\" style=\"background-color:"+data[key].color+" \"><span class='glyphicon glyphicon-leaf'></span>&nbsp;"+data[key].pbarName+"</span>"
                if(data[key].topicId == null || data[key].topicId == ""){
                    addhtml += "<span class=\"badge\" style='background-color:#84C1FF'><span class='glyphicon glyphicon-envelope'></span>&nbsp;"+ data[key].title +"</span>";
                }else{
                    addhtml += "<span class=\"badge\" style='background-color:#64A600'><span class='glyphicon glyphicon-envelope'></span>&nbsp;"+ data[key].title +"</span>";
                }

                addhtml += "<span style='font-size: 10px'>&nbsp;&nbsp;您收到来自<a href='/user/u6514" + data[key].fromUserId + "/index.html'>"+ data[key].fromUserName +"</a>的回复&nbsp;&nbsp;<span class='glyphicon glyphicon-share-alt'></span> 回复于："+getLocalTime(data[key].createTime)+
                    "&nbsp;&nbsp;<a href='javascript:void(0);' target = \"_blank\" onclick=\"ckxq("
                if(data[key].topicId == null || data[key].topicId == ""){
                    addhtml += "2, " + data[key].replyId;
                }else{
                    addhtml += "1, 'tp5416" + data[key].topicId +"'";
                }
                    addhtml+=")\"><span class='glyphicon glyphicon-eye-open'></span>&nbsp;查看详情</a></span>"+
                "<div class='panel panel-default' style='background-color: #FFF8D7'><div class='panel-body'>"
                    + data[key].shortContent +
                    "</div></div></div></div>"

                $("#topic_post_area").append(addhtml);
            }
            $("#page").val(parseInt(page) + 1);
        }
    });
}

function ckxq(flag, id){
    $("#btn_ckxq").attr('disabled',"true");
    if(flag == 1){
        window.open("/post/reply/"+id);
    }else{
        $("#ckxq_replyId").val(id);
        var page = $("#ckxq_page").val();
        $(".ckxq01").show();
        $(".ckxq02").show();

        $.post("/user/ckxq/info",{'replyId':id}, function(data){
            if(data.topic != null && data.topic != ""){

                $("#ckxq_topic_user").append("<span class=\"glyphicon glyphicon-user\"></span>&nbsp;发帖人：<a href=\"/user/u6514" + data.topic.userId + "/index.html\" target=\"_blank\">"+data.topic.userName+"</a>"+
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\"></span>&nbsp;发帖时间："+getLocalTime(data.topic.createTime)+"</span>");
                $("#ckxq_title").append("<a href=\"/post/reply/tp5416"+data.topicId+"\" target=\"_blank\">"+data.topic.title);+"</a>";

            }else{
                $("#ckxq_topic_user").append("404");
                $("#ckxq_title").append("404");
            }

            if(data.reply != null && data.reply != ""){
                $("#ckxq_reply_user").append("<span class=\"glyphicon glyphicon-user\"></span>&nbsp;发帖人：<a href=\"/user/u6514" + data.reply.userId + "/index.html\" target=\"_blank\">"+data.reply.userName+"</a>"+
                "&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\"></span>&nbsp;发帖时间："+getLocalTime(data.reply.createTime));
                $("#ckxq_reply_content").append(data.reply.content);
            }else{
                $("#ckxq_reply_user").append("404");
                $("#ckxq_reply_content").append("404");
            }
        });


        $.post("/user/ckxq/short/reply", {'replyId' : id, 'page' : page}, function(data){
            if(data == null || data == ""){
                $("#ckxq_short_list").append("然而并没有什么数据...")
            }else{
                for(key in data){
                    var addhtml = "<br/><br/><span style=\"font-size: 10px; color: #7B7B7B; line-height: 2.0\"><span class=\"glyphicon glyphicon-share-alt\"></span>&nbsp;"+
                        "该短评由 <a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a> 回复于 "+getLocalTime(data[key].createTime)+"</span><br/>"+data[key].content;
                    $("#ckxq_short_list").append(addhtml);
                }
                $("#btn_ckxq").removeAttr("disabled");
                $("#ckxq_page").val(parseInt(page) + 1);
            }
        });
    }
}

function closeInfo(){
    $(".ckxq01").hide();
    $(".ckxq02").hide();
    $("#ckxq_short_list").empty();
    $("#ckxq_topic_user").empty();
    $("#ckxq_title").empty();
    $("#ckxq_reply_user").empty();
    $("#ckxq_reply_content").empty();
    $("#ckxq_short_list").empty();
    $("#ckxq_page").val(0);
}

function ckxq_jz(){
    $("#btn_ckxq").attr('disabled',"true");
    $("#ckxq_load").show();
    var replyId = $("#ckxq_replyId").val();
    var page = $("#ckxq_page").val();
    $.post("/user/ckxq/short/reply", {'replyId' : replyId, 'page' : page}, function(data){
        if(data == null || data == ""){
            $("#ckxq_load").hide();
            $("#ckxq_nodata").fadeIn(400);
            setTimeout(function(){$("#ckxq_nodata").fadeOut(1000);},2000);
        }else{

            for(key in data){
                var addhtml = "<br/><br/><span style=\"font-size: 10px; color: #7B7B7B; line-height: 2.0\"><span class=\"glyphicon glyphicon-share-alt\"></span>&nbsp;"+
                    "该短评由 <a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a> 回复于 "+getLocalTime(data[key].createTime)+"</span><br/>"+data[key].content;
                $("#ckxq_short_list").append(addhtml);
            }
            $("#ckxq_load").hide();
            $("#ckxq_page").val(parseInt(page) + 1);
            $("#btn_ckxq").removeAttr("disabled");
        }
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


