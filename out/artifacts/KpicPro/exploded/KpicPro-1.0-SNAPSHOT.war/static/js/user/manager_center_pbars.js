$().ready(function(){
    $("#load_more").attr('disabled',"true");
    jz(false);
});

function more(){
    $("#load_more").attr('disabled',"true");
    $("#jz_load").show();
    jz(true);

}

function jz(isjz){
    var page = $("#pager").val();
    var userId = $("#userId").val();
    $.post("/user/all/user/subjects", {'userId' : userId, 'page' : page}, function(data){
        if(data == null || data == ""){
            if(!isjz){
                $("#load").hide();
                $("#nodata").show();
                return;
            }else{
                $("#jz_load").hide();
                $("#pageloading2").fadeIn(800);
                setTimeout(function(){$("#pageloading2").fadeOut(800);},2000);
                return;
            }
        }

        $("#load").hide();
        $("#jz_load").hide();

        for(key in data){
            var addhtml = "<table class=\"table table-bordered\" style=\"border-top: solid "+data[key].color+" 2px\">"+
                "<tr><th>圈子名称</th><th>主题色</th><th>类型</th><th>状态</th><th>身份</th><th>管理入口</th></tr>" +
                "<tr><td>"+data[key].name+"</td><td><span class=\"badge\" style=\"background-color: "+data[key].color+"\">"+data[key].color+"</span>" +
                "</td><td>"+data[key].type+"</td><td>"
            if(data[key].ispass == 0){
                addhtml += "<span class=\"label label-warning\">审核中..</span>"
            }else if(data[key].ispass == 1){
                addhtml += "<span class=\"label label-success\">审核通过</span>"
            }else if(data[key].ispass == 2){
                addhtml += "<span class=\"label label-default\">审核不通过</span>"
            }else{
                addhtml += "<span class=\"label label-danger\">封禁</span>"
            }
            addhtml+="</td><td>"
            if(data[key].role == 1){
                addhtml += "<span style='color:#FF5151'><b>大管理员</b></span>"
            }else{
                addhtml += "<span style='color:#FF5151'>小管理员</span>"
            }
            addhtml += "</td><td><a href=\"/subject/manager/sub4615"+data[key].pbarId+"\" target=\"_blank\">进入管理页</a>&nbsp;"
            if(data[key].ispass == 1){
                addhtml+="<span class='glyphicon glyphicon-transfer'></span>&nbsp;<a href=\"/post/subjects/sub4615"+data[key].pbarId+"\" target=\"_blank\">进入圈子</a>";
            }
            addhtml+="</td></tr></table>";
            $("#topic_post_area").append(addhtml);
        }

        $("#load_more").removeAttr("disabled");
        $("#pager").val(parseInt(page) + 1);



    });
}