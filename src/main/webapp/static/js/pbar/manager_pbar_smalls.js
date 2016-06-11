function cxzw(id, userId){
    if(confirm("一旦撤销，该用户将再也没有机会成为该话题的管理团队，确定要撤销吗？"))
    {
        var pbarId = $("#pbarId").val();
        var pbName = $("#pbarName").val();
        $("#bt_"+id).attr("disabled", "disabled");
        $.post("/pbar/manager/small/del", {'id':id, 'userId':userId, 'pbarId':pbarId, 'pbName':pbName}, function(data){
            if(data){
                alert("操作成功！");
                window.location.reload();
            }else{
                alert("操作失败，请重试~");
                $("#bt_"+id).removeAttr("disabled");
            }
        });
    }
}