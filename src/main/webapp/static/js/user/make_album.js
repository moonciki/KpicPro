$().ready(function(){
    $("#jxtj").click(function(){
        var num1 = $("#already_numt").val();
        var num2 = $("#sy_numt").val();


        if(num2 == 1){
            $("#jxtj").attr("disabled","true");
        }

        var picUrl_num_content = $("#picUrl_"+num1+"_content").val();

        var picContent_num = $("#picContent_"+num1).val();

        if(picContent_num.trim().length == 0){
            alert("请您输入图片描述");
            return;
        }
        if(picUrl_num_content.trim().length == 0){
            alert("请您上传图片");
            return;
        }
        $("#picContent_"+num1).attr("disabled", "disabled");

        var new_num1 = parseInt(num1)+1;
        var new_num2 = parseInt(num2)-1;
        $("#already_numt").val(new_num1);
        $("#sy_numt").val(new_num2);
        $("#sy_num").empty();
        $("#sy_num").append(new_num2);
        $("#tp"+new_num1).show();



    });


    $("#done").click(function(){

        var picUrl_num_content = $("#picUrl_1_content").val();

        var picContent_num = $("#picContent_1").val();

        if(picContent_num.length == 0 || picUrl_num_content.length == 0){
            alert("请将必填信息填写完整");
            return;
        }
        var num1 = $("#already_numt").val();

        var jsons = "[";

        for(i=1;i<=num1;i++){
            jsons+="{'msg' : '"+$("#picContent_"+i).val()+"', 'imgUrl' : '"+$("#picUrl_"+i+"_content").val()+"'}";
            if(i<num1){
                jsons+=",";
            }
        }

        jsons +="]";

        var name = $("#name").val().trim();
        var album_msg = $("#msg").val().trim();
        if(name.length < 5 || name.length > 36 || album_msg.length < 5 || album_msg.length > 200){
            alert("输入图集信息不合法：图集标题或者描述没有按照要求填写");
            return;
        }
        $(".loading").show();

        $("#done").attr("disabled", "disabled");

        var imageUrl = $("#imageUrl").val();

        if(imageUrl.trim().length == 0){
            imageUrl = $("#picUrl_1_content").val()
        }

        $.post("/user/make/album",{'title':name,'msg':album_msg,'imageUrl':imageUrl,'pics':jsons}, function(data){
           if(data){
               $(".loading").hide();
               $("#html").hide();
               $("#html2").show();
           }else{
               alert("发生未知错误！请重试");
               $(".loading").hide();
               $("#html").show();
               $("#done").removeAttr("disabled");
           }
        });

    });



    /** 上传*/
    $().ready(function() {

        Qiniu.uploader({
            runtimes: 'html5,flash,html4',
            browse_button:
                ['picBtn1','picBtn2','picBtn3','picBtn4','picBtn5',
                    'picBtn6','picBtn7','picBtn8','picBtn9','picBtn10',
                    'picBtn11','picBtn12','picBtn13','picBtn14','picBtn15',
                    'picBtn16','picBtn17','picBtn18','picBtn19','picBtn20','picBtn21',
                    'picBtn22','picBtn23','picBtn24','picBtn25','picBtn26','picBtn27',
                    'picBtn28','picBtn29','picBtn30'],
            uptoken_url: '/pbar/upload',
            domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
            max_file_size: '1mb',
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
                    var num = $("#already_numt").val();
                    $("#picBtn_"+num).empty();
                    var img_url = "<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/775/interlace/0/q/75\"/>";
                    $("#upload_loading").hide();
                    $("#picUrl_"+num).append(img_url)
                    $("#picUrl_"+num+"_content").val("http://7xwibn.com1.z0.glb.clouddn.com/"+info.key)
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


    /** 上传*/
    $().ready(function() {

        Qiniu.uploader({
            runtimes: 'html5,flash,html4',
            browse_button:'fm_upload',
            uptoken_url: '/pbar/upload',
            domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
            max_file_size: '1mb',
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
                    $("#fm_upload").attr("disabled","disabled");
                    $("#upload_loading1").show();
                    var filename = file.name;
                    var extStart = filename.lastIndexOf(".");
                    var ext = filename.substring(extStart, filename.length).toUpperCase();
                    console.log(up);
                    if (ext != ".JPG" && ext != ".PNG" && ext != ".GIF") {
                        alert("上传文件只支持jpg、png、gif格式的图片！");
                        up.destroy();
                        $("#fm_upload").removeAttr("disabled");
                    } else {}
                },
                'UploadProgress': function (up, file) {
                },
                'Error': function (up, err, errTip) {
                    console.log(err);
                    console.log(errTip);
                    alert("上传出错 -_-# 可能出现以下错误：\n①网络连接失效\n②上传文件过大\n③后台程序出现未知错误");
                    $("#fm_upload").removeAttr("disabled");
                },
                'FileUploaded': function (up, file, info) {
                    console.log(info);
                    info = JSON.parse(info);
                    var img_url = "<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/775/interlace/0/q/75\"/>";
                    $("#upload_loading1").hide();
                    $("#fm_url").append(img_url)
                    $("#imageUrl").val("http://7xwibn.com1.z0.glb.clouddn.com/"+info.key)
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
});

