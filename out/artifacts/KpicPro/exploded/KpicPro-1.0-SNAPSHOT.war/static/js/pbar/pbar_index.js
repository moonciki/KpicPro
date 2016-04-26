
$().ready(function(){
    $("#tip_submit").click(function(){
        $("#jb_load").show();
        $("#tip_submit").attr("disabled", "disabled");
        $("#tip_close").attr("disabled", "disabled");
        var pbarId = $("#pbarId").val();
        var userId = $("#userId").val();
        var msg = $("#tip_msg").val();

        $.post("/pbar/save/sq", {'userId':userId,'msg':msg,'pbarId':pbarId}, function(data){
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
                    addhtml+="<div style=\"margin-left: 50px;margin-top: -1px\"><span style=\"margin-left: 10px; font-size: 10px;line-height: 1.8;font-family: 微软雅黑\"><a href=\"\" target=\"_blank\">"+
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
        $("#savePost").attr('disabled',"true");
        $(".reply_loading").show();
        var userName = $("#userName").val();
        var userPic = $("#userPic").val();
        var title = $("#title").val();
        var content = ue.getContent();
        var shortContent = ue.getContentTxt();
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
                +data.title+
                "</a><span class=\"badge pull-right\" style=\"font-size:14px;background-color: #FF95CA\">"
                +data.replyNum+
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

                    if(data[key].avater == "" || data[key].avater == null){
                        addhtml += "<img src='" + data[key].userPic + "?size=70' class='index_pic'/>";
                    }else{
                        addhtml += "<img src='" + data[key].avater + "?imageView2/1/w/70/h/70/q/95' class='index_pic'/>";
                    }

                    addhtml += "</div><div class='main01_01_user_reply'></div><div class=\"main01_01\"><div class=\"main01_01_01\">"
                    if(data[key].isBlog == 1){
                        addhtml+="<span class=\"label label-default bo\">博</span>&nbsp;<a href=\"http-equiv\" style=\"font-size: 15px;font-family:微软雅黑\">"
                    }else{
                        addhtml+="<span class=\"label label-default tie\">帖</span>&nbsp;<a href=\"/post/reply/tp5416" + data[key].id + "\" target=\"_blank\" style=\"font-size: 15px;font-family:微软雅黑\">"
                    }
                    addhtml += data[key].title + "</a>"
                    if(data[key].isBoutique == 1){
                        addhtml+="&nbsp;&nbsp;<span class=\"label label-warning jing\">精</span>"
                    }
                    addhtml+= "<span class=\"badge pull-right\" style=\"font-size:14px;background-color: #FF95CA\">"
                        +data[key].replyNum+
                        "</span><br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                        +data[key].shortText+
                        "</span></div>"
                    if(data[key].img != "" && data[key].img != null){
                        addhtml += "<div class=\"main01_01_img\"><div class=\"main01_01_img_num\"><span style=\"font-family:微软雅黑;font-size: 12px\"><span class='glyphicon glyphicon-share-alt'></span> 共<span class='fxs'>&nbsp;&nbsp;"+data[key].num+"&nbsp;&nbsp;</span>个分享</div>"
                        for(key2 in data[key].img){
                            if(data[key].img[key2].imgKey == "2"){
                                addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/q/95\"" +
                                    " onmouseover = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticvideo2.png?imageView2/1/w/156/h/90/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 2)\" title=\"点击播放该视频\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
                            }else if(data[key].img[key2].imgKey == "3"){
                                addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/q/95\"" +
                                    " onmouseover = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticmusic2.jpg?imageView2/1/w/156/h/90/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 3)\" title=\"点击播放该音频\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
                            }else{
                                addhtml +="<img src=\""+data[key].img[key2].imagePath+"?imageView2/1/w/156/h/90/q/95\" class='post_pic' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 1)\" title=\"查看大图\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
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

                    if(data[key].avater == "" || data[key].avater == null){
                        addhtml += "<img src='" + data[key].userPic + "?size=70' class='index_pic'/>";
                    }else{
                        addhtml += "<img src='" + data[key].avater + "?imageView2/1/w/70/h/70/q/95' class='index_pic'/>";
                    }

                    addhtml += "</div><div class='main01_01_user_reply'></div><div class=\"main01_01\"><div class=\"main01_01_01\">"
                    if(data[key].isBlog == 1){
                        addhtml+="<span class=\"label label-default bo\">博</span>&nbsp;<a href=\"http-equiv\" style=\"font-size: 15px;font-family:微软雅黑\">"
                    }else{
                        addhtml+="<span class=\"label label-default tie\">帖</span>&nbsp;<a href=\"/post/reply/tp5416" + data[key].id + "\" target=\"_blank\" style=\"font-size: 15px;font-family:微软雅黑\">"
                    }
                    addhtml += data[key].title + "</a>"
                    if(data[key].isBoutique == 1){
                        addhtml+="&nbsp;&nbsp;<span class=\"label label-warning jing\">精</span>"
                    }
                    addhtml+= "<span class=\"badge pull-right\" style=\"font-size:14px;background-color: #FF95CA\">"
                        +data[key].replyNum+
                        "</span><br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                        +data[key].shortText+
                        "</span></div>"
                    if(data[key].img != "" && data[key].img != null){
                        addhtml += "<div class=\"main01_01_img\"><div class=\"main01_01_img_num\"><span style=\"font-family:微软雅黑;font-size: 12px\"><span class='glyphicon glyphicon-share-alt'></span> 共<span class='fxs'>&nbsp;&nbsp;"+data[key].num+"&nbsp;&nbsp;</span>个分享</div>"
                        for(key2 in data[key].img){
                            if(data[key].img[key2].imgKey == "2"){
                                addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/q/95\"" +
                                    " onmouseover = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticvideo2.png?imageView2/1/w/156/h/90/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 2)\" title=\"点击播放该视频\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
                            }else if(data[key].img[key2].imgKey == "3"){
                                addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/q/95\"" +
                                    " onmouseover = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticmusic2.jpg?imageView2/1/w/156/h/90/q/95'\"" +
                                    " onmouseout = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 3)\" title=\"点击播放该音频\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
                            }else{
                                addhtml +="<img src=\""+data[key].img[key2].imagePath+"?imageView2/1/w/156/h/90/q/95\" class='post_pic' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 1)\" title=\"查看大图\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
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

    $("#ytj").click(function(){
        $(".main01_01_reply").hide();
        $(".current").removeClass();
        $("#ytj_xl").show();
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

                    if(data[key].avater == "" || data[key].avater == null){
                        addhtml += "<img src='" + data[key].userPic + "?size=70' class='index_pic'/>";
                    }else{
                        addhtml += "<img src='" + data[key].avater + "?imageView2/1/w/70/h/70/q/95' class='index_pic'/>";
                    }

                    addhtml += "</div><div class='main01_01_user_reply'></div><div class=\"main01_01\"><div class=\"main01_01_01\">"

                    addhtml+="<span class=\"label label-default tu\">图</span>&nbsp;<a href=\"/album/ab1654" + data[key].id + "\" target=\"_blank\" style=\"font-size: 15px;font-family:微软雅黑\">"

                    addhtml += data[key].title + "</a>"

                    addhtml+= "<br/><span style=\"line-height: 1.8;font-size: 12px;color: #9D9D9D\">"
                        +data[key].msg+
                        "</span></div>"
                    addhtml += "<div class=\"main01_01_img\"><div class=\"main01_01_img_num2\"><span class='glyphicon glyphicon-camera'></span> 共<span class='fxs2'> "+data[key].picNum+" </span>张图片</div>"
                    addhtml += "<img src=\""+data[key].imageUrl+"?imageView2/1/w/673/q/75\"/>"

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


});

function bigPic(img, flag){

    $("#tp_video").show();
    if(flag == 2){
        $(".pic_big_01").show();
        $(".pic_big_01_01").show();
        $("#pic_video").append("<embed type=\"application/x-shockwave-flash\" class=\"edui-faked-video\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\""+img+"\" width=\"672px\" height=\"440px\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\" />");
    }else if(flag == 3){
        var addhtml = "<center><div class=\"pic_big_03\"><div style=\"display: inline-block;width:750px; height:50px;\"> <audio style=\"width: 750px\" controls=true>"+
            "<source src=\""+img+"\" />"+
            "</audio></div>&nbsp;<button style=\"margin-top: -22px\" type=\"button\" onclick=\"close_music()\" class=\"btn btn-danger btn-sm\"><span class=\"glyphicon glyphicon-remove-sign\"></span>&nbsp;关闭</button></div></center>"
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
    var addhtml = "<div style=\"width:672px; height:50px;\"> <audio style=\"width: 672px\" controls=true>"+
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
    var addhtml = "<div style=\"width:672px; height:50px;\"> <audio style=\"width: 672px\" controls=true>"+
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
    var addhtml = "<embed type=\"application/x-shockwave-flash\" class=\"edui-faked-video\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\""+videoUrl+"\" width=\"672px\" height=\"440px\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\" />";
    var ue = UE.getEditor('myEditor');
    ue.execCommand('inserthtml', addhtml);
}

function sx(userId){
    $("#sx_userId").val(userId);
    var login_user = $("#userId").val();
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
    $("#sx_submit").attr("disabled", "disabled");
    $("#sx_close").attr("disabled", "disabled");
    $("#sx_load").show();
    var userId = $("#sx_userId").val();
    var content = $("#sx_msg").val();
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