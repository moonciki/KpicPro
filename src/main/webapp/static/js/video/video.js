$().ready(function(){
    $("#video_share").click(function(){
        $("#tp_share_video").show();
    });



});

function video_share_close(){
    $("#tp_share_video").hide();
    $("#videoUrl").val("");
}