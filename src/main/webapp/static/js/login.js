$().ready(function(){
});

$("#login").click(function(){
    $("#login").attr("disabled", "disabled");
    var num = $("#num").val().trim();
    var pwd = $("#pwd").val();
    var code = $("#code").val();
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

    if(code == null || code == ""){
        $("#code_load").fadeIn(800);
        setTimeout(function(){$("#code_load").fadeOut(800);},2000);
        flag = false;
    }

    if(flag){
        $.post("/jugelogin", {'num':num,'pwd':pwd}, function(data){
            if(data.success){
                $.post("/realogin", {'num':num,'pwd':pwd, 'code':code}, function(data){
                    if(data==1){
                        location.reload();
                    }else if(data == 2){
                        $("#err").fadeIn(800);
                        setTimeout(function(){$("#err").fadeOut(800);},2000);
                        $("#login").removeAttr("disabled");
                    }else{
                        $("#c_err").fadeIn(800);
                        setTimeout(function(){$("#c_err").fadeOut(800);},2000);
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
