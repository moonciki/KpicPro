$().ready(function(){
   $("#type1").click(function(){
       $("#type_c_1").empty();
       $("#type_c_1").append("请在下面的文本框内输入您想要放入首页轮播的内容【站内任意优秀内容】的链接地址（可以自荐哦）");
       $("#type_c_2").empty();
       $("#type_c_2").append("请在下面文本框里写一下自己为什么要对该内容在首页进行轮播");
   });
    $("#type2").click(function(){
        $("#type_c_1").empty();
        $("#type_c_1").append("请在下面的文本框内输入您想要放入首页热推的【圈子首页】链接地址（可以自荐哦）");
        $("#type_c_2").empty();
        $("#type_c_2").append("请在下面文本框里写一下自己推荐此圈子的理由");
    });
    $("#type3").click(function(){
        $("#type_c_1").empty();
        $("#type_c_1").append("请在下面的文本框内输入您想要放入首页展览的【图集】地址（可以自荐哦）");
        $("#type_c_2").empty();
        $("#type_c_2").append("请在下面文本框里写一下自己推荐此图集的理由");
    });
    $("#type4").click(function(){
        $("#type_c_1").empty();
        $("#type_c_1").append("请在下面的文本框内输入您想要放入首页热推内容的【帖子】或者【文章】的链接地址（可以自荐哦）");
        $("#type_c_2").empty();
        $("#type_c_2").append("请在下面文本框里写一下自己为什么要对该内容在首页进行轮播");
    });
    $("#type5").click(function(){
        $("#type_c_1").empty();
        $("#type_c_1").append("请输入该牛人的【个人首页】地址（可以自荐哦）");
        $("#type_c_2").empty();
        $("#type_c_2").append("请在下面文本框里写一下该用户为牛人的理由（请以节操值、级别等基本信息为基础填写）");
    });

    $("#tj").click(function(){

        var url = $("#url").val().trim();
        var msg = $("#msg").val().trim();

        if(url.length < 5 || url.length > 200 || msg.length < 10 || msg.length > 500){
            alert("链接或申请理由输入不合法，请严格按照要求填写~");
            return;
        }
        $("#tj").attr("disabled", "disabled");
        var type = $("input[name='type_op']:checked").val();
        var typeName = type == 1 ? "轮播图" : (type == 2 ? "热门圈子" : (type == 3 ? "优秀图集" : (type == 4 ? "精彩内容" : "牛人榜")));
        $.post("/user/home/save",{'url':url,'msg':msg,'type':type,'typeName':typeName}, function(data){
            if(data){
                $("#html2").hide();
                $("#html").show();
            }else{
                alert("保存出错，请重试！");
                $("#tj").removeAttr("disabled");
            }
        });
    });
});