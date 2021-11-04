

$(document).ready(function () {
//    $("#submit").prop('disabled', true);
//    $("input").keyup(function () {
//        if ($("#pass").val() === $("#repeatPass").val()) {
//            $("#submit").prop('disabled', false);
//            $("#label").text("* Lozinke se poklapaju");
//        } else {
//            $("#label").css("display", "block");
//            $("#label").text("* Lozinke se ne poklapaju");
//        } if ($("#pass").val().length === 0 && $("#repeatPass").val().length === 0) {
//            $("#label").css("display", "none");
//        }
//    });
//
//
//
//    /* password show/hide */
//    $("#showPassword").click(function () {
//        $("#showPassword").hide();
//        $("#hidePassword").show();
//        $("#pass").attr("type", "text");
//    });
//    $("#hidePassword").click(function () {
//        $("#hidePassword").hide();
//        $("#showPassword").show();
//        $("#pass").attr("type", "password");
//    });
//
//
//
//    $("#showPasswordRepeat").click(function () {
//        $("#showPasswordRepeat").hide();
//        $("#hidePasswordRepeat").show();
//        $("#repeatPass").attr("type", "text");
//    });
//    $("#hidePasswordRepeat").click(function () {
//        $("#hidePasswordRepeat").hide();
//        $("#showPasswordRepeat").show();
//        $("#repeatPass").attr("type", "password");
//    });
   
    $("input").keyup(function () {
        if ($("#passwordReset").val() === $("#passwordResetRepeat").val()) {
            $("#label-3").text("* Lozinke se poklapaju");
            $("#label-3").css('color', '#28a745 !important');
        } else {
            $("#label-3").css("display", "block");
            $("#label-3").text("* Lozinke se ne poklapaju");
        }
        if ($("#passwordReset").val().length === 0 && $("#passwordResetRepeat").val().length === 0) {
            $("#label-3").css("display", "none");
        }
    });

});
