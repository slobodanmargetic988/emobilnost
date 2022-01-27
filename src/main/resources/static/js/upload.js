//
//$(".img-fluid").on("click", function (e) {
//   // timeout(3000);
//  
//    setTimeout(function(){
//$(".lb-closeContainer").append("<a href='/'>obrisi</a>");
//}, 3000);
//
//});
//

$("#uploadslike").on("click", function (e) {

    var formData = new FormData();
    var fileupload = $('#fileupload');
    formData.append("file", fileupload.prop('files')[0]);
    formData.append("title", $('#naslov-slike').val());
    formData.append("alt_text", $('#alt-tekst').val());
    formData.append("galerija", $('#galerijaslika').val());

    $.ajax({
        type: "post",
        cache: false,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        method: 'POST',
        type: 'POST',
        url: '/post/novaSlika/save',
        data: formData,
        success: function (resp) {
            var preview = $.parseHTML(resp);
            $(preview).css("max-width", "100px");
            $(preview).css("max-height", "100px");
            var nasdiv = $("#listaslika"); //.text($("#listaslika").text()+resp);
            nasdiv.append(preview);
            nasdiv.append("<br>");
            nasdiv.append("<div style=\"font-weight: 500;\">Kopirajte link ispod i nalepite ga u tekst vesti</div>");
            $(document.createTextNode(resp)).appendTo(nasdiv);
            nasdiv.append("<br>");
            $("#form").modal('toggle');
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    });


});

$("#uploadvideo").on("click", function (e) {

    var formData = new FormData();
    var fileupload = $('#videoupload');
    formData.append("file", fileupload.prop('files')[0]);
    formData.append("title", $('#naslov-videa').val());

    formData.append("galerija", $('#galerijavideo').val());

    $.ajax({
        type: "post",
        cache: false,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        method: 'POST',
        type: 'POST',
        url: '/post/novVideo/save',
        data: formData,
        success: function (resp) {
            var preview = $.parseHTML(resp);
            $(preview).css("max-width", "150px");
            $(preview).css("max-height", "100px");
            $(preview).children('video').width(150);
            $(preview).children('video').height(100);
            var nasdiv = $("#listavidea"); //.text($("#listaslika").text()+resp);
            nasdiv.append(preview);
//             nasdiv.append("<br>");
            nasdiv.append("<div style=\"font-weight: 500;\">Kopirajte link ispod i nalepite ga u tekst vesti</div>");
            $(document.createTextNode(resp)).appendTo(nasdiv);
            nasdiv.append("<br>");
            $("#form2").modal('toggle');

        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    });


});


//upload slike za galeriju
$("#uploadslikegalerija").on("click", function (e) {

    var formData = new FormData();
    var fileupload = $('#slikauploadgalerija');
    formData.append("file", fileupload.prop('files')[0]);
    formData.append("title", $('#naslovslikegalerija').val());
    formData.append("alt_text", $('#alttekstgalerija').val());
    // formData.append("galerija", $('#galerijaslika').val());

    $.ajax({
        type: "post",
        cache: false,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        method: 'POST',
        type: 'POST',
        url: '/post/novaSlikaGalerija/save',
        data: formData,
        success: function (resp) {
//            var preview = $.parseHTML(resp);
//            $(preview).css("max-width", "100px");
//            $(preview).css("max-height", "100px");
//            var nasdiv = $("#listaslika"); //.text($("#listaslika").text()+resp);
//            nasdiv.append(preview);
//            nasdiv.append("<br>");
//            nasdiv.append("<div style=\"font-weight: 500;\">Kopirajte link ispod i nalepite ga u tekst vesti</div>");
//            $(document.createTextNode(resp)).appendTo(nasdiv);
//            nasdiv.append("<br>");
            $("#form3").modal('toggle');
            setTimeout(function () {
                $("#successUploadSlikeGalerija").fadeOut(400);
            }, 2000);
        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }

    });
    setTimeout(function () {
        location.reload(true);
    }, 3000);


});

//deaktiviraj sliku u galeriji
$(".deaktivirajSliku").on("click", function (e) {

    // var formData = new FormData();
    // var fileupload = $('#slikauploadgalerija');
    var slikaId = $(this).attr("slikaId");

    $.ajax({
        type: "post",
        cache: false,
        contentType: false,
        processData: false,
        //  enctype: 'multipart/form-data',
        method: 'POST',
        type: 'POST',
        url: '/post/deaktivirajSlikuGalerija/' + slikaId,
        //  data: formData,
        success: function (resp) {
//            var preview = $.parseHTML(resp);
//            $(preview).css("max-width", "100px");
//            $(preview).css("max-height", "100px");
//            var nasdiv = $("#listaslika"); //.text($("#listaslika").text()+resp);
//            nasdiv.append(preview);
//            nasdiv.append("<br>");
//            nasdiv.append("<div style=\"font-weight: 500;\">Kopirajte link ispod i nalepite ga u tekst vesti</div>");
//            $(document.createTextNode(resp)).appendTo(nasdiv);
//            nasdiv.append("<br>");
//           $("#form3").modal('toggle');
            //  alert("radi ili ne radi");
            setTimeout(function () {
                $("#removeImgGallery").fadeOut(400);
            }, 2000);


        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    });

    $(this).css("display", "none");
    setTimeout(function () {
        location.reload(true);
    }, 4000);

});


$("#uploadkomentar").on("click", function (e) {

    var formData = new FormData();

    formData.append("ime", $('#kokomentarise').val());
    formData.append("poruka", $('#comment').val());
    var vestid = $(this).attr("vestid");
    $.ajax({
        type: "post",
        cache: false,
        contentType: false,
        processData: false,
        method: 'POST',
        type: 'POST',
        url: '/posaljiKomentar/' + vestid,
        data: formData,
        success: function (resp) {

            //popup hvala sto ste poslali komentar
//             $("#hvalaNaKomentaru").fadeIn(200);
             setTimeout(function () {
                $("#hvalaNaKomentaru").fadeOut(400);
            }, 2000);

        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }

    });

$(this).css("display", "none");
    setTimeout(function () {
        location.reload(true);
    }, 4000);

});

$(".uploadOdgovor").on("click", function (e) {
  // console.log("pojavi se");
    var formData = new FormData();
var komenarid = $(this).attr("komentarid");
    formData.append("ime", $('.kokomentarise[komentarid='+komenarid+']').val());
    formData.append("poruka", $('.comment[komentarid='+komenarid+']').val());
    
    $.ajax({
        type: "post",
        cache: false,
        contentType: false,
        processData: false,
        method: 'POST',
        type: 'POST',
        url: '/posaljiOdgovor/' + komenarid,
        data: formData,
        success: function (resp) {
  //  console.log("skloni se");
            //popup hvala sto ste poslali komentar
//             $("#hvalaNaKomentaru").fadeIn(200);

             setTimeout(function () {
                 //    console.log("pojavi se");
                $("#hvalaNaKomentaru").fadeOut(400);
                $('.answer-div[komentarid='+komenarid+']').toggle();
            }, 2000);

        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }

    });
   // console.log("skloni se");

$(this).css("display", "none");
    setTimeout(function () {
        location.reload(true);
    }, 4000);
});