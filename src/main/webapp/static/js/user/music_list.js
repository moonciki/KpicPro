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

    $.post("/user/list/music", {'page':page}, function(data){
        if(data != null && data != ""){
            $("#load").hide();

            var addhtml = "";
            for(key in data){
                addhtml += "<table class=\"table table-bordered\"><tr><th>音乐名称</th><th>作者</th><th>添加时间</th><th>操作</th></tr>"+
                    "<tr><td>"+data[key].name+"</td><td>"+data[key].songer+"</td><td>"+data[key].createTime+"</td><td>" +
                        "<button type=\"button\" class=\"btn btn-default btn-xs\">" +
                        "<span class=\"glyphicon glyphicon-play-circle\"></span> "+
                    "试听</button>&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-default btn-xs\">" +
                        "<span class='glyphicon glyphicon-remove-circle'></span> "+
                    "删除</button>"+
                    "</td></tr></table>";
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