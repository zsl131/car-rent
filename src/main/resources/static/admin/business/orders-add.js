$(function() {
    $("#search-car-id").click(function() {
        var inputObj = $(this).siblings("input[name='param']");
        var param = $(inputObj).val();
        if(param==null || $.trim(param)=='') {
            showDialog("请认真输入条件，可输入车牌号码、品牌名称或品牌系列名称搜索");
        } else {
            $.get("/admin/orders/listCars", {params:param}, function(res) {
                var resDatas = ($(res).find("#result-datas").html());
                $("#show-cars").html(resDatas);
            });
        }
    });

    $("#search-people-id").click(function() {
        var resCode = readCard();
        var inputObj = $(this).siblings("input[name='param']");
        if(resCode==1) {
            var code = IdrControl1.GetCode(); //身份证号
            $(inputObj).val(code);
        }
        var param = $(inputObj).val();
        if(param==null || $.trim(param)=='') {
            showDialog("请认真输入客户身份证号");
        } else {
            var nameObj = $("input[name='costumerName']"); //姓名
            var identityObj = $("input[name='costumerIdentity']"); //身份证号
            var addressObj = $("input[name='costumerAddress']"); //地址
            var phoneObj = $("input[name='costumerPhone']"); //电话
            var sexObj = $("input[name='costumerSex']"); //性别
            var ageObj = $("input[name='costumerAge']"); //年龄
            $.post("/admin/orders/queryPeople", {identity:param}, function(res) {
                if(res) {
                    $(nameObj).val(res.name); $(addressObj).val(res.address);
                    $(identityObj).val(res.identity); $(phoneObj).val(res.phone);
                    $(sexObj).val(res.sex); $(ageObj).val(res.age);
                    var idenPic = res.idenPic; var idenBackPic = res.idenBackPic; var drivePic = res.drivePic;
                    if(idenPic!=null && $.trim(idenPic)!='') {
                        $("input[name='idenPic']").siblings("div.upload-info-div").html('<a href="'+idenPic+'" title="点击查看大图" target="_blank" class="upload-img-href"><img src="'+idenPic+'"/></a>');
                        $("input[name='idenPic']").val(idenPic);
                    }
                    if(idenBackPic!=null && $.trim(idenBackPic)!='') {
                        $("input[name='idenBackPic']").siblings("div.upload-info-div").html('<a href="'+idenBackPic+'" title="点击查看大图" target="_blank" class="upload-img-href"><img src="'+idenBackPic+'"/></a>');
                        $("input[name='idenBackPic']").val(idenBackPic);
                    }
                    if(drivePic!=null && $.trim(drivePic)!='') {
                        $("input[name='drivePic']").siblings("div.upload-info-div").html('<a href="'+drivePic+'" title="点击查看大图" target="_blank" class="upload-img-href"><img src="'+drivePic+'"/></a>');
                        $("input[name='drivePic']").val(drivePic);
                    }
                } else {alert("没有客户信息");}
            });
        }
    });

    $("#dataForm").submit(function() {
        var canSubmit = true;
        $(".need-check").each(function() {
            var val = $(this).val();
            if(val==null || $.trim(val)=='') {
                var msg = $(this).attr("placeholder");
                showDialog(msg, "数据不完整提示");
                canSubmit = false;
                return false;
            }
        });
        if(canSubmit) {return true;}
        else {return false;}
    });

    $("input[name='days']").keyup(function() {
        setMoney();
    });
});

function setMoney() {
    var price = parseInt($("input[name='price']").val());
    var days = parseInt($("input[name='days']").val());
    var money = price * days;
    if(isNaN(money)) {money = 0;}
    $("input[name='needMoney']").val(money);
    $("input[name='money']").val(money);
}

function setCarId(obj) {
    var val = $(obj).val(); var infoId = $(obj).attr("infoId");
    $("input[name='carId']").val(val);
    $.post("/admin/orders/queryCarInfo", {id: infoId}, function(info) {
        $("input[name='price']").val(info.rjj+" 元/天");
        $("input[name='days']").removeAttr("readonly");
        setMoney();
    });
//    alert($("input[name='carId']").val());
}

function myupload(title, path, obj) {
    var fileName = $(obj).attr("oldName");
    var myCameraDialog = startCamera(title, function() {
        var base64 = getBase64();

        //alert(base64);
        $.post("/web/uploadByBase64", {imgStr:base64, path : path, fileName : fileName}, function(res) {
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