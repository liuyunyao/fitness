"use strict";
(function() {
	var common = {
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
		formValidate: function(form, obj) { //表单验证
			var that = this;
			if(obj != undefined && $(obj).attr("submited") != null) {
				return false;
			}
			$(obj).attr("submited", "true");
			if(form == undefined) {
				return true;
			}
			var formAFObj = $(form);
			var eles = formAFObj.find("input");
			var ele;
			var msg;
			var check;
			for(var i = 0; i < eles.length; i++) {
				ele = eles[i];
				if($(ele).attr("required") != undefined && $(ele).attr("required") != null) {
					if(ele.value == undefined || ele.value.length < 1) {
						if($(ele).hasClass("ui-date-scroll")) {
							msg = "年龄为空";
							that.removeSubmitedFlag(obj, $(ele), msg);
							return false;
						}
						if($(ele).hasClass("height")) {
							msg = "身高为空";
							that.removeSubmitedFlag(obj, $(ele), msg);
							return false;
						}
						if($(ele).hasClass("weight")) {
							msg = "体重为空";
							that.removeSubmitedFlag(obj, $(ele), msg);
							return false;
						}
					}
				}
			}
			var weight=formAFObj.find(".weight");
			if(weight.val()<0||weight.val()>500){
				msg="体重填写不正确";
				that.removeSubmitedFlag(obj,weight,msg);
				return false;
			}
			var height=formAFObj.find(".height");
			if(height.val()<0||height.val()>300){
				msg="身高填写不正确";
				that.removeSubmitedFlag(obj,height,msg);
				return false;
			}
			$(".error-tip").each(function(i, n) {$(n).text("");})
			return true;
		},
		removeSubmitedFlag: function(clickObj, validateObj, msg) { //避免重复点击
			$(clickObj).removeAttr("submited");
			$(".error-tip").each(function(i, n) {
				$(n).text("");
			})
			$(validateObj).parents("form").find(".error-tip").text(msg);
		}
	}
	window.common = common;
})();