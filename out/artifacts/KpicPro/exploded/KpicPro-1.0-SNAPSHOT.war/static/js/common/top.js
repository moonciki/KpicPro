$().ready(function(){
    $.post("/user/all/notread/news", function(data){
        if(data != "" && data != null){
            if(data != 0){
                $("#user_news").append("<span class=\"badge\" style=\"background-color: #FF79BC\">"+data+"</span>");
            }
        }
    });
});