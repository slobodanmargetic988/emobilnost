


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

 $("#oPassword").click(function () {
        $("#oPassword").hide();
        $("#cPassword").show();
        $("#pRegistration").attr("type", "text");
    });
    $("#cPassword").click(function () {
        $("#cPassword").hide();
        $("#oPassword").show();
        $("#pRegistration").attr("type", "password");
    });

    $("#openEyed").click(function () {
        $("#openEyed").hide();
        $("#closedEyed").show();
        $("#oldPassword").attr("type", "text");
    });
    $("#closedEyed").click(function () {
        $("#closedEyed").hide();
        $("#openEyed").show();
        $("#oldPassword").attr("type", "password");
    });


    $("#openEye").click(function () {
        $("#openEye").hide();
        $("#closedEye").show();
        $("#newPassword").attr("type", "text");
    });
    $("#closedEye").click(function () {
        $("#closedEye").hide();
        $("#openEye").show();
        $("#newPassword").attr("type", "password");
    });

    $("#eyeOpen").click(function () {
        $("#eyeOpen").hide();
        $("#eyeClose").show();
        $("#repeatNewPassword").attr("type", "text");
    });
    $("#eyeClose").click(function () {
        $("#eyeClose").hide();
        $("#eyeOpen").show();
        $("#repeatNewPassword").attr("type", "password");
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

 $(".oPasswordConf").click(function () {
        $(".oPasswordConf").hide();
        $(".cPasswordConf").show();
        $("#passwordRegistrationConf").attr("type", "text");
    });
    $(".cPasswordConf").click(function () {
        $(".cPasswordConf").hide();
        $(".oPasswordConf").show();
        $("#passwordRegistrationConf").attr("type", "password");
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
    
    
    
    
    
    
    
    
    
       $("#openOko").click(function () {
        $("#openOko").hide();
        $("#closeOko").show();
        $("#passwordReset").attr("type", "text");
    });
    $("#closeOko").click(function () {
        $("#closeOko").hide();
        $("#openOko").show();
        $("#passwordReset").attr("type", "password");
    });

    $("#openedOko").click(function () {
        $("#openedOko").hide();
        $("#closedOko").show();
        $("#passwordResetRepeat").attr("type", "text");
    });
    $("#closedOko").click(function () {
        $("#closedOko").hide();
        $("#openedOko").show();
        $("#passwordResetRepeat").attr("type", "password");
    });

    /* password show/hide end */



    $("input").keyup(function () {
        if ($("#passwordRegistration").val() === $("#passwordRegistrationConfirmation").val()) {
            $("#label-2").text("* Lozinke se poklapaju").css('color', '#28a745');
        } else {
            $("#label-2").css("display", "block");
            $("#label-2").text("* Lozinke se ne poklapaju").css('color', '#dc3545');;
        }
        if ($("#passwordRegistration").val().length === 0 && $("#passwordRegistrationConfirmation").val().length === 0) {
            $("#label-2").css("display", "none");
        }
    });
    
    
    
    
    $("input").keyup(function () {
        if ($("#pRegistration").val() === $("#passwordRegistrationConf").val()) {
            $("#label-3").text("* Lozinke se poklapaju").css('color', '#28a745');
//            $("#label-3").css('color', '#28a745 !important');
        } else {
            $("#label-3").css("display", "block");
            $("#label-3").text("* Lozinke se ne poklapaju").css('color', '#dc3545');;
        }
        if ($("#pRegistration").val().length === 0 && $("#passwordRegistrationConf").val().length === 0) {
            $("#label-3").css("display", "none");
        }
    });


});