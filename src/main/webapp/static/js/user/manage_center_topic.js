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


    $("#rd").click(function(){
        $(".loading3").show();
        $("#page").val(0);
        $("#orderBy").val("ttp.updateTime DESC");
        jz(false, 0, "ttp.updateTime DESC");

    });

    $("#sjd").click(function(){
        $(".loading3").show();
        $("#page").val(0);
        $("#orderBy").val("ttp.createTime DESC");
        jz(false, 0, "ttp.createTime DESC");

    });

    $("#sjz").click(function(){
        $(".loading3").show();
        $("#page").val(0);
        $("#orderBy").val("ttp.createTime ASC");
        jz(false, 0, "ttp.createTime ASC");
    });

    $("#hfl").click(function(){
        $(".loading3").show();
        $("#page").val(0);
        $("#orderBy").val("ttp.replyNum DESC");
        jz(false, 0, "ttp.replyNum DESC");
    });

    $("#jz").click(function(){
        var page = $("#page").val();
        var orderBy = $("#orderBy").val();
        $("#jz_load").show();
        jz(true, page, orderBy);
    });

});


function jz(isjz, page, orderBy){
    $("#jz").attr("disabled", "disabled");
    if(!isjz){
        $("#topic_post_area").empty();
    }else{

    }
    var userId = $("#userId").val();
    $.post("/user/management/center/topics2",{'userId':userId, 'page':page, 'orderBy':orderBy}, function(data){
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
                var addhtml = "<div class=\"panel panel-default\"><div class=\"panel-body\">"
                addhtml+="<span class='badge pull-right' style='background-color: #FF95CA'>"+
                    data[key].replyNum
                    +"</span><span class=\"badge pull-left\" style='background-color:"+data[key].color+"'><span class='glyphicon glyphicon-leaf'></span>&nbsp;"+
                    data[key].pbarName
                    +"</span>&nbsp;&nbsp;<span style=\"font-size: 16px;font-weight: bold; color:#949449\" class=\"glyphicon glyphicon-transfer\"></span>&nbsp;&nbsp;<a style=\"font-size: 15px;\" href=\"/post/reply/tp5416"+data[key].id+"\" target=\"_blank\">"
                    + data[key].title
                    +"</a>"
                if(data[key].isBoutique == 1){
                    addhtml+="&nbsp;&nbsp;<span class=\"label label-warning\">精</span>"
                }
                addhtml+="<br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                    + data[key].shortText +"</span><br/><br/>"

                if(data[key].img != "" && data[key].img != null){
                    for(key2 in data[key].img){
                        if(data[key].img[key2].imgKey == "2"){
                            addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/q/95\" title=\"视频\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
                        }else if(data[key].img[key2].imgKey == "3"){
                            addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/q/95\" title=\"音频\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
                        }else{
                            addhtml +="<img src= \""+data[key].img[key2].imagePath+"?imageView2/1/w/156/h/90/q/95\" title=\"图片\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
                        }
                    }
                }
                addhtml+="<br/><span style='line-height:3.0;font-size:10px;color:#BEBEBE;'><span class='glyphicon glyphicon-dashboard'></span>&nbsp;&nbsp;发表于："+getLocalTime(data[key].createTime)
                    +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-dashboard'></span>&nbsp;&nbsp;最后回复于："+getLocalTime(data[key].updateTime)+
                    "</div></div>";
                $("#topic_post_area").append(addhtml);
            }
            $("#jz").removeAttr("disabled");
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