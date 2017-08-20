"use strict";
(function() {
	var mobile = {
		resetFontsize: function(width) {
			var ui_width = 750;
			var jz_num;
			var offWidth = $(window).width();
			if(width) ui_width = width;
			if(offWidth > 750) offWidth = 750;
			jz_num = offWidth / ui_width;
			jz_num = (jz_num * 20).toFixed(0);
			$("html").css("font-size", jz_num + "px");
		},
		doubleTrigger: function(obj) {
			$(obj).trigger('click');
			$(obj).trigger('click');
		}
	}
	window.mobile = mobile;
	mobile.resetFontsize();
})();