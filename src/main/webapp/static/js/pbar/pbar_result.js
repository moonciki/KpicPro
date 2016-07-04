$().ready(function(){
    $("#ym_s").click(function(){
       var kw = $("#ym_kw").val().trim();
       window.location.href="/kabi/search/kw_"+kw;
    });

    $("#cjht").click(function(){
        var userId = $("#userId").val();
        if(userId == "" || userId ==null){
            alert("请先登录~");
            return;
        }
        window.location.href="/user/subject/apply";
    });
});

jz(false);

function jz(isreload){
    $("#jzz").show();
    var page = $("#page").val();
    var kw = $("#kw").val();
    $("#jz").attr("disabled","disabled");
    $.post("/kabi/search/result",{'kw':kw,'page':page}, function(data){
        if(data == "" || data == null){
            $("#jzz").hide();
            if(isreload){
                $("#no_data_2").fadeIn(800);
                setTimeout(function(){$("#no_data_2").fadeOut(800);},2000);
            }else{
                $("#no_data_1").show();
            }
        }else{
            var addhtml = "";
            for(key in data){
                addhtml+="<div class=\"pbar_con\"><div class=\"pbar_con_1\"><div class=\"pbar_con_1_1\">"+
                    "<img src=\""+data[key].logo+"?imageView2/1/w/100/h/100/q/95\" style=\"border-radius: 6px;border: solid 2px "+data[key].color+";box-shadow:0px 0px 26px "+data[key].color+";\"/>"+
                    "</div><div class=\"pbar_con_1_2\"><span style=\"font-size: 21px; font-weight: bold\">"+data[key].name+"</span>&nbsp;&nbsp;<a href=\"/post/subjects/sub4615"+data[key].id+"\" target=\"_blank\"><span class='glyphicon glyphicon-arrow-right'></span>&nbsp;进入圈子</a><br/>"+
                    "<span class=\"glyphicon glyphicon-barcode\"></span>&nbsp;分类：<a href=\"/kabi/type/tp"+data[key].typeId+"\" target=\"_blank\">"+data[key].type+"</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "<span class=\"glyphicon glyphicon-file\"></span>&nbsp;帖子：<span style=\"color:orange;font-weight: bold\">"+data[key].topicNum+"</span><br/>"+
                    "<span class=\"glyphicon glyphicon-star-empty\"></span>&nbsp;关注：<span style=\"color:#00AEAE;font-weight: bold\">"+data[key].focusNum+"</span><br/>"+
                    "<span class=\"glyphicon glyphicon-tags\"></span>&nbsp;标签："
                for(key2 in data[key].tags){
                    addhtml+="<span class=\"glyphicon glyphicon-tag\" style=\"color: "+data[key].tags[key2].color+"\"><span style=\"font-family: 微软雅黑\">"+data[key].tags[key2].name+"</span></span> &nbsp;"
                }
                addhtml+="</div><div class=\"pbar_con_2\">"+data[key].msg+"</div></div></div>"
            }
            $("#s_result").append(addhtml);
            $("#page").val(parseInt(page)+1);
            $("#jz").removeAttr("disabled");
            $("#jzz").hide();
        }
    });
}

function key(){
    var kw = $("#kw").val();
    var ym=document.getElementById('main').innerHTML;//ss是要高亮的区域，div的id值
    document.getElementById('main').innerHTML=ym.replace(/kw/g, "<span style=\"color:red;font-weight: bold\">"+kw+"</span>");
}