$().ready(function(){
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
    $("#login").click(function (e) {
        $("#login").attr("disabled", "disabled");
        var num = $("#num").val().trim();
        var pwd = $("#pwd").val();
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

        if(flag){
            var validate = captchaObj.getValidate();
            if (!validate) {
                $("#notice")[0].className = "show";
                setTimeout(function () {
                    $("#notice")[0].className = "hide";
                }, 2000);
                e.preventDefault();
                $("#login").removeAttr("disabled");
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
            data.pwd = pwd;

            $.ajax({
                url: "verify_login",
                type: "post",
                dataType: "json",
                data: data,
                success: function (data) {
                    if(data == 1){
                        location.reload();
                    }else if(data == 2){
                        alert("用户不存在或密码错误！");
                        $("#login").removeAttr("disabled");
                    }else if(data == 3){
                        alert("验证失败！");
                        $("#login").removeAttr("disabled");
                    }else{
                        alert("发生迷之错误，请刷新页面重试~")
                    }
                }
            });
        }
    });
    captchaObj.appendTo("#embed-captcha");
    captchaObj.onReady(function () {
        $("#wait")[0].className = "hide";
    });
};
