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

    $('#dataForm').bootstrapValidator({
        message: '验证不通过',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入客户姓名'
                    }
                }
            },
            identity: {
                validators: {
                    notEmpty: {
                        message: '请输入身份证号'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: '请输入手机号码'
                    }
                }
            }
        }
    });

    /*$("#upload-identity").fileinput({
        uploadUrl: '#',
        language: 'zh',
        dropZoneTitle: "此处上传<b class='remind-red'>身份证</b>图片",
        maxFilesNum: 1,
        allowedFileExtensions : ['jpg', 'png','gif']
    });
    $("#upload-drive").fileinput({
        uploadUrl: '#',
        language: 'zh',
        dropZoneTitle: "此处上传<b class='remind-red'>驾驶证</b>图片",
        maxFilesNum: 1,
        allowedFileExtensions : ['jpg', 'png','gif']
    });*/

});

function myupload(title) {
    var dialog = startCamera(title, function() {
        var base64 = getBase64();

        //alert(base64);
        $.post("/web/uploadByBase64", {imgStr:base64, path : "/people/"}, function(res) {
//            alert(res.resCode+"===="+res.resMsg);
            if(res.resCode==1) {
                $(dialog).modal("hide");

            } else {alert("上传失败："+res.resMsg);}
        }, "json");
    });
}


