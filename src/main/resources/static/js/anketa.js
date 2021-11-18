$(document).ready(function () {

    // Hide the div
  //  $("#anketa").hide();
    // Show the div in 5s
    $("#anketa").delay(5000).fadeIn(1000);

    $("#nextBtn").click(function () {
        $("#form1").css("display", "none");
        $("#form2").css("display", "block");
    });
    $("#prevBtn").click(function () {
        $("#form1").css("display", "block");
        $("#form2").css("display", "none");
    });
    $("#closeBtn").click(function () {
        $("#anketa").fadeOut();
    });

    $("#prijaviSe").click(function () {
        var email = $("#emailAnketa").val();
        var opcija = $('input[name="anketaRadios"]:checked').val();
        var anketa = new Object();
        anketa.email = email;
        anketa.opcija = opcija;

        var anketaJSON = JSON.stringify(anketa);
        Cookies.set('anketa', anketaJSON);
        console.log(anketaJSON);

        url = "/anketa";
        $.ajax({
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            url: url,
            data: {"myData": anketaJSON},

            cache: false,
            timeout: 600000,
            success: function (response) {
                // alert(response);
                if (response == "ok") {

                    $("#alertHvalaPorudzbina").css("display", "block");


                }
            },
            error: function (e) {
                //  alert(e);
                console.log("ovo je error " + e);
            }
        });

        $("#form3").css("display", "block");
        $("#form2").css("display", "none");
        $("#anketa").delay(7000).fadeOut(1000);
    });
});
