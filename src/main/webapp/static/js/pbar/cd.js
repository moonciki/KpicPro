$().ready(function(){
   $(".main_01_unit").click(function(){
      $(".loading").show();
   });

    $(".main_01_unit").hover(function(){
        $(this).attr("style", "color: #FF60AF; text-shadow: #FF95CA 0px 0px 10px;border-bottom: 4px solid #FFC1E0;");
    },function(){
        $(this).removeAttr("style");
    });
});