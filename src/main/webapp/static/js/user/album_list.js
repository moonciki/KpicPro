$().ready(function(){

    load();

    $("#jz").click(function(){
        load();
    });

    $("#closeh").click(function(){
        $(".fb_pbar").hide();
        $("#point_title").empty();
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
                addhtml += "<div class='album_list tj_"+data[key].id+"'><span title = \"删除图集\" class=\"badge pull-right\" style='cursor: pointer; background-color: #ff7575;' id=\"sctj_"+data[key].id+"\"  onclick=\"sctj("+data[key].id+")\"><span class='glyphicon glyphicon-remove-sign'></span> 删除</span>" +
                        "<span title=\"发布到圈子\" class=\"badge pull-right\" style='margin-right:10px;cursor: pointer; background-color: #64A600;' id=\"fbtj_"+data[key].id+"\" onclick=\"fbtj('"+data[key].title+"', "+data[key].id+")\"><span class='glyphicon glyphicon-send'></span> 发布</span>"+
                    "<span title=\"设置背景音乐\" class=\"badge pull-right\" style='margin-right:10px;cursor: pointer; background-color: #0080FF;' id=\"fbmusic_"+data[key].id+"\" onclick=\"fbmusic("+data[key].id+")\"><span class='glyphicon glyphicon-music'></span> 音乐</span>"+
                    "<div class='album_list_01'><img src=\""+data[key].imageUrl+"?imageView2/2/w/180/h/200/q/85\" style=\"border-radius: 6px\"/>"+
                    "</div><div class='album_list_02'><a href=\"/album/ab1654"+data[key].id+"\" target='_blank'>"+data[key].title+"</a>" +
                    "<br/><br/>" +
                    data[key].msg+
                    "<br/><br/>"+
                    "<span style='font-size: 12px;line-height: 2.0'><span class='glyphicon glyphicon-time'></span>&nbsp;发布时间：<span style='color:#FF8000'>"+getLocalTime(data[key].createTime)+"</span>"+
                    "&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-music'></span>&nbsp;是否包含背景音乐："
                if(data[key].music == null || data[key].music == ""){
                    addhtml+="<span style='color:#FF95CA' id=\"music_"+data[key].id+"\">否</span>"
                }else{
                    addhtml+="<span style='color:#FF95CA' id=\"music_"+data[key].id+"\">是</span>"
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
var isEmpty = false;

jz(false);

function jz(isjz){
    var page = $("#gzht_page").val();
    $("#btn_jz1").attr("disabled", "disabled")
    $("#jzz1").show();
    var userId = $("#ddddd").val().trim();
    if(userId == null || userId == ""){
        return;
    }
    $.post("/user/focus/subjects", {'userId':userId,'page' : page}, function(data){

        if(data == "" || data == null){
            if(isjz){
                isEmpty = false;
                $("#jzz1").hide();
                $("#my1").fadeIn(400);
                setTimeout(function(){$("#my1").fadeOut(1000);},2000);
            }else{
                isEmpty == true;
                $("#jzz1").hide();
                $("#gzht").append("<div class=\"panel panel-default\"><div class=\"panel-body\"><center><span style='color:#FF95CA;font-family: 微软雅黑; font-size: 20px'>您还没有关注过任何圈子哦~</span></center></div></div>")
            }

            return;
        }
        if(isEmpty){
            $("#self_pbar").empty();
        }
        for(key in data){

            $("#jzz1").hide();
            var addhtml = "<div class=\"panel panel-default\" style='border: solid "+data[key].color+" 1px'><div class=\"panel-body\">" +
                "<img src=\""+data[key].logo+"?imageView2/1/w/50/h/50/q/90\" style=\"border:solid "+data[key].color+" 2px;box-shadow:0px 0px 15px "+data[key].color+";border-radius: 8px;\"/>"+
                "<span style='margin-left: 50px;color:"+data[key].color+"; font-size:18px'><b>"+data[key].name+"</b></span>"+
                "<span style='margin-left: 50px;color:#8E8E8E;font-size:18px'><b>主题帖数："+data[key].topic_num+"</b></span>"+
                "<span class=\"badge pull-right\" style = \"margin-top:15px;font-size:18px;cursor:pointer; background-color:"+data[key].color+"\" onclick='fb("+data[key].id+")'><b>发布到该圈子</b></span>"+
                "</div></div>"
            $("#self_pbar").append(addhtml);

        }
        $("#btn_jz1").removeAttr("disabled");
        $("#gzht_page").val(parseInt(page) + 1);
    });
}

function fbtj(title, id){
    var jc = $("#jc").val();
    if(jc <= 28){
        alert("由于您的节操值降至28以下，当前处于禁言状态，无法发布图集");
        return;
    }
    $(".fb_pbar").show();
    $("#point_title").append(title);
    $("#album_id").val(id);

}

function fb(id){
    var pbarId = id;
    var albumId = $("#album_id").val();
    if(pbarId == null || pbarId == "" || albumId == null || albumId == ""){
        alert("程序出错，请重新操作");
        return;
    }

    $.post("/pbar/album/save", {"pbarId" : pbarId, "albumId" : albumId}, function(data){
        if(data){
            alert("发布成功！");
            $(".fb_pbar").hide();
        }else{
            alert("程序出错，请重新操作");
        }
    })
}


function sctj(id){
    $("#sctj_"+id).hide();
    $("#fbtj_"+id).hide();
    $("#fbmusic_"+id).hide();
    $.post("/album/del", {'id':id}, function(data){
        if(data){
            $(".tj_"+id).fadeOut(1000);
        }else{
            alert("操作出错，请刷新重试~");
        }
    })
}

function fbmusic(id){
    $("#tp_share_music").show();
    $("#haha").show();
    $("#tjId").val(id);
}

function music_share_close(){
    $("#tp_share_music").hide();
    $("#music_content").empty();
    $("#result").hide();
    $("#musicName").val("");
    $("#m_no_data").hide();
    $("#loading").hide();
    $("#mp3wl").val("");
    $("#selfMp3").hide();
    $("#mhs").hide();
    $("#tjId").val("");
}

$("#js").click(function(){
    $("#result").hide();
    $("#music_content").empty();
    $("#no_data").hide();
    var album_id = $("#tjId").val();
    var music_name = $("#musicName").val();
    if(music_name.trim() == "" || music_name.trim() == null){
        alert("请输入检索内容");
        return;
    }

    $("#loading").show();
    $.post("/netease/music/search", {'key':music_name}, function(data){

        if(data == null || data == ""){
            $("#m_no_data").show();
            $("#loading").hide();
            $("#mhs").show();
            return;
        }

        var addhtml = "";
        for(key in data){
            addhtml += "<div style=\"width:350px; height:50px;\"> <audio style=\"width: 275px\" controls=true>"+
                "<source src=\""+data[key].mp3Url+"\" />"+
                "你的浏览器版本太低以至于不能正常播放音频，请升级"+
                "</audio>&nbsp;<button style=\"margin-top: -22px\" type=\"button\" onclick=\"share_music('"+data[key].mp3Url+"', "+album_id+")\" class=\"btn btn-primary btn-sm\">" +
                "<span class=\"glyphicon glyphicon-share\"></span>&nbsp;设置</button></div>"
        }
        $("#result").show();
        $("#loading").hide();
        $("#mhs").show();
        $("#music_content").append(addhtml);

    })
});


function music_dw(){
    $("#haha").hide();
    $("#mhs").hide();
    $("#music_content").empty();
    $("#result").hide();
    $("#musicName").val("");
    $("#no_data").hide();
    $("#loading").hide();
    $("#selfMp3").show();
}

function share_music(url, albumId){
    music_share_close();
    $("#music_"+albumId).empty();
    $("#music_"+albumId).append("是");
    $.post("/album/make/music", {'url':url, 'albumId':albumId}, function(data){});
}

function self_music(){
    var mp3wl = $("#mp3wl").val().trim();
    if(mp3wl == "" || mp3wl == null){
        alert("请输入外链");
        return;
    }
    var album_id = $("#tjId").val();
    $("#music_"+albumId).empty();
    $("#music_"+albumId).append("是");
    music_share_close();
    $.post("/album/make/music", {'url':mp3wl, 'albumId':album_id}, function(data){});
}

function getLocalTime(timer) {
    var d = new Date(timer);
    return (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes());
}