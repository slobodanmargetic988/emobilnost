 $("#fizickolice").on("change", function () {
     $("#pravnolice").prop('checked', false);
     if($(this).is(':checked') === true){
       $("#pravnolicedetalji").css("display","none");
        $("#faktura").css("display","none");
        
    }else{
         $("#pravnolicedetalji").css("display","block");
          $("#faktura").css("display","block");
    }
 });
 
  $("#pravnolice").on("change", function () {
     $("#fizickolice").prop('checked', false);
    if($(this).is(':checked') === true){
       $("#pravnolicedetalji").css("display","block");
       $("#faktura").css("display","block");
       
        
    }else{
         $("#pravnolicedetalji").css("display","none");
           $("#faktura").css("display","none");
    }
 });