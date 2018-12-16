$().ready(function(){
    $("#ym_s").click(function(){
        var kw = $("#ym_kw").val().trim();
        window.location.href="/kabi/search/m_kw_"+kw;
    });

    $("#cjht").click(function(){
        var userId = $("#userId").val();
        if(userId == "" || userId ==null){
            alert("请先登录~");
            return;
        }
        window.location.href="/send/list_add";
    });
});

jz(false);

function jz(isreload){
    $("#jzz").show();
    var page = $("#page").val();
    var kw = $("#kw").val();
    $("#jz").attr("disabled","disabled");
    $.post("/kabi/search/m_result",{'kw':kw,'page':page}, function(data){
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
                addhtml+="<div class=\"music_con\"><div class=\"music_con_01\">";
                        if(data[key].cover == null){
                            addhtml+="<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/Fo-o_lSgGdcE26EoJmEZuj4DRQAa?imageView2/2/w/150/h/150/q/95\" class=\"music_cover\"/>"
                        }else{
                            addhtml+="<img src=\""+data[key].cover+"?imageView2/2/w/150/h/150/q/95\" class=\"music_cover\"/>"
                        }
                    addhtml+="</div><div class=\"music_con_02\"><a href=\"/media/mv"+data[key].id+"\" target=\"_blank\" style=\"font-size: 20px; font-weight: bold\">"+
                    data[key].title+"</a>&nbsp;&nbsp;"
                            if(data[key].sourceType == 1){
                                addhtml+="<span class=\"label label-success\" style=\"cursor: pointer\" title=\"点击可跳转至源站播放\" onclick=\"window.open('"+data[key].sourcePath+"')\">分享源：QQ音乐</span>"
                            }else if(data[key].sourceType == 2){
                                addhtml+="<span class=\"label label-danger\" style=\"cursor: pointer\" title=\"点击可跳转至源站播放\" onclick=\"window.open('"+data[key].sourcePath+"')\">分享源：网易云音乐</span>"
                            }else if(data[key].sourceType == 3){
                                addhtml+="<span class=\"label label-primary\" style=\"cursor: pointer\" title=\"点击可跳转至源站播放\" onclick=\"window.open('"+data[key].sourcePath+"')\">分享源：百度音乐</span>"
                            }else if(data[key].sourceType == 4){
                                addhtml+="<span class=\"label label-warning\" style=\"cursor: pointer\" title=\"点击可跳转至源站播放\" onclick=\"window.open('"+data[key].sourcePath+"')\">分享源：虾米音乐</span>"
                            }

                addhtml+="<br/><span style=\"font-size: 16px;line-height: 1.8;\"><b>所属音乐人：</b>"+data[key].songer+"<br/>"+
                "<b>所属放送单：</b><a href=\"/send/list"+data[key].slId+"\" target=\"_blank\">"+data[key].slTitle+"</a>"+
                "<br/><b>数据统计栏：</b>"+
                "<span class=\"down_tb_01_s glyphicon glyphicon-expand\" title=\"播放量\" style=\"font-size: 12px\"></span> &nbsp;"+data[key].play+
                "<span class=\"down_tb_02_s glyphicon glyphicon-align-center\" title=\"弹幕量\" style=\"margin-left:10px; font-size: 12px\"></span> &nbsp;"+data[key].damaku+
                "<span class=\"down_tb_03_s glyphicon glyphicon-heart-empty\" title=\"收藏量\" style=\"margin-left:10px; font-size: 12px\"></span> &nbsp;"+data[key].fav+
                "<span class=\"down_tb_05_s glyphicon glyphicon-ban-circle\" title=\"弹幕池容量\" style=\"margin-left:10px; font-size: 12px\"></span> &nbsp;"+data[key].damakuPool+"</span></div></div>"
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