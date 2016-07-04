function bigPic(img, flag){

    $("#tp_video").show();
    if(flag == 2){
        $(".pic_big_01").show();
        $(".pic_big_01_01").show();
        $("#pic_video").append("<embed type=\"application/x-shockwave-flash\" class=\"edui-faked-video\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\""+img+"\" width=\"682px\" height=\"440px\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\" />");
    }else if(flag == 3){
        var addhtml = "<div class=\"pic_big_03\"><div class='pic_big_03_1'></div><div class='pic_big_03_2'><center><div style=\"display: inline-block;width:520px; height:50px;\"> <audio style=\"margin-top:10px;width: 450px\" controls=true>"+
            "<source src=\""+img+"\" />"+
            "</audio>&nbsp;<button style=\"margin-top: -22px\" type=\"button\" onclick=\"close_music()\" class=\"btn btn-danger btn-sm\"><span class=\"glyphicon glyphicon-remove-sign\"></span>&nbsp;关闭</button></div></center></div><div class='pic_big_03_1'></div></div>"
        $("#music_bf").append(addhtml);
    }else{
        $("#pic_pic").append("<center><div style='margin-top: 5%'></div><div style=\"overflow-x :auto;overflow-y :auto;width:906px;height: 467px\"><img src=\""+img+"\" title=\"还原\" style=\"cursor:zoom-out\" onclick=\"clearAll()\"/></div></center>")
    }
}

function clearAll(){
    $("#tp_video").hide();
    $("#pic_video").empty();
    $("#pic_pic").empty();
    $(".pic_big_01").hide();
    $(".pic_big_01_01").hide();
}

function close_music(){
    $("#tp_video").hide();
    $("#music_bf").empty();
}