$().ready(function(){
    var pbarId = $("#gz_pbarId").val();
    var gz_num = $("#gz_num").val();
    var userId = $("#userId").val();
    if(userId != "" && userId != null){
        $.post("/user/pbar/isfocus", {'pbarId' : pbarId}, function(data){
            if(!data){
                $("#isgz_pbar").append("<button type=\"button\" id=\"gzaniu\" style=\"font-family: 微软雅黑\"  class=\"btn btn-danger\" onclick='gz_pbar_ff("+pbarId+")'><span class=\"glyphicon glyphicon-plus-sign\"></span>&nbsp;关注("+gz_num+")</button>");
            }else{
                $("#isgz_pbar").append("<button title =\"取消关注\" type=\"button\" id=\"quguan\" style=\"font-family: 微软雅黑\" class=\"btn btn-success\" onclick='gz_pbar_qx("+pbarId+")'><span class=\"glyphicon glyphicon-ok-sign\"></span>&nbsp;已关注("+gz_num+")</button>");
            }
        })
    }else{
        $("#isgz_pbar").append("<button type=\"button\" id=\"gzaniu\" style=\"font-family: 微软雅黑\"  class=\"btn btn-danger\" onclick='gz_pbar_ff("+pbarId+")'><span class=\"glyphicon glyphicon-plus-sign\"></span>&nbsp;关注("+gz_num+")</button>");
    }

});

function gz_pbar_ff(pbarId){
    var userId = $("#userId").val();
    if(userId == "" || userId == null){
        alert("请先登录~");
        return;
    }
    $("#gzaniu").attr("disabled", "disabled");
    var gz_num = $("#gz_num").val();
    $.post("/user/pbar/focus", {'pbarId' : pbarId}, function(data){
        if(data){
            $("#isgz_pbar").empty();
            $("#isgz_pbar").append("<button title =\"取消关注\" type=\"button\" id=\"quguan\" style=\"font-family: 微软雅黑\" class=\"btn btn-success\" onclick='gz_pbar_qx("+pbarId+")'><span class=\"glyphicon glyphicon-ok-sign\"></span>&nbsp;已关注("+gz_num+")</button>");
        }else{
            alert(data.msg);
        }
    })
}

function gz_pbar_qx(pbarId){
    var userId = $("#userId").val();
    if(userId == "" || userId == null){
        alert("请先登录~");
        return;
    }
    $("#quguan").attr("disabled", "disabled");
    var gz_num = $("#gz_num").val();
    $.post("/user/qx/focus_p", {'pbarId' : pbarId}, function(data){
        if(data){
            $("#isgz_pbar").empty();
            $("#isgz_pbar").append("<button type=\"button\" id=\"gzaniu\" style=\"font-family: 微软雅黑\"  class=\"btn btn-danger\" onclick='gz_pbar_ff("+pbarId+")'><span class=\"glyphicon glyphicon-plus-sign\"></span>&nbsp;关注("+gz_num+")</button>");
        }else{
            alert(data.msg);
        }
    })
}