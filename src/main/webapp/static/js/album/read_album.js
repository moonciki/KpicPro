$().ready(function(){
    var albumId = $("#albumId").val();
    $.post("/album/all/pics", {'albumId' : albumId}, function(data){
        if(data == null || data == ""){
            return;
        }else{
            var num = 0;
            for(key in data){
                num++;

            }
        }
    });
});