
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

    $("#tip_submit").click(function(){
        var msg = $("#tip_msg").val().trim();
        if(msg.length == 0 || msg.length > 100){
            alert("输入内容不合法：内容为空或者内容过长");
            return;
        }
        $("#jb_load").show();
        $("#tip_submit").attr("disabled", "disabled");
        $("#tip_close").attr("disabled", "disabled");
        var pbarId = $("#pbarId").val();
        var userId = $("#userId").val();
        var pbUserId = $("#pbar_user_id").val();

        $.post("/pbar/save/sq", {'userId':userId,'msg':msg,'pbarId':pbarId, 'pbUserId':pbUserId}, function(data){
            if(data.success){
                alert("申请成功！请静待佳音");
            }else{
                alert(data.msg);
            }
            $("#sqan").empty()
            $("#sqan").append("<button type=\"button\" class=\"btn btn-warning\"><span class=\"glyphicon glyphicon-hourglass\"></span>&nbsp;正在审核中</button>")
            $("#pl_tip").hide();
            $("#tip_submit").removeAttr("disabled");
            $("#tip_close").removeAttr("disabled");
            $("#jb_load").hide();
        });
    });

    $("#tip_close").click(function(){
        $("#pl_tip").hide();
        $("#tip_msg").val("");
        $("#tip_submit").removeAttr("disabled");
    })
    var ue = UE.getEditor('myEditor');
    ue.focus();
    var userId = $("#userId").val();
    var pbarId = $("#pbarId").val();
    var page = $("#page").val();
    var isempty = false;

    $.post("/user/all_small_manager", {'pbarId' : pbarId}, function(data){
        if(userId != "" && userId != null){
            $("#user_level_add").append(data.userLevel.level)
            $("#user_level").append("<span class=\"glyphicon glyphicon-apple\" style=\"color:#ff7575\"></span>下次升级进度：<span style=\"color:#FF79BC;font-weight: bold\">"+data.userLevel.score+"</span> / <span style=\"color:#00CACA;font-weight: bold\">"+data.allscore+"</span>"+
                "<div class=\"progress progress-striped active\"><div class=\"progress-bar progress-bar-success\" role=\"progressbar\"aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\""+
            "style=\"width: " + (data.userLevel.score/data.allscore)*100 + "%;\"></div></div>");
        }
        if(data.list == "" || data.list == null){
            $("#no-manager").show();
            if(data.status == null){
                $("#sq-manager").show();

            }

            if(data.status == 1){}

            if(data.status == 0){
                $("#sq-manager").show();
                $("#sqan").hide();
                $("#shzan").show();

            }

            $("#small_manager_num").append("<span style=\"color:#9D9D9D;font-family: 微软雅黑;font-size: 12px;\"><span class=\"glyphicon glyphicon-tags\" style=\"color: #FF95CA\"></span>"+
                "&nbsp;小管理猿已有<span style=\"color: #00AEAE;font-weight: bold\">0</span>人，剩<span style=\"color: #FF8000;font-weight: bold\">10</span>个名额</span>");

        }else{
            var flag = 0;
            for(key in data.list){
                var allscore = 0;
                if(data.list[key].level == 0){
                    allscore = 15;
                }else{
                    allscore = data.list[key].level * 15 * 2;
                }

                var addhtml = "<div class=\"panel panel-default\"><div class=\"panel-body\">"

                    if(data.list[key].avater == "" || data.list[key].avater == null){
                        addhtml+="<img src=\""+data.list[key].userPic+"?size=50\" class=\"manager_avater\"/>"
                    }else{
                        addhtml+="<img src=\""+data.list[key].avater+"?imageView2/1/w/50/h/50/q/95\" class=\"manager_avater\"/>"
                    }
                    if(data.isjm == 1){
                        addhtml+="<div class=\"manager_avater2\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\"></div>"
                    }
                    addhtml+="<div style=\"margin-left: 50px;margin-top: -1px\"><span style=\"margin-left: 10px; font-size: 10px;line-height: 1.8;font-family: 微软雅黑\"><a href=\"/user/u6514"+data.list[key].id+"/index.html\" target = \"_blank\">"+
                        data.list[key].name+"</a>&nbsp;&nbsp;<span class=\"badge\" onclick=\"sx("+data.list[key].id+")\" style=\"cursor:pointer;background-color: #46A3FF;margin-bottom: 5px\">私信</span></span>"+
                    "<div class=\"progress progress-striped active\" style=\"border: 2px solid #FF9797;\"><div class=\"progress-bar progress-bar-warning\" role=\"progressbar\"aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\"style=\"width: "+(data.list[key].score/allscore)*100+"%;\">"+
                    "<span class=\"sr-only\"></span></div></div><div style=\"margin-top: -20px\"><span style=\"color:#8E8E8E;font-size: 10px;font-family: 微软雅黑\">&nbsp;&nbsp;"+
                    "<span class=\"glyphicon glyphicon-apple\" style=\"color:#FF9797\"></span>&nbsp;<span style=\"color:#00AEAE;font-weight: bold\">"+data.list[key].level+"</span> 级 / <span style=\"color:#FF79BC;font-weight: bold\">"+data.list[key].score+"</span> 分</span>"+
                    "</div></div></div></div>"
                $("#manager_small_area").append(addhtml);
                flag++;

            }

            $("#small_manager_num").append("<span style=\"color:#9D9D9D;font-family: 微软雅黑;font-size: 12px;\"><span class=\"glyphicon glyphicon-tags\" style=\"color: #FF95CA\"></span>"+
            "&nbsp;小管理猿已有<span style=\"color: #00AEAE;font-weight: bold\">"+flag+"</span>人，剩<span style=\"color: #FF8000;font-weight: bold\">"+(10-flag)+"</span>个名额</span>");

            if(flag < 10){
                if(data.status == null){
                    $("#sq-manager").show();
                    return;
                }

                if(data.status == 1){return;}

                if(data.status == 0){
                    $("#sq-manager").show();
                    $("#sqan").hide();
                    $("#shzan").show();
                    return;
                }

            }
        }
    });

    $(".main01_01_reply").show();
    $(".current").removeClass();
    $("#home").removeClass();
    $("#home").addClass("current");
    $("#topic_post_area").empty();
    $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/> <div class=\"loading\"></div></div>");
    $("#isBlog").val("");
    $("#page").val("0");
    $("#isBoutique").val("");
    jiazai();


    function getLocalTime(timer) {
        var d = new Date(timer);
        return (d.getFullYear()) + "-" +
            (d.getMonth() + 1) + "-" +
            (d.getDate()) + " " +
            (d.getHours()) + ":" +
            (d.getMinutes());
    }

    $("#savePost").click(function(){
        var title = $("#title").val().trim();
        var content = ue.getContent();
        var shortContent = ue.getContentTxt();
        if(title.length == 0 || title.length > 36 || shortContent.length == 0 || shortContent.length > 5000){
            alert("输入内容不合法：标题和实际内容（不包含图片、音视频）不能为空，标题不超过36个字符，内容不能超出5000个字符（过大）");
            return;
        }
        $("#savePost").attr('disabled',"true");
        $(".reply_loading").show();
        var userName = $("#userName").val();
        var userPic = $("#userPic").val();
        if(shortContent.length > 210){
            shortContent = shortContent.substring(0, 210)+"...";
        }
        $("#title").val("");
        ue.setContent('', false);

        $.post("/tuan/post/save", {'title' : title, 'content' : content, 'shortContent' :　shortContent, 'pbarId' : pbarId, 'userId' : userId}, function(data){
            if(data == "" || data == null){
                alert("参数出错！");
                $(".reply_loading").hide();
                $("#title").val(title);
                ue.setContent(content, false);
                $("#savePost").removeAttr("disabled");
                return;
            }

            if(isempty){
                $("#topic_post_area").empty();
            }
            var addhtml = "<div class='main01_bw'><div class='pf'><span class=\"glyphicon glyphicon-triangle-left\"></span></div><div class='main01_01_user'><img src=\""+userPic+"\" class='index_pic'/>" +
                "</div><div class='main01_01_user_reply'></div><div class=\"main01_01\"><div class=\"main01_01_01\"><span class=\"label label-default tie\">帖</span>&nbsp;<a href=\"http-equiv\" style=\"font-size: 15px;font-family:微软雅黑\">"
                +"<a href='/post/reply/tp5416"+data.id+"' target=\"_blank\">"+data.title+
                "</a><span class=\"badge pull-right\" style=\"font-size:14px;background-color: #FF95CA\">0"+
                "</span><br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                +data.shortText+
                "</span></div>"

            addhtml += "<div class=\"main01_01_02\"><span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖人："
                +userName+
                "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖日期："
                +getLocalTime(data.createTime) +
                "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color:#FF5809\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;最后回复："
                +getLocalTime(data.updateTime) +
                "</span></div></div>"
            if(isempty){
                isempty = false;
            }
            $("#topic_post_area").append(addhtml);

            $(".reply_loading").hide();
            $(".xiu").fadeIn(400);
            setTimeout(function(){$(".xiu").fadeOut(1000);$("#savePost").removeAttr("disabled");},2000);
        })

    });

    $("#xl").click(function(){
        $("#xl").attr('disabled',"true");
        $("#pageloading2").hide();
        $("#pageloading").show();
        var page = $("#page").val();
        var isBlog = $("#isBlog").val();
        var isBoutique = $("#isBoutique").val();
        $.post("/tuan/all", {'pbarId' : pbarId, 'page' : page, 'isBlog' : isBlog, 'isBoutique' : isBoutique}, function(data){
            if(data == "" || data == null){
                $("#pageloading").hide();
                $("#pageloading2").fadeIn(800);
                setTimeout(function(){$("#pageloading2").fadeOut(800);},2000);
            }else{
                for(key in data){
                    var addhtml = "<div class='main01_bw'><div class='pf'><span class=\"glyphicon glyphicon-triangle-left\"></span></div><div class='main01_01_user'>";

                    addhtml += "<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"
                    if(data[key].avater == "" || data[key].avater == null){
                        addhtml += "<img src='" + data[key].userPic + "?size=70' class='index_pic'/>";
                    }else{
                        addhtml += "<img src='" + data[key].avater + "?imageView2/1/w/70/h/70/interlace/0/q/95' class='index_pic'/>";
                    }
                    addhtml+="</a>"
                    if(data[key].isjm == 1) {
                        addhtml += "<div class=\"manager_avater2\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\"></div>"
                    }

                    addhtml += "</div><div class='main01_01_user_reply'></div><div class=\"main01_01\"><div class=\"main01_01_01\">"
                    if(data[key].isBoutique == 1){
                        addhtml+="<span class=\"label label-warning jing\">精</span>&nbsp;"
                    }
                    if(data[key].isTop == 100){
                        addhtml+="<span class=\"label label-warning ding\">顶</span>&nbsp;"
                    }
                    if(data[key].isBlog == 1){
                        addhtml+="<span class=\"label label-default bo\">文</span>&nbsp;<a class=\"clj_blog\" href=\"/post/reply/at5416" + data[key].id + "\" target=\"_blank\">"
                    }else{
                        addhtml+="<span class=\"label label-default tie\">帖</span>&nbsp;<a href=\"/post/reply/tp5416" + data[key].id + "\" target=\"_blank\" style=\"font-size: 15px;font-family:微软雅黑\">"
                    }
                    addhtml += data[key].title + "</a>"
                    addhtml+= "<span class=\"badge pull-right\" style=\"font-size:14px;background-color: #FF95CA\">"
                        +data[key].replyNum+
                        "</span><br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                        +data[key].shortText+
                        "</span></div>"
                    if(data[key].img != "" && data[key].img != null){
                        addhtml += "<div class=\"main01_01_img\"><div class=\"main01_01_img_num\"><span style=\"font-family:微软雅黑;font-size: 12px\"><span class='glyphicon glyphicon-share-alt'></span> 共<span class='fxs'>&nbsp;&nbsp;"+data[key].num+"&nbsp;&nbsp;</span>个分享</div>"
                        for(key2 in data[key].img){
                            if(data[key].img[key2].imgKey == "2"){
                                addhtml += "<img src = \"http://7xwibn.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/interlace/0/q/95\"" +
                                    " onmouseover = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticvideo2.png?imageView2/1/w/156/h/90/interlace/0/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/interlace/0/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 2)\" title=\"点击播放该视频\"/>&nbsp;&nbsp;&nbsp;";
                            }else if(data[key].img[key2].imgKey == "3"){
                                addhtml += "<img src = \"http://7xwibn.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/interlace/0/q/95\"" +
                                    " onmouseover = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticmusic2.jpg?imageView2/1/w/156/h/90/interlace/0/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/interlace/0/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 3)\" title=\"点击播放该音频\"/>&nbsp;&nbsp;&nbsp;";
                            }else{
                                addhtml +="<img src=\""+data[key].img[key2].imagePath+"?imageView2/1/w/156/h/90/interlace/0/q/95\" class='post_pic' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 1)\" title=\"查看大图\"/>&nbsp;&nbsp;&nbsp;"
                            }
                        }
                    }

                    addhtml += "<div class=\"main01_01_02\"><span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖人："
                        +data[key].userName+
                        "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖日期："
                        +getLocalTime(data[key].createTime) +
                        "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color:#FF5809\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;最后回复："
                        +getLocalTime(data[key].updateTime) +
                        "</span></div></div>"

                    $("#topic_post_area").append(addhtml);
                    $("#page").val(parseInt(page) + 1);
                    $("#pageloading").hide();
                    $("#xl").removeAttr("disabled");
                }
            }
        });
    });

    function jiazai(){
        var userId = $("#userId").val();
        var pbarId = $("#pbarId").val();
        var isBoutique = $("#isBoutique").val();
        var page = $("#page").val();
        var isempty = false;
        var isBlog = $("#isBlog").val();
        $.post("/tuan/all", {'pbarId' : pbarId, 'page' : page, 'isBlog' : isBlog, 'isBoutique' : isBoutique}, function(data){
            $("#page").val(0);
            $("#topic_post_area").empty();
            if(data == "" || data == null){
                isempty = true;
                $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/><div class=\"no_data\"></div></div>");
            }else{
                for(key in data){
                    var addhtml = "<div class='main01_bw'><div class='pf'><span class=\"glyphicon glyphicon-triangle-left\"></span></div><div class='main01_01_user'>";
                    addhtml += "<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"
                    if(data[key].avater == "" || data[key].avater == null){
                        addhtml += "<img src='" + data[key].userPic + "?size=70' class='index_pic'/>";
                    }else{
                        addhtml += "<img src='" + data[key].avater + "?imageView2/1/w/70/h/70/interlace/0/q/95' class='index_pic'/>";
                    }
                    addhtml+="</a>";
                    if(data[key].isjm == 1) {
                        addhtml += "<div class=\"manager_avater2\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\"></div>"
                    }
                    addhtml += "</div><div class='main01_01_user_reply'></div><div class=\"main01_01\"><div class=\"main01_01_01\">"
                    if(data[key].isBoutique == 1){
                        addhtml+="<span class=\"label label-warning jing\">精</span>&nbsp;"
                    }
                    if(data[key].isTop == 100){
                        addhtml+="<span class=\"label label-warning ding\">顶</span>&nbsp;"
                    }
                    if(data[key].isBlog == 1){
                        addhtml+="<span class=\"label label-default bo\">文</span>&nbsp;<a class=\"clj_blog\" href=\"/post/reply/at5416" + data[key].id + "\" target=\"_blank\">"
                    }else{
                        addhtml+="<span class=\"label label-default tie\">帖</span>&nbsp;<a href=\"/post/reply/tp5416" + data[key].id + "\" target=\"_blank\" style=\"font-size: 15px;font-family:微软雅黑\">"
                    }
                    addhtml += data[key].title + "</a>"
                    addhtml+= "<span class=\"badge pull-right\" style=\"font-size:14px;background-color: #FF95CA\">"
                        +data[key].replyNum+
                        "</span><br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                        +data[key].shortText+
                        "</span></div>"
                    if(data[key].img != "" && data[key].img != null){
                        addhtml += "<div class=\"main01_01_img\"><div class=\"main01_01_img_num\"><span style=\"font-family:微软雅黑;font-size: 12px\"><span class='glyphicon glyphicon-share-alt'></span> 共<span class='fxs'>&nbsp;&nbsp;"+data[key].num+"&nbsp;&nbsp;</span>个分享</div>"
                        for(key2 in data[key].img){
                            if(data[key].img[key2].imgKey == "2"){
                                addhtml += "<img src = \"http://7xwibn.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/interlace/0/q/95\"" +
                                    " onmouseover = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticvideo2.png?imageView2/1/w/156/h/90/interlace/0/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/interlace/0/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 2)\" title=\"点击播放该视频\"/>&nbsp;&nbsp;&nbsp;";
                            }else if(data[key].img[key2].imgKey == "3"){
                                addhtml += "<img src = \"http://7xwibn.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/interlace/0/q/95\"" +
                                    " onmouseover = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticmusic2.jpg?imageView2/1/w/156/h/90/interlace/0/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xwibn.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/interlace/0/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 3)\" title=\"点击播放该音频\"/>&nbsp;&nbsp;&nbsp;";
                            }else{
                                addhtml +="<img src=\""+data[key].img[key2].imagePath+"?imageView2/1/w/156/h/90/interlace/0/q/95\" class='post_pic' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 1)\" title=\"查看大图\"/>&nbsp;&nbsp;&nbsp;"
                            }
                        }
                    }
                    addhtml += "<div class=\"main01_01_02\"><span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖人："
                        +data[key].userName+
                        "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖日期："
                        +getLocalTime(data[key].createTime) +
                        "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color:#FF5809\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;最后回复："
                        +getLocalTime(data[key].updateTime) +
                        "</span></div></div>"

                    $("#topic_post_area").append(addhtml);
                    $("#xl").removeAttr('disabled');
                    $("#page").val(page + 1);
                }
            }
        });
    }


    $("#onlyPost").click(function(){
        $(".main01_01_reply").show();
        $(".current").removeClass();
        $("#ytj_xl").hide();
        $("#tcy_xl").hide();
        $("#xl").show();
        $("#xl").attr("disabled","disabled");
        $("#onlyPost").removeClass();
        $("#onlyPost").addClass("current");
        $("#topic_post_area").empty();
        $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/> <div class=\"loading\"></div></div>");
        $("#isBlog").val("0");
        $("#page").val("0");
        $("#isBoutique").val("");
        jiazai("");
    });

    $("#onlyBlog").click(function(){
        $(".main01_01_reply").hide();
        $(".current").removeClass();
        $("#ytj_xl").hide();
        $("#tcy_xl").hide();
        $("#xl").show();
        $("#xl").attr("disabled","disabled");
        $("#onlyBlog").removeClass();
        $("#onlyBlog").addClass("current");
        $("#topic_post_area").empty();
        $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/> <div class=\"loading\"></div></div>");
        $("#isBlog").val("1");
        $("#page").val("0");
        $("#isBoutique").val("");
        jiazai("");
    });

    $("#home").click(function(){
        $(".main01_01_reply").show();
        $(".current").removeClass();
        $("#ytj_xl").hide();
        $("#tcy_xl").hide();
        $("#xl").show();
        $("#xl").attr("disabled","disabled");
        $("#home").removeClass();
        $("#home").addClass("current");
        $("#topic_post_area").empty();
        $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/> <div class=\"loading\"></div></div>");
        $("#isBlog").val("");
        $("#page").val("0");
        $("#isBoutique").val("");
        jiazai();
    });

    $("#jp").click(function(){
        $(".main01_01_reply").show();
        $(".current").removeClass();
        $("#ytj_xl").hide();
        $("#tcy_xl").hide();
        $("#xl").show();
        $("#xl").attr("disabled","disabled");
        $("#jp").removeClass();
        $("#jp").addClass("current");
        $("#topic_post_area").empty();
        $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/> <div class=\"loading\"></div></div>");
        $("#isBlog").val("");
        $("#page").val("0");
        $("#isBoutique").val("1");
        jiazai();
    });

    $("#tcy").click(function(){
        $(".main01_01_reply").hide();
        $(".current").removeClass();
        $("#ytj_xl").hide();
        $("#tcy_xl").show();
        $("#xl").hide();
        $("#tcy_xl").attr("disabled","disabled");
        $("#tcy").removeClass();
        $("#tcy").addClass("current");
        $("#topic_post_area").empty();
        $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/> <div class=\"loading\"></div></div>");
        $("#isBlog").val("");
        $("#page").val("0");
        $("#isBoutique").val("");
        jiazaituanchengyuan(false);
    });

    $("#ytj").click(function(){
        $(".main01_01_reply").hide();
        $(".current").removeClass();
        $("#ytj_xl").show();
        $("#tcy_xl").hide();
        $("#xl").hide();
        $("#ytj_xl").attr("disabled","disabled");
        $("#ytj").removeClass();
        $("#ytj").addClass("current");
        $("#topic_post_area").empty();
        $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/> <div class=\"loading\"></div></div>");
        $("#isBlog").val("");
        $("#page").val("0");
        $("#isBoutique").val("");
        jiazaiytj(false);
    });

    $("#ytj_xl").click(function(){
        $("#ytj_xl").attr("disabled", "disabled");
        jiazaiytj(true);
    });

    $("#tcy_xl").click(function(){
        $("#tcy_xl").attr("disabled", "disabled");
        jiazaituanchengyuan(true);
    });

    function jiazaiytj(isload){

        var pbarId = $("#pbarId").val();
        var page = $("#page").val();
        var isempty = false;
        if(isload){
            $("#pageloading2").hide();
            $("#pageloading").show();
        }
        $.post("/pbar/album/list", {'pbarId' : pbarId, 'page' : page}, function(data){
            if(!isload){
                $("#topic_post_area").empty();
            }

            if(data == "" || data == null){
                isempty = true;
                if(isload){
                    $("#pageloading").hide();
                    $("#pageloading2").fadeIn(800);
                    setTimeout(function(){$("#pageloading2").fadeOut(800);},2000);
                }else{
                    $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/><div class=\"no_data\"></div></div>");
                }

            }else{
                for(key in data){
                    var addhtml = "<div class='main01_bw'><div class='pf'><span class=\"glyphicon glyphicon-triangle-left\"></span></div><div class='main01_01_user'>";
                    addhtml += "<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"
                    if(data[key].avater == "" || data[key].avater == null){
                        addhtml += "<img src='" + data[key].userPic + "?size=70' class='index_pic'/>";
                    }else{
                        addhtml += "<img src='" + data[key].avater + "?imageView2/1/w/70/h/70/interlace/0/q/95' class='index_pic'/>";
                    }
                    addhtml+="</a>";
                    if(data[key].isjm == 1) {
                        addhtml += "<div class=\"manager_avater2\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\"></div>"
                    }
                    addhtml += "</div><div class='main01_01_user_reply'></div><div class=\"main01_01\"><div class=\"main01_01_01\">"

                    addhtml+="<span class=\"label label-default tu\">图</span>&nbsp;<a href=\"/album/ab1654" + data[key].id + "\" target=\"_blank\" style=\"font-size: 15px;font-family:微软雅黑\">"

                    addhtml += data[key].title + "</a>"

                    addhtml+= "<br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                        +data[key].msg+
                        "</span></div>"
                    addhtml += "<div class=\"main01_01_img\"><div class=\"main01_01_img_num2\"><span class='glyphicon glyphicon-camera'></span> 共<span class='fxs2'> "+data[key].picNum+" </span>张图片</div>"
                    addhtml += "<img src=\""+data[key].imageUrl+"?imageView2/1/w/673/interlace/0/q/75\"/>"

                    addhtml += "<div class=\"main01_01_02\"><span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;图集制作人："
                        +data[key].userName+
                        "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;制作日期："
                        +getLocalTime(data[key].createTime) +
                        "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color:#FF5809\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发布日期："
                        +getLocalTime(data[key].publishTime) +
                        "</span></div></div>"

                    $("#topic_post_area").append(addhtml);
                    $("#ytj_xl").removeAttr('disabled');
                    $("#page").val(page + 1);
                    if(isload){
                        $("#pageloading").hide();
                    }
                }
            }
        });
    }

    function jiazaituanchengyuan(isload){

        var pbarId = $("#pbarId").val();
        var page = $("#page").val();
        var isempty = false;
        if(isload){
            $("#pageloading2").hide();
            $("#pageloading").show();
        }
        $.post("/user/pbar/users", {'pbarId' : pbarId, 'page' : page}, function(data){
            if(!isload){
                $("#topic_post_area").empty();
            }

            if(data == "" || data == null){
                isempty = true;
                if(isload){
                    $("#pageloading").hide();
                    $("#pageloading2").fadeIn(800);
                    setTimeout(function(){$("#pageloading2").fadeOut(800);},2000);
                }else{
                    $("#topic_post_area").append("<div class=\"main01_01_no_data\"><br/><br/><br/><div class=\"no_data\"></div></div>");
                }

            }else{
                for(key in data){

                    var allscore = 0;
                    if(data[key].level == 0){
                        allscore = 15;
                    }else{
                        allscore = data[key].level * 15 * 2;
                    }

                    var addhtml = "<div class='pbar_users'><div class='pbar_users_01'>";
                        if(data[key].avater == "" || data[key].avater == null){
                            addhtml+="<img src=\""+data[key].userPic+"?size=100\" class=\"pbar_users_pic\"/>";
                        }else{
                            addhtml+="<img src=\""+data[key].avater+"?imageView2/1/w/100/h/100/interlace/0/q/95\" class=\"pbar_users_pic\"/>";
                        }
                    if(data[key].isjm == 1) {
                        addhtml += "<div class=\"manager_avater2\"><img src=\"http://7xwibn.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\"></div>"
                    }
                    addhtml+="</div><div class='pbar_users_02'>昵称：<a href=\"/user/u6514"+data[key].id+"/index.html\" target = \"_blank\">"+data[key].name+"</a>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"badge\" onclick=\"sx("+data[key].id+")\" style=\"cursor:pointer;background-color: #46A3FF;margin-bottom: 5px\">私信</span><br/>"+
						"性别："
						if(data[key].sex == 0){
							addhtml+="<div class=\"boy\"></div>";
						}else{
							addhtml+="<div class=\"girl\"></div>";
						}
						addhtml+="<br/>签名：<span style=\"font-size:12px;font-color:#FF79BC\">"
						if(data[key].tag == "" || data[key].tag == null){
							addhtml+="这家伙很潇洒，没有留下什么签名~";
						}else{
							addhtml+=data[key].tag;
						}
						addhtml+="</span>"
						addhtml+="</div><div class='pbar_users_03'>"+
                            "<div class=\"progress progress-striped active\" style=\"border: 2px solid #65b2ff;\"><div class=\"progress-bar progress-bar-info\" role=\"progressbar\"aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\"style=\"width: "+(data[key].score/allscore)*100+"%;\">"+
                            "<span class=\"sr-only\"></span></div></div><div style=\"margin-top: -20px\"><span style=\"color:#8E8E8E;font-size: 10px;font-family: 微软雅黑\">&nbsp;&nbsp;"+
                            "<span class=\"glyphicon glyphicon-apple\" style=\"color:#FF9797\"></span>&nbsp;<span style=\"color:#00AEAE;font-weight: bold\">"+data[key].level+"</span> 级 / <span style=\"color:#FF79BC;font-weight: bold\">"+data[key].score+"</span> 分</span>"+
                            "</div></div></div></div>";
                    $("#topic_post_area").append(addhtml);
                    $("#tcy_xl").removeAttr('disabled');
                    $("#page").val(page + 1);
                    if(isload){
                        $("#pageloading").hide();
                    }
                }
            }
        });
    }


});

function bigPic(img, flag){

    $("#tp_video").show();
    if(flag == 2){
        $(".pic_big_01").show();
        $(".pic_big_01_01").show();
        $("#pic_video").append("<embed type=\"application/x-shockwave-flash\" class=\"edui-faked-video\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\""+img+"\" width=\"682px\" height=\"440px\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\" />");
    }else if(flag == 3){
        var addhtml = "<div class=\"pic_big_03\"><div class='pic_big_03_1'></div><div class='pic_big_03_2'><center><div style=\"display: inline-block;width:520px; height:50px;\"> <audio style=\"margin-top:10px;width: 450px\" controls=true>"+
            "<source src=\""+img+"\" />"+
            "</audio>&nbsp;<button style=\"margin-top: -22px\" type=\"button\" onclick=\"close_music()\" class=\"btn btn-danger btn-sm\"><span class=\"glyphicon glyphicon-remove-sign\"></span>&nbsp;关闭</button></div></center></div><div class='pic_big_03_1'></div></div>"
        $("#music_bf").append(addhtml);
    }else{
        $("#pic_pic").append("<center><div style='margin-top: 5%'></div><div style=\"overflow-x :auto;overflow-y :auto;width:906px;height: 467px\"><img src=\""+img+"\" title=\"还原\" style=\"cursor:zoom-out\" onclick=\"clearAll()\"/></div></center>")
    }
}

function clearAll(){
    $("#tp_video").hide();
    $("#pic_video").empty();
    $("#pic_pic").empty();
    $(".pic_big_01").hide();
    $(".pic_big_01_01").hide();
}

function close_music(){
    $("#tp_video").hide();
    $("#music_bf").empty();
}

function sq(){
    var userId = $("#userId").val();
    if(userId == "" || userId == null){
        alert("(*￣ω￣)~要先登录哦~");
        return;
    }
    $("#pl_tip").show();
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

function sx(userId){
    $("#sx_userId").val(userId);
    var login_user = $("#userId").val();
    if(login_user==null || login_user == ""){
        alert("请先登录~");
        return;
    }
    var jc = $("#jc").val();
    if(jc <= 14){
        alert("您目前处于禁言状态，无法私信任何人~");
        return;
    }
    if(userId == login_user){
        alert("自己不能给自己写私信哦~(⊙_⊙)~");
        return;
    }
    $("#pl_sx").show();
}

$("#sx_close").click(function(){
    $("#sx_msg").val("");
    $("#pl_sx").hide();
});

$("#sx_submit").click(function(){
    var content = $("#sx_msg").val();
    if(content.trim().length == 0 || content.trim().length > 100){
        alert("输入内容为空或者字符过长");
        return;
    }
    $("#sx_submit").attr("disabled", "disabled");
    $("#sx_close").attr("disabled", "disabled");
    $("#sx_load").show();
    var userId = $("#sx_userId").val();
    $.post("/user/private/save", {'userId':userId, 'content':content}, function(data){
        if(data){
            alert("发送成功！");
        }else{
            alert("非常抱歉，后台程序出现未知错误，发送失败！");
        }
        $("#sx_submit").removeAttr("disabled");
        $("#sx_close").removeAttr("disabled");
        $("#sx_load").hide();
        $("#sx_msg").val("");
        $("#pl_sx").hide();
    });
});

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

var pbar_userId = $("#pbar_user_id").val();
$.post("/user/pbar/manager", {'userId':pbar_userId}, function(data){
   if(data == "" || data == null){
       $("#big_manager").append("获取不到大管理猿的信息");
       return;
   }
    var addhtml = "<center>";
            if(data.avater == "" || data.avater == null){
                addhtml+="<img src=\""+data.userPic+"?size=100\" class=\"userbigpic\"/>";
            }else{
                addhtml+="<img src=\""+data.avater+"?imageView2/1/w/100/h/100/interlace/0/q/95\" class=\"userbigpic\"/>";
            }
    addhtml+="</center><br/><br/><center>昵称：<a href=\"/user/u6514"+data.id+"/index.html\" target = \"_blank\">"+data.name+"</a>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"badge\" onclick=\"sx("+data.id+")\" style=\"cursor:pointer;background-color: #46A3FF;margin-bottom: 5px\">私信</span></center>";
    $("#big_manager").append(addhtml);
});


