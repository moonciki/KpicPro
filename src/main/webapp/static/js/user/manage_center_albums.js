$().ready(function(){



    var isempty = false;

    var page = $("#page").val();
    var orderBy = $("#orderBy").val();
    jz(false, page, orderBy);

    $("#jz").click(function(){
        var page = $("#page").val();
        var orderBy = $("#orderBy").val();
        $("#jz_load").show();
        jz(true, page, orderBy);
    });

});


function jz(isjz, page){
    $("#jz").attr("disabled", "disabled");
    if(!isjz){
        $("#topic_post_area").empty();
    }else{

    }
    var userId = $("#userId").val();
    $.post("/user/list/album2",{'userId':userId, 'page':page}, function(data){
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
            var addhtml = "";
            for(key in data){
                addhtml += "<div class='album_list'>"+
                    "<div class='album_list_01'><img src=\""+data[key].imageUrl+"?imageView2/2/w/180/h/200/q/85\" style=\"border-radius: 6px\"/>"+
                    "</div><div class='album_list_02'><a href=\"/album/ab1654"+data[key].id+"\" target='_blank'>"+data[key].title+"</a>" +
                    "<br/><br/>" +
                    data[key].msg+
                    "<br/><br/>"+
                    "<span style='font-size: 12px;line-height: 2.0'><span class='glyphicon glyphicon-time'></span>&nbsp;发布时间：<span style='color:#FF8000'>"+getLocalTime(data[key].createTime)+"</span>"+
                    "&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-music'></span>&nbsp;是否包含背景音乐："
                if(data[key].music == null || data[key].music == ""){
                    addhtml+="<span style='color:#FF95CA'><b>否</b></span>"
                }else{
                    addhtml+="<span style='color:#FF95CA'><b>是</b></span>"
                }

                addhtml+="&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-camera'></span>&nbsp;"
                    +"图片数量：<span style='font-size: 16px; font-style: italic; font-weight: bold; color:#ff7575'>"+data[key].picNum+"</span>"+
                    "</span></div></div>"
            }
            $("#topic_post_area").append(addhtml);
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