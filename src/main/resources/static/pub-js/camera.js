/** bootstrap modal */
function startCamera(title,okfn) {
	var idStr = "myModal_"+parseInt(Math.random()*100000000);
	if($.trim(title)=='') {title = "快拍仪";}
	var msg = '<div class="row"><p class="text-center">系统将自动打开快拍仪，若超过10秒未打开，请检查设备是否连接正确！</p><object classid="clsid:454C18E2-8B7D-43C6-8C17-B1825B49D7DE" id="captrue" width="100%" height="300"></object></div>'+
	          '<div class="row container-fluid">' +

	          //'<input type="button" value="打开拍照" class="btn btn-info btn-sm" onclick="Start1_onclick()"/>'+
	          //'<input type="button" value="获取Base64" class="btn btn-info btn-sm" onclick="Base64_onclick()"/>'+

	          '</div>';
	var html = '';
	html += '<div class="modal fade" id="'+idStr+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
		  	'<div class="modal-dialog" role="document">' +
		  	'<div class="modal-content">' +
		  	'<div class="modal-header">' +
		  	'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
		  	'<h4 class="modal-title" id="myModalLabel">'+title+'</h4>' +
		  	'</div>' +
		  	'<div class="modal-body">'+msg+'</div>' +
		  	'<div class="modal-footer">' +
		  	'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
		  	'<button type="button" class="btn btn-primary dialog-ok-btn">上传图片</button>' +
		  	'</div></div></div></div>';

	$(html).appendTo("body");
	$(("#"+idStr)).modal({keyboard:true, show:true, backdrop:'static'});
	$(("#"+idStr)).find(".dialog-ok-btn").click(okfn);
    $(("#"+idStr)).on('hidden.bs.modal', function (e) {
        stop_camera(); //先关闭，再清除
        $(("#"+idStr)).remove();
    });
    setTimeout('Start1_onclick()',500); //延迟1秒执行
}

/** 获取图片的Base64编码 */
function getBase64() {
    var str = captrue.sGetBase64();
    return str;
}
//启动主
function Start1_onclick() {
    stop_camera();
    captrue.bStartPlay(); //再启动
}

function stop_camera() {
    captrue.bStopPlay(); //先停止
}