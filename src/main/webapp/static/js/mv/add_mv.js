$().ready(function(){
    Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'up_cover',
        uptoken_url: '/pbar/upload',
        domain: 'http://7xwibn.com1.z0.glb.clouddn.com/',
        max_file_size: '10mb',
        multi_selection:false,
        flash_swf_url: 'static/js/plupload/Moxie.swf',
        max_retries: 5,
        chunk_size: '10mb',
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
                $("#cover_area").append("<img src=\"http://7xwibn.com1.z0.glb.clouddn.com/"+info.key+"?imageView2/1/w/550/interlace/0/q/95\"/>")
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

        var path = $("#sourcePath").val().trim();
        if(path.length == 0 || path.length >=150){
            alert("链接不符合基本法！");
            return;
        }
        if(path.indexOf("http://")!=0 && path.indexOf("https://")!=0){
            alert("链接不合法！");
            return;
        }
        var point_sp = path.split(".");
        if(point_sp[1] == null){
            alert("链接不合法！");
            return;
        }
        if(point_sp[1]=="baidu"){
            if(point_sp[0] == "http://y"){
                var xg_sp = path.split("/");
                if(xg_sp.length == 0){
                    alert("链接不合法！");
                    return;
                }
                var result = xg_sp[xg_sp.length-1].split("?");
                $("#sourceType").val(3);
                $("#path").val("http://link.hhtjim.com/baidu/yyr/"+result[0]+".mp3");
            }else if(point_sp[0] == "http://music"){
                var xg_sp2 = path.split("/");
                if(xg_sp2.length == 0){
                    alert("链接不合法！");
                    return;
                }
                var result2 = xg_sp2[xg_sp2.length-1].split("?");
                $("#sourceType").val(3);
                $("#path").val("http://link.hhtjim.com/baidu/"+result2[0]+".mp3");
            }else{
                alert("链接不合法！");
                return;
            }
        }else if(point_sp[1]=="163"){
            var dh_sp = path.split("=");
            if(dh_sp[1] == null){
                alert("链接不合法！");
                return;
            }
            $("#sourceType").val(2);
            $("#path").val("http://link.hhtjim.com/163/"+dh_sp[1]+".mp3");
        }else if(point_sp[1]=="qq"){
            var xgqq_sp = path.split("/");
            var teal = xgqq_sp[xgqq_sp.length-1];
            if(teal == null){
                alert("链接不合法！");
                return;
            }
            var pointqq_sp = teal.split(".");
            $("#sourceType").val(1);
            $("#path").val("http://link.hhtjim.com/qq/"+pointqq_sp[0]+".mp3");
        }else if(point_sp[1]=="xiami"){
            var xgxm_sp = path.split("/");
            if(xgxm_sp.length == 0){
                alert("链接不合法！");
                return;
            }
            var result = xgxm_sp[xgxm_sp.length-1].split("?");
            $("#sourceType").val(4);
            $("#path").val("http://link.hhtjim.com/xiami/"+result[0]+".mp3");
        }else{
            alert("链接不合法！");
        }
        var path2 = $("#path").val();
        var params = {};
        params.path = path2;
        params.sourceType = $("#sourceType").val();
        params.sourcePath = path;
        params.songer = $("#songer").val();
        params.title = title;
        params.decri = decri;
        params.slId = $("#slId").val();
        params.cover = $("#cover").val();
        $.post("/send/mv_save", params, function(data){
            if(data.code == 200){
                window.location.href="/send/list"+params.slId+"";
            }else{
                alert(data.msg);
            }
        });


    });
});