$(function() {
    $("#identity").blur(function() {
        var thisObj = $(this);
        var val = $(thisObj).val();
        var valid = identityCodeValid(val);
        if(valid) {
            var sex = idCardInfo(val, "2"); //获取性别
            var age = idCardInfo(val, "3"); //获取年龄
//            alert(sex+"===="+age);
            $("#sex").val(sex);
            $("#age").val(age);
        } else {
            //$(thisObj).focus();
            showDialog("身份证号验证失败，请检查是否正确！");
        }
    });
});