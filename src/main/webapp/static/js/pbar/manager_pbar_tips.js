
jz();
jz2();


function jz(){
    $("#btn_jz1").attr("disabled","true");
    var page = $("#01_page").val();
    var pbarId= $("#pbarId").val();
    $("#jzz1").show();
    $.post("/subject/manager/tip", {'page':page,'pbarId':pbarId}, function(data){
        if(data == "" || data == null){
            $("#jzz1").hide();
            $("#my1").fadeIn(400);
            setTimeout(function(){$("#my1").fadeOut(1000);},2000);
        }else{
            var addhtml = "";
            for(key in data){
                addhtml += "<div class=\"panel panel-default\" id=\"topictips"+data[key].topicId+"\"><div class=\"panel-body\" style=\"line-height: 1.8\">"+
                    "<span style=\"color:#737300;font-size: 15px;font-weight: bold;font-family: 微软雅黑\"><span class=\"glyphicon glyphicon-star-empty\"></span>"+
                    "标题为：</span><br/><a href=\"/post/reply/tp5416" + data[key].topicId + "\" target=\"_blank\" title=\"点击进入查看详情\">" + data[key].title + "</a>"+
                    "<br/>被举报了 <span style=\"color:red;font-weight: bold\">"+data[key].count+"</span> 次，举报类型为："+data[key].type+"&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<span id='anload"+data[key].topicId+"' style='display:none;font-family: 微软雅黑;font-size: 12px'><img src='/static/images/loading.jpg' height='30px'/>&nbsp;处理中..</span><span id='an"+data[key].topicId+"' style='font-family: 微软雅黑;font-size: 12px'><a style='cursor:pointer' onclick='deltopic("+data[key].topicId+")'><span class='glyphicon glyphicon-remove-circle'></span>&nbsp;删除该帖子</a>" +
                    "&nbsp;&nbsp;<a style='cursor:pointer' onclick='igntopic("+data[key].topicId+")'><span class='glyphicon glyphicon-eye-close'></span>&nbsp;忽略举报</a></span>"+
                    "</div></div>";
            }
            $("#jzz1").hide();
            $("#01_page").val(parseInt(page) + 1);
            $("#topicTips").append(addhtml);
            $("#btn_jz1").removeAttr("disabled");
        }
    });
}




function jz2(){
    $("#btn_jz2").attr("disabled","true");
    var page = $("#02_page").val();
    var pbarId= $("#pbarId").val();
    $("#jzz2").show();
    $.post("/reply/manager/tip", {'page':page,'pbarId':pbarId}, function(data){
        if(data == "" || data == null){
            $("#jzz2").hide();
            $("#my2").fadeIn(400);
            setTimeout(function(){$("#my2").fadeOut(1000);},2000);
        }else{
            var addhtml = "";
            for(key in data){
                addhtml += "<div class=\"panel panel-default\" id=\"replytips"+data[key].replyId+"\"><div class=\"panel-body\" style=\"line-height: 1.8\">"+
                    "<span style=\"color:#737300;font-size: 15px;font-weight: bold;font-family: 微软雅黑\"><span class=\"glyphicon glyphicon-star-empty\"></span>"+
                    "对应主题帖标题：</span><br/><a href=\"/post/reply/tp5416" + data[key].topicId + "\" target=\"_blank\" title=\"点击进入查看详情\">" + data[key].title + "</a>"+
                    "<br/><span style='color:#007979'><span class='glyphicon glyphicon-comment'></span> 评论内容:</span><br/>"+
                    "<span style='color:#7B7B7B'>"+data[key].shortText+"</span>"+
                    "<br/>被举报了 <span style=\"color:red;font-weight: bold\">"+data[key].count+"</span> 次，举报类型为："+data[key].type+"&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<span id='anreplyload"+data[key].replyId+"' style='display:none;font-family: 微软雅黑;font-size: 12px'><img src='/static/images/loading.jpg' height='30px'/>&nbsp;处理中..</span><span id='anreply"+data[key].replyId+"' style='font-family: 微软雅黑;font-size: 12px'><a style='cursor:pointer' onclick='delreply("+data[key].replyId+")'><span class='glyphicon glyphicon-remove-circle'></span>&nbsp;删除该帖子</a>" +
                    "&nbsp;&nbsp;<a style='cursor:pointer' onclick='ignreply("+data[key].replyId+")'><span class='glyphicon glyphicon-eye-close'></span>&nbsp;忽略举报</a></span>"+
                    "</div></div>";
            }
            $("#jzz2").hide();
            $("#02_page").val(parseInt(page) + 1);
            $("#con2").append(addhtml);
            $("#btn_jz2").removeAttr("disabled");
        }
    });
}


var lock = 0;

function deltopic(topicId){
    lock ++;
    if(lock != 1){
        return;
    }else{
        $("#an"+topicId).hide();
        $("#anload"+topicId).show();
        $.post("/subject/manager/deltopic", {'topicId':topicId},function(data){
            if(data){
                $("#topictips"+topicId).fadeOut(800);
            }else{
                alert("操作出错");
                $("#anload"+topicId).hide();
                $("#an"+topicId).show();
            }
            lock = 0;
        });
    }
}

var lock2 = 0;
function igntopic(topicId){
    lock2 ++;
    if(lock2 != 1){
        return;
    }else{
        $("#an"+topicId).hide();
        $("#anload"+topicId).show();
        $.post("/subject/manager/igntopic", {'topicId':topicId},function(data){
            if(data){
                $("#topictips"+topicId).fadeOut(1000);
            }else{
                alert("操作出错");
                $("#anload"+topicId).hide();
                $("#an"+topicId).show();
            }
            lock = 0;
        });
    }
}


var lock3 = 0;

function delreply(replyId){
    lock3 ++;
    if(lock3 != 1){
        return;
    }else{
        $("#anreply"+replyId).hide();
        $("#anreplyload"+replyId).show();
        $.post("/reply/manager/del", {'replyId':replyId},function(data){
            if(data){
                $("#replytips"+replyId).fadeOut(800);
            }else{
                alert("操作出错");
                $("#anreplyload"+replyId).hide();
                $("#anreply"+replyId).show();
            }
            lock = 0;
        });
    }
}

var lock4 = 0;
function ignreply(replyId){
    lock4 ++;
    if(lock4 != 1){
        return;
    }else{
        $("#an"+replyId).hide();
        $("#anload"+replyId).show();
        $.post("/reply/manager/ign", {'replyId':replyId},function(data){
            if(data){
                $("#replytips"+replyId).fadeOut(1000);
            }else{
                alert("操作出错");
                $("#anreplyload"+replyId).hide();
                $("#anreply"+replyId).show();
            }
            lock = 0;
        });
    }
}