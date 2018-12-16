function sc(){
    $("#scbtn").attr("disabled", "disabled");
    var data = {};
    data.mvId = $("#mv_id").val();
    $.post("/video/saveSc", data, function(result){
        if(result.success){
            $("#sc").empty();
            $("#sc").html("<button type=\"button\" class=\"btn btn-danger\"> <span class=\"glyphicon glyphicon-heart\"></span>已收藏 </button>");
        }else{
            $("#scbtn").removeAttr("disabled");
        }
    });
}