$().ready(function(){
    $("#submit").click(function(){
        $(".loading").show();
        var name = $("#name").val();
        var msg = $("#msg").val();
        var logo = $("#logo").val();
        var background_logo = $("#background_logo").val();
        var pbarId = $("#pbarId").val();
        $.post("/subject/manager/upd4615"+pbarId, {'name':name,'msg':msg,'logo':logo,'background_logo':background_logo}, function(data){
            if(data){
                window.location.href = "/subject/manager/sub4615"+pbarId;
            }else{
                alert("修改出错");
                $(".loading").hide();
            }
        })
        $("#form").submit();
    });


    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'sclogo',
        uptoken_url: '/pbar/upload',
        domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
        max_file_size: '0.025mb',
        multi_selection:false,
        flash_swf_url: 'static/js/plupload/Moxie.swf',
        max_retries: 5,
        chunk_size: '1mb',
        auto_start: true,
        filters: {
            mime_types: [
                {title: "Images files", extensions: "jpg,png,gif"},
            ],
            prevent_duplicates: true,
        },
        init: {
            'BeforeUpload': function (up, file) {
                $("#sclogo").attr("disabled", "disabled");
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
                $("#sclogo").removeAttr("disabled");
                console.log(err);
                console.log(errTip);
                alert("上传出错 -_-# 可能出现以下错误：\n①网络连接失效\n②上传文件过大\n③后台程序出现未知错误");
            },
            'FileUploaded': function (up, file, info) {
                console.log(info);
                info = JSON.parse(info);
                $("#logo").val("http://7xwibn.com1.z0.glb.clouddn.com/"+info.key);
                $("#logo_img").empty();
                $("#logo_img").append("<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/89/h/89/interlace/0/q/95\"/>")
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


    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'scblogo',
        uptoken_url: '/pbar/upload',
        domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
        max_file_size: '0.3mb',
        multi_selection:false,
        flash_swf_url: 'static/js/plupload/Moxie.swf',
        max_retries: 5,
        chunk_size: '1mb',
        auto_start: true,
        filters: {
            mime_types: [
                {title: "Images files", extensions: "jpg,png,gif"},
            ],
            prevent_duplicates: true,
        },
        init: {
            'BeforeUpload': function (up, file) {
                $("#scblogo").attr("disabled", "disabled");
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
                $("#scblogo").removeAttr("disabled");
                console.log(err);
                console.log(errTip);
                alert("上传出错 -_-# 可能出现以下错误：\n①网络连接失效\n②上传文件过大\n③后台程序出现未知错误");
            },
            'FileUploaded': function (up, file, info) {
                console.log(info);
                info = JSON.parse(info);
                $("#background_logo").val("http://7xwibn.com1.z0.glb.clouddn.com/"+info.key);
                $("#logo_bimg").empty();
                $("#logo_bimg").append("<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/670/h/144/q/95\"/>")
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
});