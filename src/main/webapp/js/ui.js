$(function(){

    $('.timepicker').timepicki({
        show_meridian:false,
		min_hour_value:0,
		max_hour_value:23,
		step_size_minutes:1,
		overflow_minutes:true,
		increase_direction:'up',
		//disable_keyboard_mobile: true
	});

	$('#header .r>.f').on('mouseenter', function(){
		//$('.pwd-layer').slideDown(300);
		$('.pwd-layer').fadeIn();
		$('.pwd-layer').addClass('pw-fadeInDown')
	});
	$('#header .r>.f').on('mouseleave', function(){
		//$('.pwd-layer').slideUp(300);
		$('.pwd-layer').removeClass('pw-fadeInDown').addClass('pw-fadeInUp');
		$('.pwd-layer').fadeOut(300, function(){
			$('.pwd-layer').removeClass('pw-fadeInUp');
		});
		
	})
	
	$('#header .l>button').on('click', function(){
		$('.side-menu-wrap').addClass('open');
		$('.side-menu-wrap').prepend('<div class="dim"></div>');
	});
	$('.sd-top > button').on('click', function(){
		$('.side-menu-wrap').removeClass('open');
		$('.side-menu-wrap > .dim').remove();
	});

	$(document).on('click','.side-menu-wrap.open > .dim', function(){
		$('.side-menu-wrap').removeClass('open');
		$('.side-menu-wrap > .dim').remove();
	})

    // datepicker
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		showMonthAfterYear: true,
		yearSuffix: '년',
		buttonImageOnly: false,
		buttonText: "달력",
		
	});

	$(".datepicker").datepicker();

});


function layerPopupOpen(id){
	
	//$(".layer-popup-box[data-popup="+id+"]").show();
	//$('[data-popup="' + id +'"]>.popup').focus();
	
	$(".layerpopup[data-popup="+id+"]").fadeIn();
	$(".layerpopup[data-popup="+id+"]").find('.popup').addClass('fadeInDown');
	$('[data-popup="' + id +'"]>.popup').focus();
}

function layerPopupClose(id){
	// if(focusTarget) {
	// 	focusTarget.focus();
	// }
	// $(".layer-popup-box[data-popup="+id+"]").hide();
	
	$(".layerpopup[data-popup="+id+"]").find('.popup').removeClass('fadeInDown').addClass('fadeInUp');
	$(".layerpopup[data-popup="+id+"]").fadeOut(300, function(){
		$(".layerpopup[data-popup="+id+"]").find('.popup').removeClass('fadeInUp');
	});
}
