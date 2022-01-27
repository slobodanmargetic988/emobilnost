$(document).ready(function () {
     $(".answer-div").hide();
    $(".show-hide").on('click', function () { 
        var id= $(this).attr("komentarid");
    $(".answer-div[komentarid="+id+"]").toggle();
    
    });
  });  

 