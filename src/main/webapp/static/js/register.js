$().ready(function(){
    huoqu_pic();
});

$.ajax({
    url: "start_captcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
    type: "get",
    dataType: "json",
    success: function (data) {
        initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            product: "embed",
            offline: !data.success
        }, handlerEmbed);
    }
});
var handlerEmbed = function (captchaObj) {
    $("#register").click(function(){
        $("#register").attr("disabled", "disabled");
        var num = $("#num").val().trim();
        var pwd = $("#pwd").val();
        var pwd2 = $("#pwd2").val();
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
                $("#register").removeAttr("disabled");
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

        var userPic = $("#userPic_c").val();

        if(flag){
            var validate = captchaObj.getValidate();
            if (!validate) {
                $("#notice")[0].className = "show";
                setTimeout(function () {
                    $("#notice")[0].className = "hide";
                }, 2000);
                $("#register").removeAttr("disabled");
                e.preventDefault();
                return;
            }

            var challenge = $(".geetest_challenge").val();
            var validate = $(".geetest_validate").val();
            var seccode = $(".geetest_seccode").val();

            alert("challenge = " + challenge + "    validate = " + validate + "    seccode = " + seccode);

            var data = {};
            data.challenge = challenge;
            data.validate = validate;
            data.seccode = seccode;
            data.num = num;
            data.password = pwd;
            data.name = name;
            data.birth = birth;
            data.tag = tag;
            data.address = address;
            data.userPic = userPic;

            $.ajax({
                url: "rg/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (data) {
                    if(data == 1){
                        location.reload();
                    }else if(data == 2){
                        alert("账号已存在！");
                        $("#register").removeAttr("disabled");
                    }else if(data == 5){
                        alert("昵称已存在！");
                        $("#register").removeAttr("disabled");
                    }else if(data == 3){
                        alert("验证失败！");
                        $("#register").removeAttr("disabled");
                    }else{
                        alert("发生迷之错误，请刷新页面重试~");
                    }
                }
            });
        }else{
            $("#register").removeAttr("disabled");
        }
    });
    captchaObj.appendTo("#embed-captcha");
    captchaObj.onReady(function () {
        $("#wait")[0].className = "hide";
    });
}


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