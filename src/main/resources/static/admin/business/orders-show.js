$(function() {
    $("input.form-control").each(function() {
        $(this).removeAttr("disabled");
        $(this).attr("oldVal", $(this).val());
    });
    $("input.form-control").keyup(function() {
        var val = $(this).attr("oldVal");
        $(this).val(val);
    });
});