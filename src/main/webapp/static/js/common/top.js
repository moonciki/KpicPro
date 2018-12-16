$().ready(function(){
    $("#top_s").click(function(){
        var kw = $("#top_kw").val().trim();
        if(kw == ""){
            alert("要输入内容哦~");
            return;
        }
        var type = $('input:radio[name="s_type"]:checked').val();
        if(type == 1){
            window.open("/kabi/search/m_kw_"+kw);
        }else if(type == 2){
            window.open("/kabi/search/q_kw_"+kw);
        }
        else{
            alert("迷之情况==");
        }

    });

    $("#s_music").click(function(){
        $("#top_kw").removeAttr("placeholder");
        $("#top_kw").attr("placeholder", "闲暇时光，你想听点什么呢？");
    });

    $("#s_quan").click(function(){
        $("#top_kw").removeAttr("placeholder");
        $("#top_kw").attr("placeholder", "搜一搜属于你的小圈子~");
    });

    $.post("/user/all/notread/news", function(data){
        if(data != "" && data != null){
            if(data != 0){
                $("#user_news").append("<span class=\"badge\" style=\"background-color: #FF79BC\">"+data+"</span>");
            }
        }
    });
});

function logout(){
    $.post("/logout",function(data){
        if(data){
            location.reload();
        }else{
            alert("退出过程中出现迷之错误，请刷新重试~");
        }
    });
}