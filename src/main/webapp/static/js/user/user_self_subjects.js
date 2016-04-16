
var isEmpty = false;
var isEmpty2 = false;
var isEmpty3 = false;

jz(false);
jz2(false);
jz3(false);

function jz3(isjz){
    var page = $("#fans_page").val();
    $("#btn_jz3").attr("disabled", "disabled")
    $("#jzz3").show();
    $.post("/user/focus/fans", {'page' : page}, function(data){

        if(data == "" || data == null){
            if(isjz){
                isEmpty3 = false;
                $("#jzz3").hide();
                $("#my3").fadeIn(400);
                setTimeout(function(){$("#my3").fadeOut(1000);},2000);
            }else {
                isEmpty3 == true;
                $("#jzz3").hide();
                $("#fensi").append("<div class=\"panel panel-default\"><div class=\"panel-body\"><center><span style='color:#FF95CA;font-family: 微软雅黑; font-size: 20px'>您还没有任何粉丝哦~</span></center></div></div>")
            }
            return;
        }
        if(isEmpty3){
            $("#fensi").empty();
        }
        for(key in data){

            $("#jzz3").hide();
            var addhtml = "<div class=\"panel panel-default\"><div class=\"panel-body\">"
            if(data[key].avater == "" || data[key].avater == null){
                addhtml += "<img src=\""+data[key].userPic+"?imageView2/1/w/50/h/50/q/90\"/>"
            }else{
                addhtml += "<img src=\""+data[key].avater+"?imageView2/1/w/50/h/50/q/90\"/>"
            }


            addhtml+="<span style='margin-left: 50px;color:#8E8E8E; font-size:18px'><b>"+data[key].name+"</b></span>"+

                "<span class=\"badge pull-right\" onclick='tzr("+data[key].id+")'>进入TA的个人主页 >></span>"+
                "</div></div>"
            $("#fensi").append(addhtml);

        }
        $("#btn_jz3").removeAttr("disabled");
        $("#fans_page").val(parseInt(page) + 1);
    });
}

function jz2(isjz){
    var page = $("#gzr_page").val();
    $("#btn_jz2").attr("disabled", "disabled")
    $("#jzz2").show();
    $.post("/user/focus/users", {'page' : page}, function(data){

        if(data == "" || data == null){
            if(isjz){
                isEmpty2 = false;
                $("#jzz2").hide();
                $("#my2").fadeIn(400);
                setTimeout(function(){$("#my2").fadeOut(1000);},2000);
            }else {
                isEmpty2 == true;
                $("#jzz2").hide();
                $("#gzr").append("<div class=\"panel panel-default\"><div class=\"panel-body\"><center><span style='color:#FF95CA;font-family: 微软雅黑; font-size: 20px'>您还没有关注过任何人哦~</span></center></div></div>")
            }
            return;
        }
        if(isEmpty2){
            $("#gzr").empty();
        }
        for(key in data){

            $("#jzz2").hide();
            var addhtml = "<div class=\"panel panel-default\"><div class=\"panel-body\">"
                if(data[key].avater == "" || data[key].avater == null){
                    addhtml += "<img src=\""+data[key].userPic+"?imageView2/1/w/50/h/50/q/90\"/>"
                }else{
                    addhtml += "<img src=\""+data[key].avater+"?imageView2/1/w/50/h/50/q/90\"/>"
                }


                addhtml+="<span style='margin-left: 50px;color:#8E8E8E; font-size:18px'><b>"+data[key].name+"</b></span>"+

                "<span class=\"badge pull-right\" onclick='tzr("+data[key].id+")'>进入TA的个人主页 >></span>"+
            "</div></div>"
            $("#gzr").append(addhtml);

        }
        $("#btn_jz2").removeAttr("disabled");
        $("#gzr_page").val(parseInt(page) + 1);
    });
}


function jz(isjz){
    var page = $("#gzht_page").val();
    $("#btn_jz1").attr("disabled", "disabled")
    $("#jzz1").show();
    $.post("/user/focus/subjects", {'page' : page}, function(data){

        if(data == "" || data == null){
            if(isjz){
                isEmpty = false;
                $("#jzz1").hide();
                $("#my1").fadeIn(400);
                setTimeout(function(){$("#my1").fadeOut(1000);},2000);
            }else{
                isEmpty == true;
                $("#jzz1").hide();
                $("#gzht").append("<div class=\"panel panel-default\"><div class=\"panel-body\"><center><span style='color:#FF95CA;font-family: 微软雅黑; font-size: 20px'>您还没有关注过任何话题哦~</span></center></div></div>")
            }

            return;
        }
        if(isEmpty){
            $("#gzht").empty();
        }
        for(key in data){

            $("#jzz1").hide();
            var addhtml = "<div class=\"panel panel-default\" style='border: solid "+data[key].color+" 1px'><div class=\"panel-body\">" +
                "<img src=\""+data[key].logo+"?imageView2/1/w/50/h/50/q/90\" style=\"border:solid "+data[key].color+" 2px;box-shadow:0px 0px 15px "+data[key].color+";border-radius: 8px;\"/>"+
                "<span style='margin-left: 50px;color:"+data[key].color+"; font-size:18px'><b>"+data[key].name+"</b></span>"+
                "<span style='margin-left: 50px;color:#8E8E8E;font-size:18px'><b>主题帖数："+data[key].topic_num+"</b></span>"+
                "<span class=\"badge pull-right\" style = \"margin-top:15px;font-size:18px;cursor:pointer; background-color:"+data[key].color+"\" onclick='tzht("+data[key].id+")'><b>进入话题 >></b></span>"+
            "</div></div>"
            $("#gzht").append(addhtml);

        }
        $("#btn_jz1").removeAttr("disabled");
        $("#gzht_page").val(parseInt(page) + 1);
    });
}

function tzht(id){
    window.open("/post/subjects/sub4615"+id);
}

function tzr(id){
    window.open("");
}