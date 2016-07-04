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
    $.post("/user/home/list2", {'page' : page}, function(data){
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
            var addhtml = "<span id=\"hr_s"+data[key].id+"\"><table class=\"table table-bordered\">"+
                "<tr><th>申请内容</th><th>申请类型</th><th>申请理由</th><th>状态</th><th>站长回信</th><th>操作</th></tr>" +
                "<tr><td><a href=\""+data[key].url+"\" target='_blank'>点击查看</a></td><td><span class=\"badge\" style=\"background-color: #4b7eff\">"+data[key].typeName+"</span>" +
                "</td><td><span class=\"glyphicon glyphicon-eye-open\" title='点击查看详情' style='color:#ff62ab;cursor:pointer;font-size: 21px' onclick=\"tck('"+data[key].msg+"')\"></span></td><td>"
            if(data[key].status == 0){
                addhtml += "<span class=\"label label-warning\">审核中..</span>"
            }else if(data[key].status == 1){
                addhtml += "<span class=\"label label-success\">审核通过</span>"
            }else if(data[key].status == 2){
                addhtml += "<span class=\"label label-danger\">审核不通过</span>"
            }
            addhtml+="</td><td>"
            if(data[key].reason == null || data[key].reason == ""){
                addhtml += "<span class=\"glyphicon glyphicon-eye-close\" title='暂无回信' style='color:#868585;font-size: 21px'></span>"
            }else{
                addhtml += "<span class=\"glyphicon glyphicon-eye-open\" title='点击查看回信' style=\"color:#10af04;cursor:pointer;font-size: 21px\" onclick=\"tck('"+data[key].reason+"')\"></span>"
            }
            addhtml += "</td><td><button type=\"button\" id=\"b_del"+data[key].id+"\" onclick='del("+data[key].id+")' class=\"btn btn-default btn-sm\">"+
            "删除</button></td></tr></table></span>";
            $("#topic_post_area").append(addhtml);
        }

        $("#load_more").removeAttr("disabled");
        $("#pager").val(parseInt(page) + 1);
    });
}


function del(id){
    if(confirm("确定要删除吗？")){
        $("#b_del"+id).attr("disabled", "disabled");
        $.post("/user/home/del", {'id':id}, function(data){
            if(data){
                $("#hr_s"+id).fadeOut(500);
            }else{
                alert("操作失败，请重试");
                $("#b_del"+id).removeAttr("disabled");
            }
        });
    }
}

function tck(msg){
    $("#tck_well").show();
    $("#tck_c").append(msg);
}
function tck_close(){
    $("#tck_well").hide();
    $("#tck_c").empty();
}