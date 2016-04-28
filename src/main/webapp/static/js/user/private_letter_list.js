$().ready(function(){

    jz();

    $("#jz").click(function(){
        jz();
    });
});

function jz(){
    $("#jz").attr("disabled", "disabled");
    $("#load").show();
    var page = $("#page").val();
    $.post("/user/private/letter", {'page':page}, function(data){
        if(data == null || data == ""){
            $("#load").hide();
            $("#no_data").fadeIn(400);
            setTimeout(function(){$("#no_data").fadeOut(1000);},2000);
        }else{
            var addhtml = "";
            for(key in data){
                addhtml+="<div class=\"sx\" id=\"sx_"+data[key].id+"\"><div class=\"sx_01\">"

                if(data[key].avater == "" || data[key].avater == null){
                    addhtml+="<img src=\""+data[key].userPic+"\" class=\"sx_avater\"/>"
                }else{
                    addhtml+="<img src=\""+data[key].avater+"\" class=\"sx_avater\"/>"
                }

                addhtml+="</div><div class=\"sx_02\">" +
                    "<b>于 "+getLocalTime(data[key].createTime)+" 收到来自<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a>的私信：</b>"+
                    "<hr/>"+data[key].content+
                    "<br/><br/><br/><span class=\"badge pull-left hf_sx\" onclick=\"sx_hf("+data[key].id+")\" style=\"background-color: #64A600\"><span class=\"glyphicon glyphicon-send\"></span> 回复</span><br/><br/>"+
                        "<span id=\"area_"+data[key].id+"\" style=\"display:none;\"><textarea class=\"form-control\" rows=\"3\" id=\"wbk_"+data[key].id+"\"></textarea><br/>"
                    +"<button type=\"button\" id=\"tj_"+data[key].id+"\" onclick=\"tj("+data[key].id+","+data[key].userId+")\" class=\"btn btn-primary btn-xs\">提交</button></span>"+
                "</div></div>"
            }
            $("#sx_list").append(addhtml);
            $("#load").hide();
            $("#page").val(parseInt(page)+1);
            $("#jz").removeAttr("disabled");
        }
    });
}

function sx_hf(id){
    $("#area_"+id).show();
}

function tj(sx_id, user_id){
    $("#tj_"+sx_id).attr("disabled", "disabled");
    var reply = $("#wbk_"+sx_id).val();
    if(reply.trim() == ""){
        alert("回复不能为空！");
        return;
    }
    $.post("/user/private/reply", {'id':sx_id, 'reply':reply, 'userId':user_id}, function(data){
        if(data){
            $("#sx_"+sx_id).fadeOut(1000);
        }else{
            alert("回复出现异常，请重试~");
            $("#tj_"+sx_id).removeAttr("disabled");
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