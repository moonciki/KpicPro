$().ready(function(){
    $.post("/user/all/notread/news", function(data){
        $("#user_news").append(data);
    });
});