jQuery(function($) {
    $('#dataForm').bootstrapValidator({
//		        live: 'disabled',
        message: '验证不通过',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            brandId: {
                validators: {
                    notEmpty: {
                        message: '请选择车辆品牌'
                    }
                }
            },
            ppxl: {
                validators: {
                    notEmpty: {
                        message: '请输入品牌系列'
                    }
                }
            },
            typeId: {
                validators: {
                    notEmpty: {
                        message: '请选择车辆种类'
                    }
                }
            }
        }
    });
});