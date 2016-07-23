$().ready(function(){

    $("#logo_upload").click(function(){
        $("#logo_upload").attr("disabled", "disabled");
    });

    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'logo_upload',
        uptoken_url: '/pbar/upload',
        domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
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
                $("#scz").show();
            },
            'Error': function (up, err, errTip) {
                console.log(err);
                console.log(errTip);
                alert("上传出错 -_-# 可能出现以下错误：\n①网络连接失效\n②上传文件过大\n③后台程序出现未知错误");
                $("#scz").hide();
                $("#logo_upload").removeAttr("disabled");
            },
            'FileUploaded': function (up, file, info) {
                console.log(info);
                info = JSON.parse(info);
                $("#scz").hide();
                $("#logo_show").append("<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/100/h/100/interlace/0/q/95\"/>")
                $("#logo").val("http://7xwibn.com1.z0.glb.clouddn.com/"+info.key);
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


    var flag = 0;
    $("#add_tags").click(function(){
        var tags = $("#tags").val();
        if(tags.trim().length == 0){
            alert("不能为空！");
            return;
        }
        if(tags.trim().length > 5){
            alert("标签名称不能超过5个字符！");
            return;
        }

        if(flag < 4){
            var color = $("input[name='tag_color']:checked").val();
            $("#tags_show").append("<span style='color:"+color+"'><span class='glyphicon glyphicon-tag'></span>"+tags+"</span>&nbsp;&nbsp;&nbsp;&nbsp;");

            var tags_v = $("#tag_value").val()+tags + ":" + color + "&";

            $("#tag_value").val(tags_v);
            if(flag == 0){
                $("#tag_close").show();
            }
        }else{
            alert("数量已达到上限！");
        }
        flag ++;
    });

    $("#tag_close").click(function(){
        flag = 0;
        $("#tag_value").val("");
        $("#tags_show").empty();
        $("#tag_close").hide();
        $("#tags").val("");
    });


    $("#save_pbar").click(function(){

        var name = $("#name").val().trim();
        var msg = $("#msg").val().trim();
        var type = jQuery("#type  option:selected").val();
        var logo = $("#logo").val();
        var color = $("input[name='color']:checked").val();
        var tags = $("#tag_value").val().trim();

        if(name.length == 0 || name.length > 12 || msg.length == 0 || msg.length > 50 || tags.length == 0){
            alert("信息输入不合法，请严格按照文本框内提示的规则进行填写");
            return;
        }

        if(logo.length == 0){
            alert("请上传该圈子logo");
            return;
        }

        $("#save_pbar").attr("disabled", "disabled");
        $("#zt").hide();
        $(".loading3").show();
        $("#loading4").show();

        $.post("/user/pbar/save", {'name' : name, 'msg' : msg, 'type' : type, 'logo' : logo, 'color' : color, 'tags' : tags}, function(data){
            $(".loading3").hide();
            $("#loading4").hide();
            if(data.success){
                $("#pbar_add_success").show();
            }else{
                alert("递交出错");
                $("#zt").show();
                $("#save_pbar").removeAttr("disabled");
            }
        });

    });

});