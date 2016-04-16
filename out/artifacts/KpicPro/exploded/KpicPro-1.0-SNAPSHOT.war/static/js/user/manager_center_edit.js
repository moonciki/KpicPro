$().ready(function(){

    $("#touxiang").click(function(){
        $("#touxiang").attr("disabled", "disabled");
    });

    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'touxiang',
        uptoken_url: '/pbar/upload',
        domain: 'http://7xs5lv.com1.z0.glb.clouddn.com/',
        max_file_size: '0.025mb',
        multi_selection: false,
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
                $("#touxiang").removeAttr("disabled");
            },
            'FileUploaded': function (up, file, info) {
                console.log(info);
                info = JSON.parse(info);
                $("#touxiang_xs").empty();
                $("#avater").empty();
                $("#touxiang_xs").append("<img src=\"http://7xs5lv.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/100/h/100/q/95\"/>")
                $("#avater").append("http://7xs5lv.com1.z0.glb.clouddn.com/"+info.key);
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
        var name = $("#name").val();
        var age = $("#age").val();
        var password = $("#password").val();
        var address = $("#address").val();
        var avater = $("#avater").html();
        var tag = $("#tag").val();
        var id = $("#id").val();
        $.post("/user/management/center/update", {'id' : id, 'name' : name, 'age' : age, 'password' : password, 'address' : address, 'avater' : avater, 'tag' : tag}, function(data){
           alert(data);
            if(data.success){
               $(".loading2").show();
               $.post("/logout2", function(data){});
           }else{
               alert("修改失败！");
               window.location.href = "/user/management/center/edit";
           }
        });

    });
});