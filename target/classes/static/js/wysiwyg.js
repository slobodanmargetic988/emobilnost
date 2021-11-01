$(function () {
    $('.wysiwyg_editor_default').summernote({
        height: 200,
        toolbar: [
            ['parastyle', ['style']],
            ['fontstyle', ['fontname', 'fontsize']],
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough', 'superscript', 'subscript']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert', ['picture', 'link', 'table', 'hr']],
            ['history', ['undo', 'redo']],
            ['misc', ['codeview', 'fullscreen']],
            ['help', ['help']]
        ],
    });
});