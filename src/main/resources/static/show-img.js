$(function() {
    $("img.show-img-cls").each(function() {
        var thisObj = $(this);
        var fileName = $(thisObj).attr("alt");
        //alert(fileName);
        $.get("/public/showImg", {fileName:fileName}, function(data) {
            $(thisObj).attr("src", "data:image/jpg;base64, "+data);
//            alert(data);
        });
    });
});