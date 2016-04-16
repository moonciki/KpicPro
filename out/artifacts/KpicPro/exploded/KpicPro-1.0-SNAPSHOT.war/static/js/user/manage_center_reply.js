$().ready(function(){



    var isempty = false;

    var page = $("#page").val();
    var orderBy = $("#orderBy").val();
    jz(false, page, orderBy);

    $(".badge").click(function(){
        $(".xz").removeAttr("style");
        $(".xz").attr("class", "badge wxz");
        $(this).attr("class", "badge xz");
        $(this).attr("style","background-color: #FF60AF");
    });

    $("#sjd").click(function(){
        $(".loading3").show();
        $("#page").val(0);
        $("#orderBy").val("trp.createTime DESC");
        jz(false, 0, "trp.createTime DESC");

    });

    $("#sjz").click(function(){
        $(".loading3").show();
        $("#page").val(0);
        $("#orderBy").val("trp.createTime ASC");
        jz(false, 0, "trp.createTime ASC");
    });

    $("#hfl").click(function(){
        $(".loading3").show();
        $("#page").val(0);
        $("#orderBy").val("trp.replyNum DESC");
        jz(false, 0, "trp.replyNum DESC");
    });

    $("#jz").click(function(){
        var page = $("#page").val();
        var orderBy = $("#orderBy").val();
        $("#jz_load").show();
        jz(true, page, orderBy);
    });

});


function jz(isjz, page, orderBy){
    if(!isjz){
        $("#topic_post_area").empty();
    }else{

    }
    $.post("/user/management/center/reply2",{'page':page, 'orderBy':orderBy}, function(data){
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
                var addhtml = "<div class=\"tt\"><span class=\"badge pull-right\" style=\"background-color: "+data[key].color+"\"><span class=\"glyphicon glyphicon-leaf\"></span>&nbsp;"+data[key].pbarName+"</span><span class='glyphicon glyphicon-comment' style='color:#FF95CA'></span>&nbsp;&nbsp;主题："+
                    "<a href=\"\">"
                    +data[key].title+
                    "</a>"
                    +"</div><div class=\"yb\"><div class=\"pf\"><span class=\"glyphicon glyphicon-triangle-top\"></span></div><div class=\"mane2_reply\"></div><div class=\"mane2_reply2\"></div>"
                    +"</div><div class=\"reply_content\"><span class=\"badge pull-right\" style=\"background-color: #FF60AF\">"+data[key].replyNum+"</span>"+
                    "<a href=\"\" style=\"color:#9D9D9D;font-size: 14px\">"
                    +data[key].shortText+"</a><br/><br/><span style=\"color:#ADADAD; font-size: 8px\">"+
                    "<span class=\"glyphicon glyphicon-dashboard\"></span>"+
                    "&nbsp;回复时间："+getLocalTime(data[key].createTime)+"</span></div>"
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