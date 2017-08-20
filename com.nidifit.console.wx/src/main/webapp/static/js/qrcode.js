$(function() {
	var ui_width = 1920;
	var ui_height = 1080;
	var pageWidth = $(window).width();
	var pageHeight = $(window).height();
	var widthScale = pageWidth / ui_width;
	var heightScale = pageHeight / ui_height;
	var jz_num = widthScale;
	if(Math.abs(widthScale - 1) > Math.abs(heightScale - 1) || Math.abs(widthScale - 1) == 0) jz_num = heightScale;
	if(Math.abs(widthScale - 1) > Math.abs(heightScale - 1) && Math.abs(heightScale - 1) == 0) jz_num = widthScale;
	jz_num = (jz_num * 20).toFixed(0);
	$("html").css("font-size", jz_num + "px");
});