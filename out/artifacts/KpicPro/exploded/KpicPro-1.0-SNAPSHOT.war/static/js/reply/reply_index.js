$().ready(function(){

    $('.music_button').hover(function(){
        $(this).addClass("music_button_hover");
    },function(){
        $(this).removeClass("music_button_hover");
    });

    $('.video_button').hover(function(){
        $(this).addClass("music_button_hover");
    },function(){
        $(this).removeClass("music_button_hover");
    });

    $('.eif_button').hover(function(){
        $(this).addClass("music_button_hover");
    },function(){
        $(this).removeClass("music_button_hover");
    });

    var ue = UE.getEditor('myEditor');

    $(".reply_body_main_02").mouseover(function(){
        $("#jb").show();
    });

    $(".reply_body_main_02").mouseout(function(){
       $("#jb").hide();
    });

    var topicId = $("#topicId").val();
    var page = $("#page").val();
    var isEmpty = false;
    var lou = 0;
    $("#xl").hide();
    $.post("/tuan/post/getallreply",{'topicId' : topicId, 'page' : page},function(data){
        if(data == "" || data == null){
            isEmpty = true;
            $("#reply_loading").hide();
            $("#no-data").show();

        }else{
            $("#reply_post_area").empty();
            $("#xl").show();
            for(key in data){
                lou = (parseInt(lou) + 1)
                var addHtml = "<div class=\"reply_body_main\"><div class = \"reply_body_main_01\">"
                addHtml += "<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"
                if(data[key].avater==""||data[key].avater == null){
                          addHtml+="<img src=\""+data[key].userPic+"?size=70\" class=\"userpic_hpost\"/>"
                      }else{
                          addHtml+="<img src=\""+data[key].avater+"?imageView2/1/w/70/h/70/interlace/0/q/95\" class=\"userpic_hpost\"/>"
                      }
                addHtml+="</a>";
                if(data[key].isjm){
                    addHtml+="<div class=\"manager_avater2\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\" title='节操达人'/></div>"
                }
                    addHtml+="</div><div class=\"main01_01_user_reply\" style = \"margin-top: 23px\"></div><div class=\"reply_body_main_02\">"+
                    "<div class=\"pf3\"><span class=\"glyphicon glyphicon-triangle-left\"></span></div>"+
                    "<div class=\"reply_body_main_02_01\">"+
                    "<span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖人："+
                        data[key].userName +
                "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;回复时间："+
             getLocalTime(data[key].createTime)+ "</span>"
                        if(data[key].isTip){
                            addHtml+="<span style = \"color: #BEBEBE; font-size: 13px\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-flag\"></span>&nbsp;已举报</span>"
                        }else{
                            addHtml+="<span class = \"reply_jb"+data[key].id+"\" style = \"color: #BEBEBE; cursor:pointer; font-size: 13px\" onclick=\"jubao2("+data[key].id+")\">&nbsp;&nbsp;&nbsp;&nbsp;"+
                                "<span class=\"glyphicon glyphicon-flag\"></span>&nbsp;举报</span>"
                        }
                addHtml+="<span class=\"badge pull-right\" style=\"font-size:14px;background-color: #E1E100\"># "+ lou +"</span>"
                var topicUserId = $("#topicUserId").val();
                if(data[key].userId == topicUserId){
                    addHtml+="<span class=\"badge pull-right\" style='background-color: #FFB5B5;margin-right: 20px'>楼主</span>"
                }
                        addHtml+="</div><div class=\"reply_body_main_content\"><span style=\"font-size: 12px; line-height: 1.8\">" +
                    data[key].content +
                    "</span></div><div class=\"reply_body_main_02_01\" style=\"color:#ADADAD; font-size: 11px\"><a href='javascript:void(0);' onclick='comment("+data[key].id+","+data[key].userId+")'><span class=\"glyphicon glyphicon-align-left\"></span> 查看短评(" +
                    +data[key].replyNum+
                    ")</a></div></div></div>";

                $("#reply_post_area").append(addHtml);

            }

            $("#page").val(page + 1);
        }
    });


    $("#saveReply").click(function(){
        //String content, Long topicId, Long userId
        var shortText = ue.getContentTxt();
        if(shortText.trim().length == 0 || shortText.trim().length >2000){
            alert("输入内容不合法：实际内容（不包含图片、音视频）为空或者超过2000个字符");
            return;
        }
        $("#saveReply").attr('disabled',"true");
        var content = ue.getContent();
        var topicId = $("#topicId").val();
        var userId = $("#userId").val();
        var userName = $("#userName").val();
        var userPic = $("#userPic").val();
        var pbarId = $("#pbarId").val();
        var topicUserId = $("#topicUserId").val();
        if(shortText.length > 100){
            shortText = shortText.substring(0, 100)+"...";
        }
        ue.setContent('', false);
        $(".reply_loading").show();
        $.post("/tuan/reply/save", {'content':content, 'shortText':shortText, 'topicId' : topicId, 'userId' : userId, 'pbarId' : pbarId, 'topicUserId' : topicUserId}, function(data){
            if(data == "" || data == null){
                alert("发生未知错误！");
                $(".reply_loading").hide();
                ue.setContent(content, false);
                $("#saveReply").removeAttr("disabled");
                return;
            }else{
                if(isEmpty){
                    $("#reply_post_area").empty();
                }
                var addHtml = "<div class=\"reply_body_main\"><div class = \"reply_body_main_01\">"+
                    "<img src=\""+userPic+"\" class=\"userpic_hpost\"/>"+
                    "</div><div class=\"main01_01_user_reply\" style = \"margin-top: 23px\"></div><div class=\"reply_body_main_02\">"+
                    "<div class=\"pf3\"><span class=\"glyphicon glyphicon-triangle-left\"></span></div>"+
                    "<div class=\"reply_body_main_02_01\">"+
                    "<span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖人："+
                    userName +
                "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;回复时间："+
                 getLocalTime(data.createTime) +
                    "<span class=\"badge pull-right\" style=\"font-size:14px;background-color: #E1E100\"># NEW</span>"

                if(userId == topicUserId){
                    addHtml+="<span class=\"badge pull-right\" style='background-color: #FFB5B5;margin-right: 20px'>楼主</span>"
                }

                    addHtml+="</div><div class=\"reply_body_main_content\"><span style=\"font-size: 12px; line-height: 1.8\">" +
                data.content +
                "</span></div><div class=\"reply_body_main_02_01\" style=\"color:#ADADAD; font-size: 11px\"><span class=\"glyphicon glyphicon-align-left\"></span> 查看回复(0)</div></div></div>";

                $("#reply_post_area").append(addHtml);

                $(".reply_loading").hide();
                $(".xiu").fadeIn(400);
                setTimeout(function(){$(".xiu").fadeOut(1000);$("#saveReply").removeAttr("disabled");},2000);
            }

        });

    });

    $("#xl").click(function(){
        $("#xl").attr('disabled',"true");
        $("#pageloading2").hide();
        $("#pageloading").show();
        var page = $("#page").val();
        $.post("/tuan/post/getallreply",{'topicId' : topicId, 'page' : page},function(data){
            if(data == "" || data == null){
                $("#pageloading").hide();
                $("#pageloading2").fadeIn(800);
                setTimeout(function(){$("#pageloading2").fadeOut(800);},2000);
            }else{
                for(key in data){
                    lou = (parseInt(lou) + 1);
                    var addHtml = "<div class=\"reply_body_main\"><div class = \"reply_body_main_01\">"
                    addHtml += "<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"
                    if(data[key].avater==""||data[key].avater == null){
                            addHtml+="<img src=\""+data[key].userPic+"?size=70\" class=\"userpic_hpost\"/>"
                        }else{
                            addHtml+="<img src=\""+data[key].avater+"?imageView2/1/w/70/h/70/interlace/0/q/95\" class=\"userpic_hpost\"/>"
                        }
                    addHtml+="</a>";
                    if(data[key].isjm){
                        addHtml+="<div class=\"manager_avater2\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\" title='节操达人'/></div>"
                    }
                        addHtml+="</div><div class=\"main01_01_user_reply\" style = \"margin-top: 23px\"></div><div class=\"reply_body_main_02\">"+
                        "<div class=\"pf3\"><span class=\"glyphicon glyphicon-triangle-left\"></span></div>"+
                        "<div class=\"reply_body_main_02_01\">"+
                        "<span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖人："+
                        data[key].userName +
                        "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;回复时间："
                        + getLocalTime(data[key].createTime)+ "</span>"
                    if(data[key].isTip){
                        addHtml+="<span style = \"color: #BEBEBE; font-size: 13px\">&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-flag\"></span>&nbsp;已举报</span>"
                    }else{
                        addHtml+="<span class = \"reply_jb"+data[key].id+"\" style = \"color: #BEBEBE; cursor:pointer; font-size: 13px\" onclick=\"jubao2("+data[key].id+")\">&nbsp;&nbsp;&nbsp;&nbsp;"+
                            "<span class=\"glyphicon glyphicon-flag\"></span>&nbsp;举报</span>"
                    }

                        addHtml += "<span class=\"badge pull-right\" style=\"font-size:14px;background-color: #E1E100\"># "+ lou +"</span>"
                        var topicUserId = $("#topicUserId").val();
                        if(data[key].userId == topicUserId){
                            addHtml+="<span class=\"badge pull-right\" style='background-color: #FFB5B5;margin-right: 20px'>楼主</span>"
                        }
                            addHtml+="</div><div class=\"reply_body_main_content\"><span style=\"font-size: 12px; line-height: 1.8\">" +
                        data[key].content +
                        "</span></div><div class=\"reply_body_main_02_01\" style=\"color:#ADADAD; font-size: 11px\"><a href='javascript:void(0);' onclick='comment("+data[key].id+","+data[key].userId+")'><span class=\"glyphicon glyphicon-align-left\"></span> 查看短评(" +
                        +data[key].replyNum+
                    ")</div></div></div>";

                    $("#reply_post_area").append(addHtml);

                }
                    $("#page").val(parseInt(page) + 1);
                    $("#pageloading").hide();
                    $("#xl").removeAttr("disabled");
                }
        });
    });

});

function comment(reply_id, reply_user_id){
    $("#tj").attr('disabled',"true");
    $("#short_page").val(0);
    $("#short_loading").show();
    $("#short_content").val("");
    $("#short_userId").val(reply_user_id);
    $("#shortText").empty();
    shortReply(false, reply_id);
}

function short_xl(){
    $("#tj").attr('disabled',"true");
    $("#shortxl").attr('disabled',"true");
    $("#load_xl").show();
    var reply_id = $("#reply_id").val();
    shortReply(true, reply_id);
}

function closePic(){
    $("#pl_big").hide();
    $("#short_reply_content").empty();
}

var yuan_reply_userId = 0;

function shortReply(is_load, reply_id){
    $("#shortxl").attr('disabled',"true");
    if(!is_load){
        $("#pl_big").show();
    }
    var page = $("#short_page").val();

    $("#reply_id").val(reply_id);

    $.post("/all/short/reply", {'replyId' : reply_id, 'page' : page}, function(data){
        if(data.success){

            yuan_reply_userId = data.reply_userId;

            $("#short_page").val(parseInt(page)+1);
            if(!is_load){
                $("#shortText").append(data.shortText);
            }

            $("#short_userId").val(yuan_reply_userId);

            var addhtml = "";

            if(data.data == "" || data.data == null){
                $("#short_loading").hide();
                if(is_load){
                    $("#load_xl").hide();
                    $("#load_xl_nodata").fadeIn(800);
                    setTimeout(function(){$("#load_xl_nodata").fadeOut(800);},2000);
                }else{
                    $("#short_reply_content").append(addhtml+"<span style='color:red;padding-top: 30px'><center><b>* 没有数据</b></center></span>");
                }
                $("#tj").removeAttr("disabled");
                return;
            }

            for(key in data.data){
                addhtml+= "<div class=\"panel panel-default\" style=\"border: solid 1px #FFF\">"+
                    "<div class=\"panel-body\"><div class=\"pl_user\">"
                    addhtml += "<a href=\"/user/u6514"+data.data[key].userId+"/index.html\" target=\"_blank\">"
                        if(data.data[key].avater =="" || data.data[key].avater==null){
                            addhtml+="<img src=\"" + data.data[key].userPic +"?size=48\" style=\"border-radius:100px\"/>"
                        }else{
                            addhtml+="<img src=\"" + data.data[key].avater +"?imageView2/1/w/48/h/48/q/95\" style=\"border-radius:100px\"/>"

                        }
                    addhtml+="</a>"
                    addhtml+="</div><div class=\"pl_pf\"><div class=\"pl_dpf\"><span class=\"glyphicon glyphicon-triangle-left\"></span></div></div>"+
                    "<div class=\"pl_text\"><div class=\"pl_text_01\"><span class=\"glyphicon glyphicon-user\"></span> 发帖人：<a href=\"/user/u6514"+data.data[key].userId+"/index.html\" target=\"_blank\">"+data.data[key].userName+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "<span class=\"glyphicon glyphicon-time\"></span> 发帖时间："+getLocalTime(data.data[key].createTime)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    if(data.data[key].isTip){
                        addhtml+="<span style = \"color: #BEBEBE; font-size: 13px\"><span class=\"glyphicon glyphicon-flag\"></span> 已举报</span></span>"
                    }else{
                        addhtml+="<span class = \"short_jb"+data.data[key].shortId+"\" style = \"color: #BEBEBE; cursor:pointer; font-size: 13px\" onclick=\"jubao3("+data.data[key].shortId+")\"><span class=\"glyphicon glyphicon-flag\"></span>"+
                        " 举报</span></span>"
                    }


                    addhtml+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick=\"transfer('"+data.data[key].replyId+"','"+data.data[key].userId+"','"+data.data[key].userName+"')\"><span class=\"glyphicon glyphicon-share-alt\"></span>"+
                    " 回复TA</a></div>"+
                    "<div class=\"pl_text_02\">"+data.data[key].content+"</div></div></div></div>"
            }

            $("#short_loading").hide();
            $("#short_reply_content").append(addhtml);
            $("#shortxl").removeAttr("disabled");

        }else{
            $("#short_reply_content").append("<span style='color:red;padding-top: 30px'><center><b>* 非常抱歉，数据加载出错。。</b></center></span>")
        }

        $("#tj").removeAttr("disabled");
        $("#load_xl").hide();
    });
}

function transfer(replyId, userId, userName){
    var u_id = $("#userId").val();
    if(u_id == userId){
        alert("不能回复自己！");
        return;
    }
    location.href="#s_reply_area";
    $("#short_userId").val(userId);
    $("#short_content").val("@"+userName+":");
}

function saveShort(){
    $("#tj").attr('disabled',"true");
    $("#short_fs_loading").show();
    var content = $("#short_content").val();
    var replyId = $("#reply_id").val();
    var pbarId = $("#pbarId").val();
    if(content.trim().length == 0 || content.trim().length > 100){
        alert("内容为空或者输入内容过长！");
        $("#tj").removeAttr("disabled");
        $("#short_fs_loading").hide();
        return;
    }
    var replyUserId = $("#short_userId").val();
    $("#short_content").val("");
    $.post("/short/save", {'content':content, 'replyUserId':replyUserId, 'replyId':replyId, 'pbarId' : pbarId}, function(data){
        if(data == null || data==""){
            alert("抱歉，程序出现未知错误╭(╯^╰)╮");
        }else{
            var addhtml = "<div class=\"panel panel-default\" style=\"border: solid 1px #FFF\">"+
                "<div class=\"panel-body\"><div class=\"pl_user\">" +
                    "<img src=\"" + $("#userPic2").val() +"\" style=\"border-radius:100px\"/>"+
                "</div><div class=\"pl_pf\"><div class=\"pl_dpf\"><span class=\"glyphicon glyphicon-triangle-left\"></span></div></div>"+
                "<div class=\"pl_text\"><div class=\"pl_text_01\"><span class=\"glyphicon glyphicon-user\"></span> 发帖人：<a href=\"\">"+$("#userName").val()+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                "<span class=\"glyphicon glyphicon-time\"></span> 发帖时间："+data.createTime+
                "<span class=\"badge pull-right\" style=\"background-color: #FF9797\"># 1</span></div>"+
                "<div class=\"pl_text_02\">"+data.content+"</div></div></div></div>"
            $("#short_reply_content").append(addhtml);
        }
        $("#short_userId").val(yuan_reply_userId);
        $("#tj").removeAttr("disabled");
        $("#short_fs_loading").hide();
    })
}

function jubao(id){
    var userId = $("#userId").val();
    if(userId == "" || userId == null){
        alert("(*￣ω￣)~要先登录哦~");
        return;
    }
    $("#pl_tip").show();
    $("#tip_id").val(id);
    $("#tip_type").val(1);
}

function jubao2(id){
    var userId = $("#userId").val();
    if(userId == "" || userId == null){
        alert("(*￣ω￣)~要先登录哦~");
        return;
    }
    $("#pl_tip").show();
    $("#tip_id").val(id);
    $("#tip_type").val(2);

}

function jubao3(id){
    var userId = $("#userId").val();
    if(userId == "" || userId == null){
        alert("(*￣ω￣)~要先登录哦~");
        return;
    }
    $("#pl_tip").show();
    $("#tip_id").val(id);
    $("#tip_type").val(3);

}

$("#tip_submmit").click(function(){
    $("#load_area").append("&nbsp;&nbsp;<span style=\"color:#9D9D9D; font-size: 14px\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/loading.jpg\" style=\"width: 30px; height: 30px\"/>&nbsp;举报提交中..</span>");
    $("#tip_submmit").attr("disabled", "disabled");
    $("#tip_close").attr("disabled", "disabled");
    var pbarId = $("#pbarId").val();
    var id = $("#tip_id").val();
    var type = $("#tip_type").val();
    var title = $("input[name='tip_type']:checked").val();
    var msg = $("#tip_msg").val();
    if(id!="" && id != null){
        if(type == 1){
            $.post("/topic/tip", {'id':id,'title':title,'msg':msg,'pbarId':pbarId}, function(data){
                if(data.success){
                    $("#load_area").empty();
                    $("#load_area").append("&nbsp;&nbsp;<span style=\"color:#9D9D9D; font-size: 14px\"><span class=\"glyphicon glyphicon-ok-circle\" style=\"color:#73BF00\"></span>&nbsp;举报成功！感谢您的反馈(*￣ω￣)~</span>")

                }else{}

                setTimeout(function(){
                    $("#load_area").empty();
                },2000);
                $(".jb_topic"+id).removeAttr("onclick");
                $(".jb_topic"+id).css("cursor","auto");
                $(".jb_topic"+id).empty();
                $(".jb_topic"+id).append("&nbsp;&nbsp;<span class=\"glyphicon glyphicon-flag\"></span>&nbsp;已举报")
                $("#pl_tip").fadeOut(2000);
                setTimeout(function(){
                    $("#tip_submmit").removeAttr("disabled");
                    $("#tip_close").removeAttr("disabled");
                },2000);
            });
        }else if(type == 2){
            $.post("/reply/tip", {'id':id,'title':title,'msg':msg,'pbarId':pbarId}, function(data){
                if(data.success){
                    $("#load_area").empty();
                    $("#load_area").append("&nbsp;&nbsp;<span style=\"color:#9D9D9D; font-size: 14px\"><span class=\"glyphicon glyphicon-ok-circle\" style=\"color:#73BF00\"></span>&nbsp;举报成功！感谢您的反馈(*￣ω￣)~</span>");

                }else{}

                setTimeout(function(){
                    $("#load_area").empty();
                },2000);
                $(".reply_jb"+id).removeAttr("onclick");
                $(".reply_jb"+id).css("cursor","auto");
                $(".reply_jb"+id).empty();
                $(".reply_jb"+id).append("&nbsp;&nbsp;<span class=\"glyphicon glyphicon-flag\"></span>&nbsp;已举报")
                $("#pl_tip").fadeOut(2000);
                setTimeout(function(){
                    $("#tip_submmit").removeAttr("disabled");
                    $("#tip_close").removeAttr("disabled");
                },2000);


            });
        }else if(type == 3){
            $.post("/short/tip", {'id':id,'title':title,'msg':msg,'pbarId':pbarId}, function(data){
                if(data.success){
                    $("#load_area").empty();
                    $("#load_area").append("&nbsp;&nbsp;<span style=\"color:#9D9D9D; font-size: 14px\"><span class=\"glyphicon glyphicon-ok-circle\" style=\"color:#73BF00\"></span>&nbsp;举报成功！感谢您的反馈(*￣ω￣)~</span>")
                }else{}

                setTimeout(function(){
                    $("#load_area").empty();
                },2000);
                $(".short_jb"+id).removeAttr("onclick");
                $(".short_jb"+id).css("cursor","auto");
                $(".short_jb"+id).empty();
                $(".short_jb"+id).append("&nbsp;&nbsp;<span class=\"glyphicon glyphicon-flag\"></span>&nbsp;已举报")
                $("#pl_tip").fadeOut(2000);
                setTimeout(function(){
                    $("#tip_submmit").removeAttr("disabled");
                    $("#tip_close").removeAttr("disabled");
                },2000);

            });
        }else{
            return;
        }
    }

});

$("#tip_close").click(function(){
    $("#pl_tip").hide();
    $("#tip_msg").val("");
    $("#tip_submmit").removeAttr("disabled");
})

function getLocalTime(timer) {
    var d = new Date(timer);
    return (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes());
}

function share_music(mp3url){
    music_share_close();
    var addhtml = "<div style=\"width:682px; height:50px;\"> <audio style=\"width: 682px\" controls=true>"+
        "<source src=\""+mp3url+"\" />"+
        "</audio></div>"
    var ue = UE.getEditor('myEditor');
    ue.execCommand('inserthtml', addhtml);
}

function self_music(){
    var mp3wl = $("#mp3wl").val().trim();
    if(mp3wl == "" || mp3wl == null){
        alert("请输入外链");
        return;
    }

    music_share_close();
    var addhtml = "<div style=\"width:682px; height:50px;\"> <audio style=\"width: 682px\" controls=true>"+
        "<source src=\""+mp3wl+"\" />"+
        "</audio></div>"
    var ue = UE.getEditor('myEditor');
    ue.execCommand('inserthtml', addhtml);
}

function share_video(){

    var videoUrl = $("#videoUrl").val().trim();

    if(videoUrl == "" || videoUrl == null){
        alert("请填写视频flash地址");
        return;
    }
    video_share_close();
    var addhtml = "<embed type=\"application/x-shockwave-flash\" class=\"edui-faked-video\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\""+videoUrl+"\" width=\"682px\" height=\"440px\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\" />";
    var ue = UE.getEditor('myEditor');
    ue.execCommand('inserthtml', addhtml);
}

$("#eif_share").click(function(){
    $("#tp_eif").show();
});



jz();

$("#jz").click(function(){
    jz();
});

var i=1;

function jz(){
    $("#jz").attr("disabled", "disabled");
    $("#load").show();
    var page = $("#page_eif").val();
    $.post("/user/emotion/list", {'page':page}, function(data){
        if(data == null || data == ""){
            $("#load").hide();
            if(i==1){
                $("#eif_content").append("<center><br/><br/><br/><br/><a href='/user/emotion/save' target='_blank'>当前您的表情包空空如也，点击添加</a>或者去<a href=\"/emotion/pool\">表情池</a>直接勾选</center>");
            }
            $("#no_data").fadeIn(400);
            setTimeout(function(){$("#no_data").fadeOut(1000);},2000);
        }else{
            var addhtml = "";
            for(key in data){
                addhtml+="<div class=\"eif_unit\"><img onclick=\"emotion_add('"+data[key].url+"?imageView2/1/w/600/q/95')\" style=\"border-radius:6px\" src=\""+data[key].url+"?imageView2/1/w/114/h/114/q/95\" title=\""+data[key].title+"\"/></div>"
            }
            $("#eif_content").append(addhtml);
            $("#load").hide();
            $("#page_eif").val(parseInt(page)+1);
            $("#jz").removeAttr("disabled");
        }
    });
    i++;
}

function eif_close(){
    $("#tp_eif").hide();
}

function emotion_add(url){
    var addhtml = "<img src=\""+url+"\"/>"
    var ue = UE.getEditor('myEditor');
    ue.execCommand('inserthtml', addhtml);
    $("#tp_eif").hide();
}

function qxjp_click(id){
    $("#qxjp").empty();
    $("#qxjp").append("取消中..");
    $.post("/user/m/qxjp", {'id' : id}, function(data){
        if(data){
            $("#qxjp").empty();
            $("#qxjp").append("设置成功");
            $(".reply_body").removeClass("jp");
        }else{
            $("#qxjp").empty();
            $("#qxjp").append("设置失败，请刷新重试");
        }
    });
}

function jp_click(id){
    $("#jp").empty();
    $("#jp").append("加精中..");
    var userId = $("#topicUserId").val();
    $.post("/user/m/jp", {'id' : id, 'userId' : userId}, function(data){
        if(data){
            $("#jp").empty();
            $("#jp").append("加精成功");
            $(".reply_body").addClass("jp");
        }else{
            $("#jp").empty();
            $("#jp").append("设置失败，请刷新重试");
        }
    });
}

function qxzd_click(id){
    $("#qxzd").empty();
    $("#qxzd").append("取消中..");
    $.post("/user/m/qxzd", {'id' : id}, function(data){
        if(data){
            $("#qxzd").empty();
            $("#qxzd").append("设置成功");
        }else{
            $("#qxzd").empty();
            $("#qxzd").append("设置失败，请刷新重试");
        }
    });
}

function zd_click(id){
    $("#zd").empty();
    $("#zd").append("置顶中..");
    var userId = $("#topicUserId").val();
    $.post("/user/m/zd", {'id' : id, 'userId' : userId}, function(data){
        if(data){
            $("#zd").empty();
            $("#zd").append("置顶成功");
        }else{
            $("#zd").empty();
            $("#zd").append("设置失败，请刷新重试");
        }
    });
}