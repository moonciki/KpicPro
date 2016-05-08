$().ready(function(){
    $('.cd_dy').hover(function(){
        $(this).attr("style", "color: #FF60AF; text-shadow: #FF95CA 0px 0px 10px;border-bottom: 4px solid #FF60AF;");
    },function(){
        $(this).removeAttr("style");
    });

    $("#msg_center").click(function(){
        window.open("/user/management/center");
    });
})

$(".wx").click(function(){
    $(".loading").show();
});

var userId = $("#userId1").val();
if(userId == "" || userId == null){

}else{
    $("#gz").attr("disabled", "disabled");
    var userId2 = $("#userId2").val();
    $.post("/user/center/isgz", {'userId' : userId2}, function(data){
        if(data){
            $("#isgz").empty();
            $("#isgz").append("<button type=\"button\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-ok-sign\"></span> 已关注</button>");
        }else{
            $("#gz").removeAttr("disabled");
        }
    });
}

$("#gz").click(function(){
    $("#gz").attr("disabled", "disabled");
    var userId = $("#userId1").val();
    if(userId == null || userId == ""){
        alert("要先登录哦~");
        $("#gz").removeAttr("disabled");
        return;
    }

    var userId2 = $("#userId2").val();

    $.post("/user/center/gz", {'userId' : userId2}, function(data){
        if(data){
            $("#isgz").empty();
            $("#isgz").append("<button type=\"button\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-ok-sign\"></span> 已关注</button>");
        }else{
            alert("关注失败！请刷新重试~");
        }
    });
});


function sx(userId){
    var userId = $("#userId1").val();
    if(userId == null || userId == ""){
        alert("要先登录哦~");
        return;
    }
    $("#sx_userId").val(userId);
    $("#pl_sx").show();
}

$("#sx_close").click(function(){
    $("#sx_msg").val("");
    $("#pl_sx").hide();
});

$("#sx_submit").click(function(){
    $("#sx_submit").attr("disabled", "disabled");
    $("#sx_close").attr("disabled", "disabled");
    $("#sx_load").show();
    var userId = $("#sx_userId").val();
    var content = $("#sx_msg").val();
    $.post("/user/private/save", {'userId':userId, 'content':content}, function(data){
        if(data){
            alert("发送成功！");
        }else{
            alert("非常抱歉，后台程序出现未知错误，发送失败！");
        }
        $("#sx_submit").removeAttr("disabled");
        $("#sx_close").removeAttr("disabled");
        $("#sx_load").hide();
        $("#sx_msg").val("");
        $("#pl_sx").hide();
    });
});