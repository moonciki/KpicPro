$().ready(function(){

    load();

    $("#jz").click(function(){
        load();
    });
});


function load(){

    $("#jz").attr("disabled", "disabled");

    $("#load").show();

    var page = $("#page").val();

    $.post("/user/list/album", {'page':page}, function(data){
        if(data != null && data != ""){
            $("#load").hide();

            var addhtml = "";
            for(key in data){
                addhtml += "<div class='album_list'><div class='album_list_01'><img src=\""+data[key].imageUrl+"?imageView2/2/w/180/h/200/q/85\" style=\"border-radius: 6px\"/>"+
                    "</div><div class='album_list_02'><a href=\"/album/ab1654"+data[key].id+"\" target='_blank'>"+data[key].title+"</a>" +
                    "<br/><br/>" +
                    data[key].msg+
                    "<br/><br/>"+
                    "<span style='font-size: 12px;line-height: 2.0'><span class='glyphicon glyphicon-time'></span>&nbsp;发布时间：<span style='color:#FF8000'>"+data[key].createTime+"</span>"+
                    "&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-music'></span>&nbsp;是否包含背景音乐："
                if(data[key].musicId == null || data[key].musicId == ""){
                    addhtml+="<span style='color:#FF95CA'>否</span>"
                }else{
                    addhtml+="<span style='color:#FF95CA'>是</span>"
                }

                addhtml+="&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-camera'></span>&nbsp;"
                    +"图片数量：<span style='font-size: 16px; font-style: italic; font-weight: bold; color:#ff7575'>"+data[key].picNum+"</span>"+
                    "</span></div></div>"
            }
            $("#album_list").append(addhtml);
            $("#jz").removeAttr("disabled");
            $("#page").val(parseInt(page)+1);
        }else{
            $("#load").hide();
            $("#no_data").fadeIn(400);
            setTimeout(function(){$("#no_data").fadeOut(1000);},2000);
        }
    });
}