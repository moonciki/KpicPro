$().ready(function(){
    $("#n_z").click(function(){
        var num = parseInt($("#year").val());
        $("#year").val(num+1);

    });

    $("#n_j").click(function(){
        var num = parseInt($("#year").val());
        $("#year").val(num-1);
    });

    $("#y_z").click(function(){
        var num = parseInt($("#month").val());
        var num2 = num+1;
        if(num2 > 12){
            num2 = 1;
        }
        $("#month").val(num2);

    });

    $("#y_j").click(function(){
        var num = parseInt($("#month").val());
        var num2 = num-1;
        if(num2 < 1){
            num2 = 12
        }
        $("#month").val(num2);
    });

    $("#js").click(function(){
        $("#js").attr("disabled", "disabled");
       var year = $("#year").val();
        var month = $("#month").val();
        var pbarId = $("#pbarId").val();
        window.location.href="/subject/manager/tj4615"+pbarId+"_"+year+"_"+month;
    });
});