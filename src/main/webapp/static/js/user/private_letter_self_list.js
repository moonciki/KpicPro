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
    $.post("/user/private/letter2", {'page':page}, function(data){
        if(data == null || data == ""){
            $("#load").hide();
            $("#no_data").fadeIn(400);
            setTimeout(function(){$("#no_data").fadeOut(1000);},2000);
        }else{
            var addhtml = "";
            for(key in data){
                addhtml+="<div class=\"sx\" id=\"sx_"+data[key].id+"\">"
                    +"<div class=\"sx_02\" style='width: 775px'>" +
                    "<b>于 "+getLocalTime(data[key].createTime)+" 发送给<a href=\"/user/"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a>的私信：</b>"+
                    "<hr/>"+data[key].content+"<br/><br/>"

                if(data[key].isReply == 0){
                    addhtml+="<b style='color:#FF5151'>暂未回复</b>"
                }else{
                    addhtml+="<b style='line-height: 2.5'><a href=\"/user/"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a>于 "+getLocalTime(data[key].updateTime)+" 回复：</b><br/><div class=\"well\">"+data[key].reply+"</div>"
                }

                addhtml+="</div></div>"
            }
            $("#sx_list").append(addhtml);
            $("#load").hide();
            $("#page").val(parseInt(page)+1);
            $("#jz").removeAttr("disabled");
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