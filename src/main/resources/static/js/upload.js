 $("#uploadslike").on("click",async function (e) {
   
  let formData = new FormData(); 
   
  formData.append("file", $('input[type=file]')[0].files[0]);
   formData.append("alt_text", $("#alt-tekst").val());
    formData.append("title", $("#naslov-slike").val());
   formData.append("galerija", $("#galerijaslika").val());
//  let response = await fetch('/admin/novaSlika/save', {
//    method: "POST", 
//    body: formData
//  }); 

$.ajax({
    url: '/admin/novaSlika/save',
    data: formData,
    type: 'POST',
    contentType: false, // NEEDED, DON'T OMIT THIS (requires jQuery 1.6+)
    processData: false, // NEEDED, DON'T OMIT THIS
    // ... Other options like success and etc
});
 });