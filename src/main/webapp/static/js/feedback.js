$("#fs").click(function(){

    var fk_type = $("input[name='fk_type']:checked").val();
    var url = $("#url").val().trim();
    var msg = $("#msg").val().trim();
    var flag = true;
    if(fk_type == 1){
        if(url.length == 0 || url.length > 200){
            flag = false;
        }
    }else{
        url = "未填写";
    }

    if(msg.length < 5 || msg.length > 500){
        flag = false;
    }

    if(!flag){
        alert("所填信息不合法，请严格按照要求填写");
        return;
    }
    $("#fs").attr("disabled", "disabled");
    $.post("/feedback/save", {'url': url, 'msg':msg}, function(data){
        if(data){
            $("#zt").fadeOut(500);
            $("#success").fadeIn(500);
        }else{
            alert("发送过程中出现未知错误，请重试");
            $("#fs").removeAttr("disabled");
        }
    });
});


$("#jy").click(function(){
    $("#jy_jb").hide();
});

$("#jb").click(function(){
    $("#jy_jb").show();
});