$().ready(function(){
    $("#submit").click(function(){
        $(".loading").show();
        var name = $("#name").val();
        var msg = $("#msg").val();
        var logo = $("#logo").val();
        var background_logo = $("#background_logo").val();
        var pbarId = $("#pbarId").val();
        $.post("/subject/manager/upd4615"+pbarId, {'name':name,'msg':msg,'logo':logo,'background_logo':background_logo}, function(data){
            if(data){
                window.location.href = "/subject/manager/sub4615"+pbarId;
            }else{
                alert("修改出错");
                $(".loading").hide();
            }
        })
        $("#form").submit();
    });
});