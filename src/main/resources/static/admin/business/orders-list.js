$(function() {
    $(".update-status-cls").click(function() {
        var objId = $(this).attr("objId");
        var legalCount = $(this).attr("legalCount"); //违章条数
        var legalMoney = $(this).attr("legalMoney"); //违章罚款
        var legalScore = $(this).attr("legalScore"); //违章扣分
//        alert(objId);
        var html =  '<p class="remind-red">状态说明：</p>'+
                    '<p class="remind-gray">·已归还：车辆已经归还到仓库；</p>'+
                    '<p class="remind-gray">·已取消：客户终止租车；</p>'+
                    '<p class="remind-gray">·已完结：压金已退还客户，订单已完成；</p>'+
                    '<div class="form-group form-group-lg">' +
                       '<div class="input-group input-group-lg">' +
                            '<div class="input-group-addon">订单状态</div>' +
                            '<div class="form-control">' +
                            '<input type="radio" name="ordersStatus" value="2" id="orders-status-2"/><label for="orders-status-2">设置为已归还&nbsp;&nbsp;</label>' +
                            '<input type="radio" name="ordersStatus" value="3" id="orders-status-3"/><label for="orders-status-3">设置为已取消&nbsp;&nbsp;</label>' +
                            '<input type="radio" name="ordersStatus" value="10" id="orders-status-10"/><label for="orders-status-10">设置为已完结</label>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +

                    '<div class="form-group form-group-lg">' +
                    '<div class="input-group input-group-lg">' +
                        '<div class="input-group-addon">违章信息</div>' +
                        '<span class="form-control">共'+legalCount+'条违章，扣'+legalScore+'分，罚款'+legalMoney+'元</span>' +
                    '</div>'+
                    '</div>'+

                    '<div class="form-group form-group-lg">' +
                    '<div class="input-group input-group-lg">' +
                        '<div class="input-group-addon">扣除压金</div>' +
                        '<input class="form-control" name="money" placeholder="请输入需要扣除的压金金额" onkeyup="this.value=this.value.replace(/\\D/g,\'\')"/>' +
                    '</div>'+
                    '</div>'+

                    '<div class="form-group form-group-lg">' +
                    '<div class="input-group input-group-lg">' +
                    '<div class="input-group-addon">备注信息</div>' +
                    '<textarea style="width:100%" rows="5" placeholder="请输入设置状态的原因"></textarea>' +
                    '</div>'+
                    '</div>';
        var statusDialog = confirmDialog(html, "修改订单状态", function() {
            var status = $(statusDialog).find("input[name='ordersStatus']:checked").val();
            var msg = $(statusDialog).find("textarea").val();
            if(!status) {
                showDialog("请选择订单状态");
            } else {
                //alert(status+"===="+msg);
                if(status=='3' && (msg==null || $.trim(msg)=='')) {showDialog("请输入取消订单的原因");}
                else {
                    $.post("/admin/orders/updateStatus/"+objId+"/"+status, {msg : msg}, function(res) {
                        if(res=='1') {window.location.reload();}
                        else {showDialog("设置订单状态失败");}
                    }, "json");
                }
            }
        });
    });
});