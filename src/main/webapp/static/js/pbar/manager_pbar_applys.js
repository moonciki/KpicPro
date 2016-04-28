
var pbarId = $("#pbarId").val();

jz();

function jz(){
    var page = $("#page").val();
    $("#btn_jz").attr("disabled","true");
    $("#jzz").show();
    $.post("/pbar/manager/apply", {'page':page,'pbarId':pbarId}, function(data){
        if(data == "" || data == null){
            $("#jzz").hide();
            $("#my").fadeIn(400);
            setTimeout(function(){$("#my").fadeOut(1000);},2000);
        }else{
            var addhtml = "";
            for(key in data){
                addhtml += "<div id=\"sq"+data[key].id+"\" class=\"panel panel-default\"><div class=\"panel-heading\"><h3 class=\"panel-title\" style=\"color:#7B7B7B;font-family:'MicrosoftYaHei'\">"+
                    "<span class=\"glyphicon glyphicon-align-justify\"style=\"color:#FF95CA\"></span>&nbsp;申请人：<a href=\"/user/u6514"+data[key].userId+"/index.html\">"+data[key].userName+"</a> &nbsp;&nbsp;&nbsp;&nbsp;申请日期：<span style=\"color:#FF8040\">" + getLocalTime(data[key].createTime) + "</span>"+
                    "<span class=\"badge pull-right\" onclick=\"tg("+data[key].id+","+data[key].userId+")\" style=\"margin-left:10px;cursor:pointer;background-color:#64A600\"><span class=\"glyphicon glyphicon-ok-sign\"></span> 通过</span>&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "<span class=\"badge pull-right\" onclick=\"btg("+data[key].id+")\" style=\"cursor:pointer;background-color:#ff7575\"><span class=\"glyphicon glyphicon-remove-sign\"></span> 不通过"+
                    "</span></h3></div> <div class=\"panel-body\"> "+data[key].msg+" </div> </div>";
            }
            $("#jzz").hide();
            $("#page").val(parseInt(page) + 1);
            $("#content").append(addhtml);
            $("#btn_jz").removeAttr("disabled");
        }
    });
}

function getLocalTime(timer) {
    var d = new Date(timer);
    return (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes());
}

function tg(id, userId){
    $("#sq"+id).hide();
    $.post("/pbar/manager/apply/tg", {'userId':userId,'pbarId':pbarId,'id':id}, function(data){
       if(data){}else{
           $("#sq"+id).show();
       }
    });
}

function btg(id){
    $("#sq"+id).hide();
    $.post("/pbar/manager/apply/btg", {'id':id}, function(data){
        if(data){}else{
            $("#sq"+id).show();
        }
    });
}