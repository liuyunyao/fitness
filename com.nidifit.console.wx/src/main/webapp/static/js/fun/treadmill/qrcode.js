$(function() {
	qrcode_resetFontsize(); //重置页面单位标准
	// This method just used for developing.
	$('#nidoneIMG').click(function() {
		var url = "infos";
		window.open(url);
	});
});

function qrcode_resetFontsize() {
	var ui_width = 1920;
	var ui_height = 1080;
	var pageWidth = $(window).width();
	var pageHeight = $(window).height();
	var widthScale = pageWidth / ui_width;
	var heightScale = pageHeight / ui_height;
	var jz_num = widthScale;
	if(Math.abs(widthScale - 1) > Math.abs(heightScale - 1) || Math.abs(widthScale - 1) == 0) jz_num = heightScale;
	if(Math.abs(widthScale - 1) > Math.abs(heightScale - 1) && Math.abs(heightScale - 1) == 0) jz_num = widthScale;
	console.log("Math.abs(widthScale)=" + Math.abs(widthScale));
	console.log("Math.abs(heightScale)=" + Math.abs(heightScale));
	console.log("Math.abs(widthScale-1)=" + Math.abs(widthScale - 1));
	console.log("Math.abs(heightScale-1)=" + Math.abs(heightScale - 1));
	console.log("jz_num=" + jz_num);
	jz_num = (jz_num * 20).toFixed(0);
	$("html").css("font-size", jz_num + "px");
}