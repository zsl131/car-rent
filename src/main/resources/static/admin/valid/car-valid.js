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
            carNo: {
                validators: {
                    notEmpty: {
                        message: '请输入车牌号码'
                    },
                    stringLength: {
                        min: 7,
                        max: 7,
                        message: '车牌号码长度只能为7，如：云C00001'
                    }
                }
            },
            engineNo: {
                validators: {
                    notEmpty: {
                        message: '请输入完整发动机号'
                    }
                }
            },
            frameNo: {
                validators: {
                    notEmpty: {
                        message: '请输入完整车架车'
                    }
                }
            }
        }
    });
});