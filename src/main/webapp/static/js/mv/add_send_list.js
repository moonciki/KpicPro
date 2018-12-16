$().ready(function(){
    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'up_cover',
        uptoken_url: '/pbar/upload',
        domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
        max_file_size: '0.025mb',
        multi_selection:false,
        flash_swf_url: 'static/js/plupload/Moxie.swf',
        max_retries: 5,
        chunk_size: '0.025mb',
        auto_start: true,
        filters: {
            mime_types: [
                {title: "Images files", extensions: "jpg,png,gif"},
            ],
            prevent_duplicates: true,
        },
        init: {
            'BeforeUpload': function (up, file) {
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
            },
            'FileUploaded': function (up, file, info) {
                console.log(info);
                info = JSON.parse(info);
                $("#cover").val("http://7xwibn.com1.z0.glb.clouddn.com/"+info.key);
                $("#cover_area").empty();
                $("#cover_area").append("<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/200/h/200/interlace/0/q/95\"/>")
            },
            'Key': function (up, file) {
                var key = "";
                $.ajax({
                    url: "/pbar/uuid", async: false, context: document.body, success: function (data) {
                        if (data.success) {
                            key = data.uuid;
                        } else {

                        }
                    }, error: function () {
                    }, "dataType": "json"
                });

                return key;
            }
        }
    });

    $("#submit").click(function(){
       var title = $("#title").val().trim();
       if(title.length == 0 || title.length >=25){
           alert("标题不符合基本法！");
           return;
       }

        var decri = $("#decri").val().trim();
        if(decri.length == 0 || decri.length >=450){
            alert("描述不符合基本法！");
            return;
        }
        $("#submit").attr("disabled", "disabled");
        var cover = $("#cover").val().trim();

        var params = {};
        params.title = title;
        params.decri = decri;
        params.cover = cover;
        $.post("/send/list_save", params, function(data){
            if(data.code == 200){
                window.location.href="/send/list_my";
            }else{
                alert(data.msg);
            }
        })

    });
});