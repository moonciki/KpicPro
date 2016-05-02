var ue = UE.getEditor('myEditor');
ue.focus();

$("#tj").click(function(){
    var title = $("#title").val().trim();
    var content = ue.getContent();
    var shortContent = ue.getContentTxt();
    if(shortContent.length > 210){
        shortContent = shortContent.substring(0, 210)+"...";
    }

    $("#tj").attr("disabled", "disabled");
    $.post("/user/save/blog",{'title':title,'content':content,'shortContent':shortContent}, function(data){
        if(data){
            $("#html2").hide();
            $("#html").show();
        }else{
            alert("非常抱歉，后台程序出错了。。看看自己填写的东西是不是有哪里不符合规定呢？");
        }
    });
});