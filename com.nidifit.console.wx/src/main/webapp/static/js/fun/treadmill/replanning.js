$(function() {
	switchSex();
	selectAthleticgoal();
	initDate();
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

function initDate() { //date init
	/**
	 * @param  {[date]}      [设置日期]
	 * @param  {[onSelect]}  [日期选中后回调函数 参数date为选中日期]
	 */
	date.init({
		date: '',
		onSelect: function(date) {
			var year = date.substring(0,4);
			var age = new Date().getFullYear()-year;
			$('input[name="age"]').val(age+'('+year+')');
		}
	});
}

function replanning_submit(obj) {
	if(common.formValidate($('.input-container'), obj)){
        var $inputArr = $('input');//length = 3
        //循环处理input,并定义结果集
        var result ="";
        $inputArr.each(function(){
            //将每个input的值放进结果集
			var name=$(this).attr("name");
			var val=$(this).val();
            result+="\""+name+"\""+":"+"\""+val+"\""+",";
        });
        result=result.substring(0,result.length-1);
        result="{"+result+"}";
        console.log(result);
		window.location.href="/v1/trainingplan?result="+result;
    }
}