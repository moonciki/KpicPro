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
            alert("�ǳ���Ǹ����̨��������ˡ��������Լ���д�Ķ����ǲ��������ﲻ���Ϲ涨�أ�");
        }
    });
});