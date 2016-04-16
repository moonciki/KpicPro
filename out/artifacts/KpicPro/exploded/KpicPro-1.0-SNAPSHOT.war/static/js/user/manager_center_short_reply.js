
$().ready(function(){
    jz(false, 0);
});

function more(){
    var page = $("#page").val();
    jz(true,page);
}

function jz(isjz, page){

    if(isjz){
        $("#jz_load").show();
    }

    $.post("/user/management/center/all/shortreplys",{'page':page}, function(data){

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
            for(key in data){
                var addhtml = "<span style=\"background-color:"+data[key].color+"\" class=\"badge pull-right\"><span class=\"glyphicon glyphicon-leaf\"></span>&nbsp;"+
                    data[key].pbarName+"</span><span style=\"background-color:#009393\" class=\"badge pull-left\"><span class=\"glyphicon glyphicon-pushpin\"></span>&nbsp;"+
                "对应主题</span>&nbsp;<a href=\"\" target=\"_blank\">" + data[key].title + "</a><br/>&nbsp;&nbsp;&nbsp;&nbsp;"+
                "<span class=\"glyphicon glyphicon-arrow-up\" style = \"color:#548C00\"></span><br/>"+
                "【对应回复】&nbsp;"+data[key].shortText+"<br/><div class=\"yb\"><div class=\"pf\"><span class=\"glyphicon glyphicon-triangle-top\"></span>"+
                "</div></div><div class=\"reply_content\" style='width:99%;'>"+data[key].content+"<br/><br/><span style=\"color:#ADADAD; font-size: 8px\"><span class=\"glyphicon glyphicon-dashboard\">" +
                "</span>&nbsp;评论时间："+getLocalTime(data[key].createTime)+"</span></div><br/>"

                $("#topic_post_area").append(addhtml);
            }
            $("#page").val(parseInt(page) + 1);
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