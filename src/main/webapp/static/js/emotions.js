jz();
function jz(){
    var page = $("#cur").val();
    $("#jz").attr("disabled", "disabled");
    $("#jzz").show();
    $.post("/emotion/lists", {'page':page}, function(data){
        $("#jzz").hide();
       if(data == null || data == ""){
           $("#mgd").fadeIn(800);
           setTimeout(function(){$("#mgd").fadeOut(800);},2000);
       }else{
           var addhtml = "";
           for(key in data){
               addhtml+="<div class=\"bq\">"+
               "<img src=\""+data[key].url+"?imageView2/1/w/180/h/180/interlace/0/q/100\" width=\"180px\" height=\"180px\" class=\"img\"/>"+
               "<br/><center><span style=\"line-height: 3.1;font-weight: bold\">由<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a>提供</span>"+
               "<br/>";
               if(data[key].stored){
                   addhtml+=
                       "<button type=\"button\" class=\"btn btn-default btn-lg btn-block\" style=\"border: 1px solid #807880;color:#807880\">"+
                       "<span class=\"glyphicon glyphicon-star\"></span> 已收藏</button></center></div>"
               }else{
                   addhtml+=
                       "<span id=\"sc_"+data[key].id+"\"><button id=\"btn_"+data[key].id+"\" type=\"button\" onclick='scbq("+data[key].id+")' class=\"btn btn-default btn-lg btn-block\" style=\"border: 1px solid #ff7276;color:#ff7276\">"+
                       "<span class=\"glyphicon glyphicon-star-empty\"></span> 收藏这个表情</button></center></div></span>"
               }
           }
            $("#bqs").append(addhtml);
           $("#cur").val(parseInt(page)+1);
           $("#jz").removeAttr("disabled");
       }
    });
}

function scbq(id){
    var userId = $("#dsa").val();
    if(userId == null || userId == ""){
        alert("要先登录哦~");
        return;
    }
    $("#btn_"+id).attr("disabled","disabled");
    $.post("/emotion/store", {'id' : id}, function(data){
       if(data){
           $("#sc_"+id).empty();
           $("#sc_"+id).append("<button type=\"button\" class=\"btn btn-default btn-lg btn-block\" style=\"border: 1px solid #807880;color:#807880\">"+
               "<span class=\"glyphicon glyphicon-star\"></span> 已收藏</button></center></div>");
       }else{
           alert("任务出错，请重试~");
           $("#btn_"+id).removeAttr("disabled");
       }
    });
}