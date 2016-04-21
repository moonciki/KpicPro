$().ready(function(){
    $("#tj").click(function(){
        var name = $("#name").val().trim();
        var songer = $("#songer").val().trim();
        var musicUrl = $("#musicUrl").val().trim();
        if(name == "" || name == null){
            alert("音乐名称不能为空！");
            return;
        }

        if(musicUrl == "" || musicUrl == null){
            alert("请上传MP3文件！");
            return;
        }
        if(songer == "" || songer == null){
            $("#songer").val("佚名");
        }
        var name2 = $("#name").val();
        var songer2 = $("#songer").val();
        $("#tj").attr("disabled", "disabled");
        $.post("/user/make/music",{'name':name2,'songer':songer2,'musicUrl':musicUrl}, function(data){
           if(data){
               $("#html2").hide();
               $("#html").show();
           }else{
                alert("非常抱歉，后台程序出错了。。看看自己填写的东西是不是有哪里不符合规定呢？");
           }
        });
    });
});

/** 上传*/
$().ready(function() {

    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'music_upload',
        uptoken_url: '/music/upload',
        domain: 'http://7xt8jn.com1.z0.glb.clouddn.com/',
        max_file_size: '15mb',
        flash_swf_url: 'static/js/plupload/Moxie.swf',
        multi_selection:false,
        max_retries: 5,
        auto_start: true,
        filters: {
            mime_types: [
                {title: "Music files", extensions: "mp3"},
            ],
            prevent_duplicates: true,
        },
        init: {
            'BeforeUpload': function (up, file) {
                $("#music_upload").attr("disabled", "disabled");
                $("#upload_loading").show();
                var filename = file.name;
                var extStart = filename.lastIndexOf(".");
                var ext = filename.substring(extStart, filename.length).toUpperCase();
                console.log(up);
                if (ext != ".MP3") {
                    alert("上传文件只支持mp3格式的文件！");
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
                $("#music_upload").removeAttr("disabled");
            },
            'FileUploaded': function (up, file, info) {
                console.log(info);
                info = JSON.parse(info);
                $("#upload_loading").hide();
                $("#musicUrl").val(info.key);
            },
            'Key': function (up, file) {
                var key = "";
                $.ajax({
                    url: "/music/uuid", async: false, context: document.body, success: function (data) {
                        alert(data.uuid);
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