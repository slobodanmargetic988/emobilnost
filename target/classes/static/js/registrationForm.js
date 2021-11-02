


//form container height
const toggleSignin = function () {
    const container = document.querySelector('.signinBx').classList.toggle('active');
    const container2 = document.querySelector('.signupBx').classList.toggle('active');
    $(".container-registration").css("min-height", "30rem");

    $('html, body').animate({
        scrollTop: 50
    }, 800);
};
const toggleRegister = function () {
    const container = document.querySelector('.signinBx').classList.toggle('active');
    const container2 = document.querySelector('.signupBx').classList.toggle('active');
    $(".container-registration").css("min-height", "92rem");
};


const toggleForgotSignin = function () {
    const container = document.querySelector('.signinBx').classList.toggle('active');
    const container2 = document.querySelector('.forgotBx').classList.toggle('active');

};


//show/hide password
$(document).ready(function () {

    /* password show/hide start */
    $("#openPassword").click(function () {
        $("#openPassword").hide();
        $("#closedPassword").show();
        $("#passwordRegistration").attr("type", "text");
    });
    $("#closedPassword").click(function () {
        $("#closedPassword").hide();
        $("#openPassword").show();
        $("#passwordRegistration").attr("type", "password");
    });




    $(".openPasswordConf").click(function () {
        $(".openPasswordConf").hide();
        $(".closedPasswordConf").show();
        $("#passwordRegistrationConfirmation").attr("type", "text");
    });
    $(".closedPasswordConf").click(function () {
        $(".closedPasswordConf").hide();
        $(".openPasswordConf").show();
        $("#passwordRegistrationConfirmation").attr("type", "password");
    });




    $(".openPasswordLogin").click(function () {
        $(".openPasswordLogin").hide();
        $(".closedPasswordLogin").show();
        $("#passwordLogin").attr("type", "text");
    });
    $(".closedPasswordLogin").click(function () {
        $(".closedPasswordLogin").hide();
        $(".openPasswordLogin").show();
        $("#passwordLogin").attr("type", "password");
    });
    /* password show/hide end */



    $("input").keyup(function () {
        if ($("#passwordRegistration").val() === $("#passwordRegistrationConfirmation").val()) {
            $("#label-2").text("* Lozinke se poklapaju");
            $("#label-2").css('color', '#28a745 !important');
        } else {
            $("#label-2").css("display", "block");
            $("#label-2").text("* Lozinke se ne poklapaju");
        } if ($("#passwordRegistration").val().length === 0 && $("#passwordRegistrationConfirmation").val().length === 0) {
            $("#label-2").css("display", "none");
        }
    });


});