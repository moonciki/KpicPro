$().ready(function(){
    huoqu_pic();
});

$("#register").click(function(){
    $("#register").attr("disabled", "disabled");
    var num = $("#num").val().trim();
    var pwd = $("#pwd").val();
    var pwd2 = $("#pwd2").val();
    var code = $("#code").val();
    var name = $("#name").val();
    var birth = $("#birth").val();
    var tag = $("#tag").val();
    var address = $("#address").val();
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

    if(pwd2 == null || pwd2 == "" || pwd2.length < 6 || pwd2.length > 20){
        $("#pwd_load2").fadeIn(800);
        setTimeout(function(){$("#pwd_load2").fadeOut(800);},2000);
        flag = false;
    }

    if(pwd != null && pwd != "" && pwd2 != pwd){
        $("#pwd_load3").fadeIn(800);
        setTimeout(function(){$("#pwd_load3").fadeOut(800);},2000);
        flag = false;
    }

    if(name.trim().length > 6 || name.trim().length <= 0){
        $("#name_load").fadeIn(800);
        setTimeout(function(){$("#name_load").fadeOut(800);},2000);
        flag = false;
    }

    if(birth != null && birth != ""){
        if(birth.trim().length > 10 || birth.trim().length <= 0){
            $("#birth_load").fadeIn(800);
            setTimeout(function(){$("#birth_load").fadeOut(800);},2000);
            flag = false;
        }
    }else{
        birth = "未填写";
    }

    if(tag != null && tag != ""){
        if(tag.trim().length > 20 || tag.trim().length < 5){
            $("#tag_load").fadeIn(800);
            setTimeout(function(){$("#tag_load").fadeOut(800);},2000);
            flag = false;
        }
    }else{
        tag = "这家伙很懒，什么也没留下";
    }

    if(address != null && address != ""){
        if(address.trim().length > 20 || address.trim().length <= 0){
            $("#address_load").fadeIn(800);
            setTimeout(function(){$("#address_load").fadeOut(800);},2000);
            flag = false;
        }
    }else{
        address = "地球";
    }

    if(code == null || code == ""){
        $("#code_load").fadeIn(800);
        setTimeout(function(){$("#code_load").fadeOut(800);},2000);
        flag = false;
    }

    var userPic = $("#userPic_c").val();

    if(flag){

               $.post("/rg/check_num",{'num':num}, function(data){
                   if(data){
                       $.post("/rg/check_name",{'name':name},function(data){
                           if(data){
                               $("#loading").show();
                               var sex = $("input[name='sex']:checked").val();
                               $.post("/rg/save", {'num':num,'password':pwd,'name':name,'sex':sex,'age':0,'birth':birth,'address':address,'userPic':userPic,'tag':tag,'code':code},function(data){
                                   if(data){
                                       location.reload();
                                   }else{
                                       $("#loading").hide();
                                       alert("注册出错，请检查输入信息是否合格，验证码填写是否错误");
                                       $("#register").removeAttr("disabled");
                                   }
                               });
                           } else{
                               $("#name_load2").fadeIn(800);
                               setTimeout(function(){$("#name_load2").fadeOut(800);},2000);
                               $("#register").removeAttr("disabled");
                           }
                       });
                   }else{
                       $("#num_load2").fadeIn(800);
                       setTimeout(function(){$("#num_load2").fadeOut(800);},2000);
                       $("#register").removeAttr("disabled");
                   }
               });


    }else{
        $("#register").removeAttr("disabled");
    }

});


function huoqu_pic(){
    $.post("/register/pic",function(data){
        if(data.success){
            $("#userPic").empty();
            $("#userPic").append("<img src = \"http://identicon.relucks.org/"+data.pic+".png?size=100\"/>");
            $("#userPic_c").val("http://identicon.relucks.org/"+data.pic+".png");
        }else{
            alert("获取验证码的时候发生迷之错误，请刷新重试~");
        }
    })
}