$().ready(function(){

    jz();

    $("#jz").click(function(){
        jz();
    });
});

function jz(){
    $("#jz").attr("disabled", "disabled");
    $("#load").show();
    var page = $("#page").val();
    $.post("/user/emotion/list", {'page':page}, function(data){
        if(data == null || data == ""){
            $("#load").hide();
            $("#no_data").fadeIn(400);
            setTimeout(function(){$("#no_data").fadeOut(1000);},2000);
        }else{
            var addhtml = "";
            for(key in data){
                addhtml+="<table class=\"table table-bordered\"><tr>"+
                    "<td><img src=\""+data[key].url+"?imageView2/1/w/80/h/80/q/95\"/></td><td>"+data[key].title+"</td>"+
                    "</tr><tr></table>";
            }
            $("#emotion_list").append(addhtml);
            $("#load").hide();
            $("#page").val(parseInt(page)+1);
            $("#jz").removeAttr("disabled");
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


/** 上传*/
$().ready(function() {

    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'emotion_upload',
        uptoken_url: '/pbar/upload',
        domain: 'http://7xs5lv.com1.z0.glb.clouddn.com/',
        max_file_size: '0.7mb',
        flash_swf_url: 'static/js/plupload/Moxie.swf',
        multi_selection:false,
        max_retries: 5,
        auto_start: true,
        filters: {
            mime_types: [
                {title: "Images files", extensions: "jpg,png,gif"},
            ],
            prevent_duplicates: true,
        },
        init: {
            'BeforeUpload': function (up, file) {
                $("#upload_loading").show();
                $("#emotion_upload").attr("disabled", "disabled");
                var filename = file.name;
                var extStart = filename.lastIndexOf(".");
                var ext = filename.substring(extStart, filename.length).toUpperCase();
                console.log(up);
                if (ext != ".JPG" && ext != ".PNG" && ext != ".GIF") {
                    alert("上传文件只支持jpg、png、gif格式的图片！");
                    up.destroy();
                } else {}
            },
            'UploadProgress': function (up, file) {
            },
            'Error': function (up, err, errTip) {
                console.log(err);
                console.log(errTip);
                alert("上传出错 -_-# 可能出现以下错误：\n①网络连接失效\n②上传文件过大\n③后台程序出现未知错误");
                $("#upload_loading").hide();
                $("#emotion_upload").removeAttr("disabled");
            },
            'FileUploaded': function (up, file, info) {
                console.log(info);
                info = JSON.parse(info);
                $("#upload_loading").hide();
                $("#url").val("http://7xs5lv.com1.z0.glb.clouddn.com/"+info.key);
                $("#img_url").append("<img src=\"http://7xs5lv.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/600/q/95\"/>");
                $("#an").append("<button type=\"button\" onclick=\"tj()\" class=\"btn btn-success\">添加</button>")
            },
            'Key': function (up, file) {
                var key = "";
                $.ajax({
                    url: "/pbar/uuid", async: false, context: document.body, success: function (data) {
                        if (data.success) {
                            key = data.uuid;
                        } else {
                            return;
                        }
                    }, error: function () {
                    }, "dataType": "json"
                });

                return key;
            }
        }
    });
});

function tj(){
    $("#an").empty();
    var title = $("#title").val();
    var url = $("#url").val();
    if(url.trim() == "" || url.trim() == ""){
        alert("出现未知错误，请刷新页面重试~");
        return;
    }

    if(title.trim().length == 0){
        title="无描述";
    }

    $.post("/user/emotion/save", {'title' : title, 'url' : url}, function(data){
        if(data){
            var addhtml = "<table class=\"table table-bordered\"><tr>"+
                "<td><img src=\""+url+"?imageView2/1/w/80/h/80/q/95\"/></td><td>"+title+"</td>"+
                "</tr><tr></table>";
            $("#emotion_list").append(addhtml);
            $("#emotion_upload").removeAttr("disabled");
            $("#img_url").empty();
            $("#title").val("");
        }else{
            alert("出现未知错误，请刷新页面重试~");
            return;
        }
    });
}