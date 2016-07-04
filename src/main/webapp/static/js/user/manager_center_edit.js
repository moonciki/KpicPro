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
        var birth = $("#birth").val();
        var password = $("#password").val();
        var address = $("#address").val();
        var avater = $("#avater").html();
        var tag = $("#tag").val();
        var id = $("#id").val();
        var flag = true;
        if(password == null || password == "" || password.length < 6 || password.length > 20){
            $("#yz_pwd").show();
            flag = false;
        }

        if(name.trim().length > 6 || name.trim().length <= 0){
            $("#yz_name_1").show();
            flag = false;
        }

        if(birth != null && birth != ""){
            if(birth.trim().length > 10 || birth.trim().length <= 0){
                $("#yz_birth").show();
                flag = false;
            }
        }else{
            birth = "未填写";
        }

        if(tag != null && tag != ""){
            if(tag.trim().length > 20 || tag.trim().length < 5){
                $("#yz_tag").show();
                flag = false;
            }
        }else{
            tag = "这家伙很懒，什么也没留下";
        }

        if(address != null && address != ""){
            if(address.trim().length > 20 || address.trim().length <= 0){
                $("#yz_address").show();
                flag = false;
            }
        }else{
            address = "地球";
        }

        if(flag){
            $("#submit").attr("disabled", "disabled");
            var old_name = $("#userName").val();
            if(name.trim() == old_name){
                $.post("/user/management/center/update", {'id' : id, 'name' : name, 'birth' : birth, 'password' : password, 'address' : address, 'avater' : avater, 'tag' : tag}, function(data){
                    if(data.success){
                        $(".loading2").show();
                    }else{
                        alert("修改失败！");
                        window.location.href = "/user/management/center/edit";
                    }
                });
            }else{
                $.post("/rg/check_name",{'name':name},function(data){
                    if(data){
                        $.post("/user/management/center/update", {'id' : id, 'name' : name, 'birth' : birth, 'password' : password, 'address' : address, 'avater' : avater, 'tag' : tag}, function(data){
                            alert(data);
                            if(data.success){
                                $(".loading2").show();
                            }else{
                                alert("修改失败！");
                                window.location.href = "/user/management/center/edit";
                            }
                        });
                    } else{
                        $("#yz_name_2").show();
                        $("#submit").removeAttr("disabled");
                    }
                });
            }


        }else{
            $("#submit").removeAttr("disabled");
        }

    });
    $("#submit3").click(function(){
        $(".loading2").hide();
    });
    $("#submit2").click(function(){
        $.post("/logout2", function(data){
            if(data.success){
                window.location.reload();
            }else{}
        });

    });
});