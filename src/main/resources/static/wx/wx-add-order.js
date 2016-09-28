$(function() {
    $(".add-order-href").click(function() {
        var carId = $(this).attr("carId");
        var carName = $(this).attr("carName");

        addOrder(carId, carName);
    });
});

function addOrder(carId, carName) {
    if(!carName || carName==null || carName=='') {carName = "未选择";}

    var html = '<div class="form-group form-group-lg">' +
                 '<div class="input-group input-group-lg">' +
                      '<div class="input-group-addon">车辆</div>' +
                      '<span class="form-control">'+carName+'</span>' +
                  '</div>' +
              '</div>' +

              '<div class="form-group form-group-lg">' +
                   '<div class="input-group input-group-lg">' +
                        '<div class="input-group-addon">姓名</div>' +
                        '<input class="form-control" name="name" placeholder="输入姓名"/>' +
                    '</div>' +
                '</div>' +
              '<div class="form-group form-group-lg">' +
                   '<div class="input-group input-group-lg">' +
                        '<div class="input-group-addon">手机</div>' +
                        '<input class="form-control" name="phone" placeholder="输入手机号码"/>' +
                    '</div>' +
                '</div>' +
              '<div class="form-group form-group-lg">' +
                   '<div class="input-group input-group-lg">' +
                        '<div class="input-group-addon">天数</div>' +
                        '<input class="form-control" name="days" placeholder="输入租车天数"/>' +
                    '</div>' +
                '</div>' +
              '<div class="form-group form-group-lg">' +
                   '<div class="input-group input-group-lg">' +
                        '<div class="input-group-addon">备注</div>' +
                        '<input class="form-control" name="remind" placeholder="输入备注，可空"/>' +
                    '</div>' +
                '</div>' +
            '';

    var orderDialog = confirmDialog(html, '<i class="fa fa-gavel"></i> 立即租车', function() {
        var name = $(orderDialog).find("input[name='name']").val(); //姓名
        var phone = $(orderDialog).find("input[name='phone']").val(); //手机
        var days = $(orderDialog).find("input[name='days']").val(); //天数
        var remind = $(orderDialog).find("input[name='remind']").val(); //天数

        if(phone==null || $.trim(phone).length!=11) {
            showDialog("请输入正确的手机号码，以方便我们与您取得联系，谢谢！");
        } else if(name==null || $.trim(name)=='') {
            showDialog("请输入姓名，方便称呼，谢谢！");
        } else {
            days = parseInt(days); days = isNaN(days)?0:days;
            //alert(name+"==="+phone+"=="+days);

            var conDialog = confirmDialog('确定提交租车信息吗？', "信息确认", function() {
                $.get("/app/addOrder", {name:name, days:days, remind:remind, phone:phone, infoId:carId}, function(res) {
                    if(res.resCode==1) {
                        showDialog("下单成功，请注意接听电话！祝您生活愉快！");
                        $(conDialog).modal("hide");
                        $(orderDialog).modal("hide");
                        //可以跳转到订单页面
                    } else {
                        showDialog("下单失败："+res.resMsg); $(conDialog).modal("hide");
                    }
                }, "json");
            });
        }
    });
}