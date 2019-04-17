$(function(){
	$('#next_1').on('click', function(){
		$('#page_2').click();
	});
	$('#next_2').on('click', function(){
		$('#page_3').click();
	});
	$('.before_2').on('click', function(){
		$('#page_1').click();
	});
	$('.before_3').on('click', function(){
		$('#page_2').click();
	});
	
	//유효성 체크
	$('#rsrv_form').on('button', function() {
		
		var cp_ym = $('#cp_year_month');
		
		if($('#fr_fnm').val() == ''){
			alert('예약자명(성)을 입력해주세요.'); 
			return false;
		}
		
		if($('#fr_nm').val() == ''){
			alert('예약자명(이름)을 입력해주세요.');
			return false;
		}
		
		if($('#fr_passport').val() == ''){
			alert('여권번호를 입력해주세요.');
			return false;
		}
		
		if($('#fr_email').val() == ''){
			alert('예약자 이메일을 입력해주세요.');
			return false;
		}
		
		if($('#fr_phone').val() == ''){
			alert('예약자 연락처를 입력해주세요.');
			return false;
		}
		
		if($('#cp_num').val() == ''){
			alert('카드 번호를 입력해주세요.');
			return false;
		}
		
		if($('#cp_pin_num').val() == ''){
			alert('카드 핀 넘버를 입력해주세요.');
			return false;
		}
		
		if(cp_ym.val() == ''){
			alert('카드 연월(MM/YY)을 입력해주세요.');
			return false;
		}
		
		if(cp_ym.val().length != 5 || cp_ym.val().substr(2,1) != '/'){
			alert('카드 연월을 MM/YY 형태로 입력해주세요.');
			return false;
		}
	});
});