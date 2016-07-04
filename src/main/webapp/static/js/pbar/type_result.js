function new_result(id){
    $(".curr").addClass("pt");
    $(".curr").removeClass("curr");
    if(id == null || id == ""){
        $("#all").removeClass("pt");
        $("#all").addClass("curr");
    }else{
        $("#tp"+id).removeClass("pt");
        $("#tp"+id).addClass("curr");
    }
    $("#typeId").val(id);
    $("#pbars").empty();
    $("#page").val(0);
    jz(true);

}

jz(true);

function jz(flag){
    $("#jz").attr("disabled","disabled");
    var page = $("#page").val();
    var id = $("#typeId").val();
    $.post("/kabi/type/result", {'typeId':id, 'page':page}, function(data){
        if(data == "" || data == null){
            if(flag){
                $("#pbars").append("<center><span class='loading'>╮(╯_╰)╭ 暂无数据</span></center>");
            }else{
                $("#no_data").fadeIn(800);
                setTimeout(function(){$("#no_data").fadeOut(800);},2000);
            }
        }else{
            for(key in data){
                $("#pbars").append("<div class=\"pbar_box\" onclick=\"openLink('/post/subjects/sub4615"+data[key].id+"')\">"+
                    "<div class=\"pbar_box_01\"><img src=\""+data[key].logo+"?imageView2/1/w/100/h/100/q/95\" style=\"border-radius:8px;border:2px solid "+data[key].color+"\"/></div>"+
                    "<div class=\"pbar_box_02\"><span style='font-size: 20px'>"+data[key].name+"</span><br/>" +
                    "<span style='font-size: 13px; line-height: 1.5;color:#8f8e9a;'>"+data[key].msg+"</span><br/>"+
                    "<button type=\"button\" class=\"btn btn-default btn-xs\" style='border: 1px solid #ff4f72;color:#ff4f72;margin-top: 10px'><span class='glyphicon glyphicon-fire'></span> 关注数："+data[key].focus_num+"</button>"+
                    "<button type=\"button\" class=\"btn btn-default btn-xs\" style='border: 1px solid #4a9aff;color:#4a9aff;margin-top: 10px;margin-left: 20px'><span class='glyphicon glyphicon-list'></span> 帖子数："+data[key].topic_num+"</button>"+
                    "</div></div>");
            }
            $("#page").val(parseInt(page)+1);
            $("#jz").removeAttr("disabled");
        }
    });
}


function openLink(link)
{
    window.open(link);
}
