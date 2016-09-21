function readCard() {
	var result;
	var photoname;
	var cardname;
	var cardsn;
    try {
        var ax = new ActiveXObject("IDRCONTROL.IdrControlCtrl.1");
//        alert("已安装");

        result=IdrControl1.ReadCard("1001", "");
        if(result==1) {
            var name = IdrControl1.GetName(); //姓名
            var nation = IdrControl1.GetFolk(); //民族
            var sex = IdrControl1.GetSex(); //性别
            var code = IdrControl1.GetCode(); //身份证号
            var address = IdrControl1.GetAddress(); //地址
//            alert(code);
        } else if(result == -1) {
            showDialog("端口初始化失败，请检查身份证阅读器是否连接正确！<p class='remind-red'>注意：只能使用IE、360等浏览器使用此功能！</p>");
        } else if(result == -2) {
            showDialog("请重新将卡放到阅读器上！");
        } else if(result == -3) {
            showDialog("读取数据失败！");
        }
    } catch(e) {
        alert(e);
        showDialog("身份证阅读器控件未能正确安装，请先<a href='/reader-setup.exe' target='_blank'><b class='remind-red'>下载驱动</b></a>自行安装！" +
                   "<p class='remind-red'>注意：只能使用IE、360等浏览器使用此功能！</p>");
    //        alert("控件未安装");
    }
    return result;
}