$().ready(function(){
    $('.cd_dy').hover(function(){
        $(this).attr("style", "color: #FF60AF; text-shadow: #FF95CA 0px 0px 10px;border-bottom: 4px solid #FF60AF;");
    },function(){
        $(this).removeAttr("style");
    });

    $("#msg_center").click(function(){
        window.open("/user/management/center");
    });
})

$(".wx").click(function(){
    $(".loading").show();
});