$(function() {
	switchSex();
	selectAthleticgoal();
		compatiblePlaceholder();
	//	clickAgeInput();
});

function switchSex() { //sex switch 
	$('.sex-content .select-item').on('click', function() {
		if(!$(this).hasClass('active')) {
			$('.sex-content .select-item').removeClass('active');
			$(this).addClass('active');
			$('input[name="sex"]').val($(this).attr('data-sexval'));
		}
	})
}

function selectAthleticgoal() { //athletic goal select
	$('.athleticgoal-content .select-item').on('click', function() {
		if(!$(this).hasClass('active')) {
			$('.athleticgoal-content .select-item').removeClass('active');
			$(this).addClass('active');
			$('input[name="athleticgoal"]').val($(this).text());
		}
	})
}

function compatiblePlaceholder() { //compatible with Placeholder
	$('input[name="age"]').focus(function() {
		var obj = $(this);
		var initVal = obj.val();
		var switchType = null;
		obj.prop('type', 'date');
		setTimeout(function() {
			if(navigator.userAgent.indexOf('iPhone') > -1) {
				obj.trigger('blur');
				obj.trigger('focus');
			} else obj.trigger('click');
		}, 10);
	});
}

function replanning_submit(obj) {
	if(!common.formValidate($('.input-container'), obj)) {

	}
}

function clickAgeInput() {
	$('input[name="age"]').on('change', function(e) {
		var val = $(this).val();
		$('input[name="age"]').val(val.substring(0, 4));
	})
}