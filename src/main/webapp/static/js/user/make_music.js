$().ready(function(){
    $("#tj").click(function(){
        var name = $("#name").val().trim();
        var songer = $("#songer").val().trim();
        var musicUrl = $("#musicUrl").val().trim();
        if(name.length == 0 || name.length >100 || songer.length > 100 ||musicUrl.length ==0){
            alert("信息提交不合法！");
            return;
        }
        if(songer.length == 0){
            songer = "未知";
        }
        $("#tj").attr("disabled", "disabled");
        $.post("/user/make/music",{'name':name,'songer':songer,'musicUrl':musicUrl}, function(data){
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
        domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
        max_file_size: '10mb',
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