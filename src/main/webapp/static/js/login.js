$().ready(function(){
    huoqu_code();
});

$("#login").click(function(){
    $("#login").attr("disabled", "disabled");
    var num = $("#num").val().trim();
    var pwd = $("#pwd").val();
    var code = $("#code").val();
    var code_c = $("#code_c").val();
    var flag = true;
    if(num == null || num == "" || isNaN(num) || num.length < 6 || num.length > 20){
        $("#num_load").fadeIn(800);
        setTimeout(function(){$("#num_load").fadeOut(800);},2000);
        flag = false;
    }

    if(pwd == null || pwd == "" || pwd.length < 6 || pwd.length > 20){
        $("#pwd_load").fadeIn(800);
        setTimeout(function(){$("#pwd_load").fadeOut(800);},2000);
        flag = false;
    }

    if(code == null || code == "" || code != code_c){
        $("#code_load").fadeIn(800);
        setTimeout(function(){$("#code_load").fadeOut(800);},2000);
        flag = false;
    }

    if(flag){
        $.post("/jugelogin", {'num':num,'pwd':pwd}, function(data){
            if(data.success){
                $.post("/realogin", {'num':num,'pwd':pwd}, function(data){
                    if(data){
                        location.reload();
                    }else{
                        $("#err").fadeIn(800);
                        setTimeout(function(){$("#err").fadeOut(800);},2000);
                        $("#login").removeAttr("disabled");
                    }
                })
            }else{
                $("#login").removeAttr("disabled");
                $("#err").fadeIn(800);
                setTimeout(function(){$("#err").fadeOut(800);},2000);
                flag = false;
            }
        });
    }else{
        $("#login").removeAttr("disabled");
    }
});

function huoqu_code(){
    $.post("/login/code",function(data){
        if(data.success){
            $("#code_c").val(data.num);
        }else{
            alert("获取验证码的时候发生迷之错误，请刷新重试~");
        }
    })
}