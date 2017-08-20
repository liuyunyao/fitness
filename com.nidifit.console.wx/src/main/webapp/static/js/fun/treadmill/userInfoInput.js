'use strict';
/**
 * These functions below can be invoked after loading DOM
 */
$(function() {
	common.resetFontsize();  //重置页面元素大小
	initRadioValue(); //初始化radio事件
	toggleRadioValue(); //绑定切换radio事件
	// Error message obj
	var errorMsg;

	$('#createBtn').click(function() {
		if($(this).attr("submited") == true) {
			return;
		} else {
			var age = $('#age').val();
			var height = $('#height').val();
			var weight = $('#weight').val();
			var phone = $('#phone').val();
			var numRex = /^[0-9]+?$/;
			var phoneRex = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
			$(this).attr("submited", "true");
			if('' == age || 'undefined' == typeof age) {
				$(".input-error").text('');
				$('#age').parents('.input-group').find('.input-error').text('请输入您的年龄');
				$(this).removeAttr("submited");
				return;
			}
			if('' == height || 'undefined' == typeof height) {
				$(".input-error").text('');
				$('#height').parents('.input-group').find('.input-error').text('请输入您的身高');
				$(this).removeAttr("submited");
				return;
			}
			if('' == weight || 'undefined' == typeof weight) {
				$(".input-error").text('');
				$('#weight').parents('.input-group').find('.input-error').text('请输入您的体重');
				$(this).removeAttr("submited");
				return;
			}
			if(!numRex.test(height) || !numRex.test(weight)) {
				$(".input-error").text('');
				$('#phone').parents('.input-group').find('.input-error').text('请输入正确的数字格式');
				$(this).removeAttr("submited");
				return;
			}
			if('' != phone && 'undefined' != typeof phone) {
				if(!phoneRex.test(phone)) {
					$(".input-error").text('');
					$('#phone').parents('.input-group').find('.input-error').text('请输入正确的手机号码');
					$(this).removeAttr("submited");
					return;
				}
			}

			var formData = {};
			// Parse form data to json object --- Serialize form data as JSON Created by dav 7/16/17 6:05 PM
			$("#inputForm").serializeArray().map(function(x) {
				if(formData[x.name] !== undefined) {
					if(!formData[x.name].push) {
						formData[x.name] = [formData[x.name]];
					}
					formData[x.name].push(x.value || '');
				} else {
					formData[x.name] = x.value || '';
				}
			});
			$("#userInfoInput .input-error").text('');
			$("#userInfoInput .mask").show();//save gif
			$.ajax({
				type: "POST",
				url: "saveInfos",
				cache: false,
				dataType: 'json',
				data: formData,
				success: function(result) {				
					$("#userInfoInput .mask").hide();
					if(!result.success) {
						errorMsg = result.message;
						alert(errorMsg);
					} else {
						window.location = "unlock"
					}
				}
			});
		}

	});
});

//初始化radio事件
function initRadioValue(val) {
	var sex = val;
	if(sex == undefined || sex == null || sex == "" || sex == "undefined") {
		sex = 2;
	}
	$("#userInfoInput input[name='sexType']").val(sex);
	$.each($(".radio-item"), function(i, n) {
		var radiovalue = $(n).attr("data-radio");
		if(sex == radiovalue) {
			$(".radio-item label").removeClass("active");
			$(n).find("label").addClass("active");
			return '';
		}
	});
}

//绑定切换radio事件
function toggleRadioValue() {
	$(".radio-item").on('click', function() {
		var sex = $(this).attr("data-radio");
		$("#userInfoInput input[name='sexType']").val(sex);
		$(this).parents().find(".radio-item label").removeClass("active");
		$(this).find("label").addClass("active");
	});
}