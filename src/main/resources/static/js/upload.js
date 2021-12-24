$("#uploadslike").on("click", function (e) {

    var formData = new FormData();
    var fileupload = $('#fileupload')
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
            $(document.createTextNode(resp)).appendTo(nasdiv);
            nasdiv.append("<br></br>");
$("#form").modal('toggle');

        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    });


});

$("#uploadvideo").on("click", function (e) {

    var formData = new FormData();
    var fileupload = $('#videoupload')
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
            $(preview).css("max-width", "100px");
            $(preview).css("max-height", "100px");
            $(preview).children('video').width(100);
            $(preview).children('video').height(100);
            var nasdiv = $("#listavidea"); //.text($("#listaslika").text()+resp);
            nasdiv.append(preview);
            $(document.createTextNode(resp)).appendTo(nasdiv);
            nasdiv.append("<br></br>");
$("#form2").modal('toggle');

        },
        error: function (jqXHR) {
            alert(jqXHR.status);
        }
    });


});