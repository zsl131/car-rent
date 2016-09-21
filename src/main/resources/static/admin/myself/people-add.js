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

    $("#read-identity").click(function() {
        var resCode = readCard();
        if(resCode==1) {
            var name = IdrControl1.GetName(); //姓名
            var nation = IdrControl1.GetFolk(); //民族
//            var sex = IdrControl1.GetSex(); //性别,男、女
            var sex = IdrControl1.GetSexN(); //性别，1、2
            var code = IdrControl1.GetCode(); //身份证号
            var address = IdrControl1.GetAddress(); //地址
            $("#identity").val(code); $("#sex").val(sex);
            $("#name").val(name); $("#address").val(address);
            var valid = identityCodeValid(code);
            if(valid) {
                //var sex = idCardInfo(code, "2"); //获取性别
                var age = idCardInfo(code, "3"); //获取年龄
                //alert(sex+"===="+age);
                $("#age").val(age);
            }

//            var photo = IdrControl1.GetPhotobuf(); //身份证头像
            var photo = IdrControl1.GetCardPhotobuf(); //身份证正反面
            var obj = $("#id-photo");
            var fileName = $(obj).attr("oldName");
            $.post("/web/uploadByBase64", {imgStr:photo, path : "/people/", fileName : fileName}, function(res) {
                if(res.resCode==0) {
                    alert("上传失败："+res.resMsg);
                } else {
                    var imgPath = res.resMsg;

                    $(obj).attr("oldName", imgPath);
                    var showObj = $(obj).siblings("div.upload-info-div");
                    $(showObj).html('<a href="'+imgPath+'" title="点击查看大图" target="_blank" class="upload-img-href"><img src="'+imgPath+'"/></a>');
                    $(obj).siblings("input.need-input-val").val(imgPath);
                }
            }, "json");
//            alert(code+"==="+address);
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


