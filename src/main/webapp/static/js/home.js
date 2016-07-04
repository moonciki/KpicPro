$().ready(function(){
    $("#types").append("<span class='loading'><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg\" height='15px'>加载中...</span>");
    $.post("/home/type",function(data){
        $("#types").empty();
        if(data == "" || data == null){
            $("#types").append("<span class='loading'>╮(╯_╰)╭ 暂无数据</span>");
        }else{
            for(key in data){
                $("#types").append("<div class=\"hot_type_unit\" style='cursor: pointer' onclick=\"openLink('/kabi/type/tp"+data[key].id+"')\">"+
                    "<img src=\""+data[key].picUrl+"?imageView2/1/w/100/h/100/q/95\" class=\"type_img\"/>"+
                    "<br/><span style=\"font-size: 18px; line-height: 2.5;padding-left: 35px\">"+
                    data[key].name+
                    "</span></div>");
            }
        }
    });

    all_pbars();


    $("#albums").append("<span class='loading'><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg\" height='15px'>加载中...</span>");
    $.post("/home/album", function(data){
        $("#albums").empty();
        if(data == "" || data == null){
            $("#albums").append("<span class='loading'>╮(╯_╰)╭ 暂无数据</span>");
        }else{
            for(key in data){
                $("#albums").append("<div class=\"album_unit\">"+
                    "<a href=\"/album/ab1654"+data[key].id+"\" target=\"_blank\"><img src=\""+data[key].imageUrl+"?imageView2/2/w/243/h/250/q/85\" class=\"album_img\" width=\"243px\" height=\"250px\"/></a>"+
                    "<br/><a href=\"/album/ab1654"+data[key].id+"\" target=\"_blank\">"+data[key].title+"</a><br/><span class=\"glyphicon glyphicon-user\"></span>"+
                    "&nbsp;<a href=\"/user/u6514"+data[key].userId+"/index.html\" target=\"_blank\">"+data[key].userName+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "<span class=\"glyphicon glyphicon-picture\"></span> &nbsp; <span style=\"font-size: 21px; color:#ff7287;font-style: italic\"><b>"+data[key].picNum+"</b></span> 张"+
                    "</div>")
            }
        }
    });


    $("#users").append("<span class='loading'><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg\" height='15px'>加载中...</span>");
    $.post("/home/user", function(data){
        $("#users").empty();
        if(data == "" || data == null){
            $("#users").append("<span class='loading'>╮(╯_╰)╭ 暂无数据</span>");
        }else{
            var i=1;
            var addhtml = "";
            for(key in data){
                addhtml+="<div class=\"main02_01\">"+
                    "<div class=\"main02_01_nr\"><div class=\"main02_01_nr_1\">"
                if(i<=3){
                    addhtml+="<span class=\"label label-default nr_num\">"
                }else{
                    addhtml+="<span class=\"label label-default nr_num_2\">"
                }

                addhtml+=i+"</span>"+
                    "</div><div class=\"main02_01_nr_2\">"
                if(data[key].avater == "" || data[key].avater == null){
                    addhtml+="<img src=\""+data[key].userPic+"?size=50\" class=\"nr_pic\"/>";
                }else{
                    addhtml+="<img src=\""+data[key].avater+"?imageView2/1/w/50/h/50/q/95\" class=\"nr_pic\"/>";
                }

                addhtml+="</div><div class=\"main02_01_nr_3\">"+
                    "<a href=\"/user/u6514"+data[key].id+"/index.html\" target=\"_blank\">"+data[key].name+"</a>&nbsp;&nbsp;";
                if(data[key].sex == 0){
                    addhtml+="<div class=\"boy\"></div>";
                }else{
                    addhtml+="<div class=\"girl\"></div>"
                }
                addhtml+="<br/><span style=\"font-size: 12px\">"+
                    "级别：<span style=\"color:green;font-weight: bold\">"+data[key].level+"</span>"+
                    "&nbsp;&nbsp;节操值：<span style=\"color:#fd5234;font-weight: bold\">"+data[key].jc+"</span>"+
                    "</span></div><div class=\"main02_01_nr_4\">";
                if(data[key].tag == "" || data[key].tag == null){
                    addhtml+="这家伙很懒，啥签名也没留下";
                }else{
                    addhtml+=data[key].tag;
                }
                addhtml+="</div></div>";
                i++;
            }

            $("#users").append(addhtml);
        }
    });


    $("#warns").append("<span class='loading'><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg\" height='15px'>加载中...</span>");
    $.post("/home/warn", function(data){
        $("#warns").empty();
        if(data == "" || data == null){
            $("#warns").append("<span class='loading'>╮(╯_╰)╭ 暂无数据</span>");
        }else{
            var i=1;
            var addhtml = "";
            for(key in data){
                addhtml+="<div class=\"main02_01\">"+
                    "<div class=\"main02_01_nr\"><div class=\"main02_01_nr_1\">"
                if(i<=3){
                    addhtml+="<span class=\"label label-default jg_num\">"
                }else{
                    addhtml+="<span class=\"label label-default jg_num_2\">"
                }

                addhtml+=i+"</span>"+
                    "</div><div class=\"main02_01_nr_2\">"
                if(data[key].avater == "" || data[key].avater == null){
                    addhtml+="<img src=\""+data[key].userPic+"?size=50\" class=\"jg_pic\"/>";
                }else{
                    addhtml+="<img src=\""+data[key].avater+"?imageView2/1/w/50/h/50/q/95\" class=\"jg_pic\"/>";
                }

                addhtml+="</div><div class=\"main02_01_nr_3\">"+
                    "<a href=\"/user/u6514"+data[key].id+"/index.html\" target=\"_blank\">"+data[key].name+"</a>&nbsp;&nbsp;";
                if(data[key].sex == 0){
                    addhtml+="<div class=\"boy\"></div>";
                }else{
                    addhtml+="<div class=\"girl\"></div>"
                }
                addhtml+="<br/><span style=\"font-size: 12px\">"+
                    "级别：<span style=\"color:green;font-weight: bold\">"+data[key].level+"</span>"+
                    "&nbsp;&nbsp;节操值：<span style=\"color:#fd5234;font-weight: bold\">"+data[key].jc+"</span>"+
                    "</span></div><div class=\"main02_01_nr_4\">";
                if(data[key].tag == "" || data[key].tag == null){
                    addhtml+="这家伙很懒，啥签名也没留下";
                }else{
                    addhtml+=data[key].tag;
                }
                addhtml+="</div></div>";
                i++;
            }

            $("#warns").append(addhtml);
        }
    });



    $("#posts").append("<span class='loading'><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg\" height='15px'>加载中...</span>");
    jz(true);
});

function jz(flag){
    $("#jz").attr("disabled", "disabled");
    var page = $("#page").val();
    $.post("/home/post",{'page':page}, function(data){
        if(flag){
            $("#posts").empty();
        }
        if(data == "" || data == null){
            if(flag){
                $("#posts").append("<span class='loading'>╮(╯_╰)╭ 暂无数据</span>");
            }else{
                $("#no_data").fadeIn(800);
                setTimeout(function(){$("#no_data").fadeOut(800);},2000);
            }

        }else{
            for(key in data){
                var addhtml = "<div class='main01_bw'><div class='pf'><span class=\"glyphicon glyphicon-triangle-left\"></span></div><div class='main01_01_user'>";

                if(data[key].avater == "" || data[key].avater == null){
                    addhtml += "<img src='" + data[key].userPic + "?size=70' class='index_pic'/>";
                }else{
                    addhtml += "<img src='" + data[key].avater + "?imageView2/1/w/70/h/70/q/95' class='index_pic'/>";
                }
                if(data[key].isjm == 1) {
                    addhtml += "<div class=\"manager_avater2\"><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/wangguan.png?imageView2/1/w/30/h/20/q/95/\"></div>"
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
                            addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/q/95\"" +
                                " onmouseover = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticvideo2.png?imageView2/1/w/156/h/90/q/95'\"" +
                                " onmouseout = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticvideo1.png?imageView2/1/w/156/h/90/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 2)\" title=\"点击播放该视频\"/>&nbsp;&nbsp;&nbsp;";
                        }else if(data[key].img[key2].imgKey == "3"){
                            addhtml += "<img src = \"http://7xnud1.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/q/95\"" +
                                " onmouseover = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticmusic2.jpg?imageView2/1/w/156/h/90/q/95'\"" +
                                " onmouseout = \"this.src='http://7xnud1.com1.z0.glb.clouddn.com/staticmusic1.jpg?imageView2/1/w/156/h/90/q/95'\" class='post_pic' style='cursor:pointer' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 3)\" title=\"点击播放该音频\"/>&nbsp;&nbsp;&nbsp;";
                        }else{
                            addhtml +="<img src=\""+data[key].img[key2].imagePath+"?imageView2/1/w/156/h/90/q/95\" class='post_pic' onclick=\"bigPic('"+data[key].img[key2].imagePath+"', 1)\" title=\"查看大图\"/>&nbsp;&nbsp;&nbsp;"
                        }
                    }
                    addhtml+="<div class=\"main01_01_02\">";
                }else{
                    addhtml+="<div class=\"main01_01_02_plus\">";
                }
                addhtml += "<button type=\"button\" title=\"进入该圈子\" class=\"btn btn-default btn-xs\" style='border:1px solid "+data[key].color+";background-color:"+data[key].color+"; color:#FFF' onclick=\"openLink('/post/subjects/sub4615"+data[key].pbarId+"')\">"+data[key].pbarName+"</button>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-user\" style=\"color: #84C1FF\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖人："
                    +data[key].userName+
                    "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color: #FF79BC\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;发帖日期："
                    +getLocalTime(data[key].createTime) +
                    "</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"glyphicon glyphicon-time\" style=\"color:#FF5809\"></span><span style=\"color: #9D9D9D;font-size: 12px;\">&nbsp;&nbsp;最后回复："
                    +getLocalTime(data[key].updateTime) +
                    "</span></div></div>"
                $("#posts").append(addhtml);
            }
            $("#jz").removeAttr("disabled");
            $("#page").val(parseInt(page)+1);
        }
    });
}


function all_pbars(){
    $("#zx").removeClass("curr");
    $("#rm").addClass("curr");
    $("#pbars").empty();
    $("#pbars").append("<span class='loading'><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg\" height='15px'>加载中...</span>");
    $.post("/home/pbar",function(data){
        $("#pbars").empty();
        if(data == "" || data == null){
            $("#pbars").append("<span class='loading'>╮(╯_╰)╭ 暂无数据</span>");
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
        }
    });
}

function sq(){
    var userId = $("#userId").val();
    if(userId == "" || userId == null){
        alert("要先登录哦(๑´ㅂ`๑)");
        return;
    }
    window.open("/user/subject/apply");
}

function all_new_pbars(){
    $("#rm").removeClass("curr");
    $("#zx").addClass("curr");
    $("#pbars").empty();
    $("#pbars").append("<span class='loading'><img src=\"http://7xtmxr.com1.z0.glb.clouddn.com/static/loading.jpg\" height='15px'>加载中...</span>");
    $.post("/home/new_pbar",function(data){
        $("#pbars").empty();
        if(data == "" || data == null){
            $("#pbars").append("<span class='loading'>╮(╯_╰)╭ 暂无数据</span>");
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
        }
    });
}



function openLink(link)
{
    window.open(link);
}

function getLocalTime(timer) {
    var d = new Date(timer);
    return (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes());
}