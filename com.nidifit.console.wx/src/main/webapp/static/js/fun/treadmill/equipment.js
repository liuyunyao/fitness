$(function() {
	switchEquipment(); //switch equipment
});

function switchEquipment() { //switch equipment
	$('.select-name').on('click', function() {
		$('.select-name').removeClass('active');
		$(this).addClass('active');
	});
}