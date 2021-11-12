
$(window).scroll(function () {
    $('.changenavlinkcolor').toggleClass('changecolor', $(this).scrollTop() > 50);
});

$(window).scroll(function () {
    $('.maliekran.meni').toggleClass('changehambcolor', $(this).scrollTop() > 50);
});



$(window).scroll(function () {
    $('.fancy-link-18 a').toggleClass('changecolorfancylink', $(this).scrollTop() > 50);
});

$(window).scroll(function () {
    $('.uclani-se-btn').toggleClass('changeclr', $(this).scrollTop() > 50);
});

$(window).scroll(function () {
    $('.navbar-toggler-icon.navbar-toggler-icon-clr').toggleClass('changehambcolour', $(this).scrollTop() > 50);
});



//$(function () {
//    //meni da promeni boju kad se skroluje
//    $(document).scroll(function () {
//        $('.changenavlinkcolor').toggleClass('changecolor', $(this).scrollTop() > 50);
//
//    });
//});
