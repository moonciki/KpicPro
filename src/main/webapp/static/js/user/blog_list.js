$().ready(function(){

    load();

    $("#jz").click(function(){
        load();
    });

    $("#closeh").click(function(){
        $(".pic_big").hide();
        $("#point_title").empty();
    });
});


function load(){

    $("#jz").attr("disabled", "disabled");

    $("#load").show();

    var page = $("#page").val();

    $.post("/user/list/blog", {'page':page}, function(data){
        if(data != null && data != ""){
            $("#load").hide();

            var addhtml = "";
            for(key in data){
                addhtml += "<div class=\"panel panel-default\"><div class=\"panel-body\">"
                    +"<span class='glyphicon glyphicon-book' style='color:#FF82AB'></span>&nbsp;&nbsp;<a href=\"/post/read/at5416"+data[key].id+"\" target=\"_blank\"><span style='font-size: 15px'>"+data[key].title+"</span></a><span class=\"badge pull-right\" style='cursor: pointer; background-color: #ff7575;' id=\"sctj\"><span class='glyphicon glyphicon-remove-sign'></span> 删除</span>"
                    +"<span class=\"badge pull-right\" style='margin-right:10px;cursor: pointer; background-color: #64A600;' onclick=\"fbtj('"+data[key].title+"', "+data[key].id+")\"><span class='glyphicon glyphicon-send'></span> 发布</span><br/>"
                    +"<span style='font-size: 12px; line-height: 2.0'>"+data[key].shortContent+"</span></div></div>"
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
    var userId = $("#userId").val();
    $.post("/user/focus/subjects", {'userId' : userId, 'page' : page}, function(data){

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
        alert("由于您的节操值降至28以下，当前处于禁言状态，无法发布文章");
        return;
    }
    $(".pic_big").show();
    $("#point_title").append(title);
    $("#album_id").val(id);

}

function fb(id){
    var pbarId = id;
    var blogId = $("#album_id").val();
    if(pbarId == null || pbarId == "" || blogId == null || blogId == ""){
        alert("程序出错，请重新操作");
        return;
    }

    $.post("/pbar/blog/save", {"pbarId" : pbarId, "blogId" : blogId}, function(data){
        if(data){
            alert("发布成功！");
            $(".pic_big").hide();
        }else{
            alert("程序出错，请重新操作");
        }
    })
}