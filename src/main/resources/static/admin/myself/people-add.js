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

});

function myupload(title, obj) {
    var fileName = $(obj).attr("oldName");
    var myCameraDialog = startCamera(title, function() {
        var base64 = getBase64();

        //alert(base64);
        $.post("/web/uploadByBase64", {imgStr:base64, path : "/people/", fileName : fileName}, function(res) {
//            alert(res.resCode+"===="+res.resMsg);
            if(res.resCode==0) {
                alert("上传失败："+res.resMsg);
            } else {
                var imgPath = res.resMsg;
                $(myCameraDialog).modal("hide");
                $(obj).attr("oldName", imgPath);
                var showObj = $(obj).siblings("div.upload-info-div");
                $(showObj).html('<a href="'+imgPath+'" title="点击查看大图" target="_blank" class="upload-img-href"><img src="'+imgPath+'"/></a>');
                $(obj).siblings("input.need-input-val").val(imgPath);
            }
        }, "json");
    });
}


