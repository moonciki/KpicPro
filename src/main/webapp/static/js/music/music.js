$().ready(function(){
    $("#js").click(function(){
        $("#result").hide();
        $("#music_content").empty();
        $("#no_data").hide();
        var music_name = $("#musicName").val();
        if(music_name.trim() == "" || music_name.trim() == null){
            alert("请输入检索内容");
            return;
        }

        $("#loading").show();
        $.post("/netease/music/search", {'key':music_name}, function(data){

            if(data == null || data == ""){
                $("#no_data").show();
                $("#loading").hide();
                $("#mhs").show();
                return;
            }

            var addhtml = "";
            for(key in data){
                addhtml += "<div style=\"width:350px; height:50px;\"> <audio style=\"width: 275px\" controls=true>"+
                    "<source src=\""+data[key].mp3Url+"\" />"+
                    "你的浏览器版本太低以至于不能正常播放音频，请升级"+
                    "</audio>&nbsp;<button style=\"margin-top: -22px\" type=\"button\" onclick=\"share_music('"+data[key].mp3Url+"')\" class=\"btn btn-primary btn-sm\">" +
                    "<span class=\"glyphicon glyphicon-share\"></span>&nbsp;分享</button></div>"
            }
            $("#result").show();
            $("#loading").hide();
            $("#mhs").show();
            $("#music_content").append(addhtml);

        })
    });


    $("#music_share").click(function(){
        $("#tp_share_music").show();
        $("#haha").show();
    });



});

function music_share_close(){
    $("#tp_share_music").hide();
    $("#music_content").empty();
    $("#result").hide();
    $("#musicName").val("");
    $("#no_data").hide();
    $("#loading").hide();
    $("#mp3wl").val("");
    $("#selfMp3").hide();
    $("#mhs").hide();
}

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