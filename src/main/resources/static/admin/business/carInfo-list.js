$(function() {
    $(".set-money-btn").click(function() {
        var objId = $(this).attr("objId");
        var curVal = $(this).attr("curVal");

        var msg = '<p style="font-weight:bold;">请输入此车的日租金：</p>'
                  + '<input class="form-control" value="'+curVal+'" onkeyup="this.value=this.value.replace(/\\D/g,\'\')"/>';
        var dialog = confirmDialog(msg, "<span class='fa fa-pencil'>设置日租金</span>", function() {
            var setVal = parseFloat($(dialog).find(".form-control").val());
            //alert(setVal);
            if(isNaN(setVal) || setVal<=0) {
                alert("请认真输入日租金！");
            } else {
                $(dialog).modal("hide");
                $.post("/admin/carInfo/updatePrice/"+objId+"/"+setVal, {}, function(res) {
                    if(res=='1') {window.location.reload();}
                    else {alert("保存失败："+res);}
                }, "json");
            }
        });
    });
});