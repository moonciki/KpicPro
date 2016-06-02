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
    $.post("/user/list/blog2",{'userId':userId, 'page':page}, function(data){
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
            var addhtml="";
            for(key in data){
                addhtml += "<div class=\"panel panel-default\"><div class=\"panel-body\">"
                    +"<span class='glyphicon glyphicon-book' style='color:#FF82AB'></span>&nbsp;&nbsp;<a href=\"\"><span style='font-size: 15px'>"+data[key].title+"</span></a><span class=\"badge pull-right\" style='cursor: pointer; background-color: #FF8247;' id=\"sctj\"><span class='glyphicon glyphicon-time'></span> "+getLocalTime(data[key].createTime)+"</span>"
                    +"<br/>"
                    +"<span style='font-size: 12px; line-height: 2.0'>"+data[key].shortContent+"</span></div></div>"
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