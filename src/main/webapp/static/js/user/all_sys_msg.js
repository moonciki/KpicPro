$().ready(function(){

    jz();

    $("#jz").click(function(){
        jz();
    });
});

function jz(){
    $("#jz").attr("disabled", "disabled");
    $("#load").show();
    var page = $("#page").val();
    var num = parseInt($("#num").val());
    $.post("/user/self/sysmsgs", {'page':page}, function(data){
        if(data == null || data == ""){
            $("#load").hide();
            $("#no_data").fadeIn(400);
            setTimeout(function(){$("#no_data").fadeOut(1000);},2000);
        }else{
            var flag = parseInt(page)*10;
            var addhtml = "";
            for(key in data){
                flag ++;
                addhtml+="<div class=\"sx\" id=\"sx_"+data[key].id+"\">"
                if(flag <= num){
                    addhtml+="<div class=\"sx_02\" style='border-top: 5px solid #FF5151;width: 775px'>"
                }else{
                    addhtml+="<div class=\"sx_02\" style='border-top: 5px solid #E0E0E0;width: 775px'>"
                }
                addhtml +=
                "<b>["+getLocalTime(data[key].createTime)+"]&nbsp;&nbsp;<span style=\"glyphicon glyphicon-send\"></span>&nbsp;&nbsp;"+data[key].title+"</b>"+
                "<hr/><div class=\"well\">"+data[key].content+"</div></div></div>"
            }
            $("#sx_list").append(addhtml);
            $("#load").hide();
            $("#page").val(parseInt(page)+1);
            $("#jz").removeAttr("disabled");
        }
    });
}

function getLocalTime(timer) {
    var d = new Date(timer);
    return (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes());
}