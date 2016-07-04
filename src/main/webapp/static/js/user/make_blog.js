var ue = UE.getEditor('myEditor');
ue.focus();

$("#tj").click(function(){
    var title = $("#title").val().trim();
    var content = ue.getContent();
    var shortContent = ue.getContentTxt();
    if(title.length < 5 || title.length > 36 || shortContent.trim().length == 0 || shortContent.trim().length > 10000){
        alert("输入的内容不合法：标题或实质内容（除了图片、表情）可能为空，标题和内容过长或者过少，标题最低5个字符，最长36个，实质内容最多10000个字符");
        return;
    }
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