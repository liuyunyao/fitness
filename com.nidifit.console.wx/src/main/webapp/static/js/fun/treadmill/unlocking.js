$(function() {
	common.resetFontsize();  //重置页面元素大小
	setTimeout(function() {
		$("#unlocking .schedual-bar").addClass("active");
	}, 2000)
	setTimeout(function() {
		$("#unlocking .mask").show();
	}, 3000)
})

function unlocking_submit(obj){
	$("#unlocking .mask").hide();
}
