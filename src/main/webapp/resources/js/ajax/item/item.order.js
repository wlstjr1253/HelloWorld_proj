$('#ibh_request').keyup(function (e){
    var ibh_request = $(this).val();
    $('#counter').html("("+ibh_request.length+" / 최대 50자)");    //글자수 실시간 카운팅

    if (ibh_request.length > 50){
        alert("최대 50자까지 입력 가능합니다.");
        $(this).val(ibh_request.substring(0, 50));
        $('#counter').html("(50 / 최대 50자)");
    }
});

//체크박스 선택여부
$('#orderFormPart').on('submit', function() {
	if($("input:checkbox[name=checked_num]").is(":checked") == false) {
		 alert("주문하실 상품을 선택해주세요.");
         return false;
		}
});


/**
 * 상품 구매 ajax
 */
$(function(){
	$('.next_1').on('click', function() {
		$('#move_2').click();
	});
	
	$('.before_2').on('click', function() {
		$('#move_1').click();
	});
	
	$('#orderForm').on('submit', function() {
		
		var cp_ym = $('#cp_year_month');
		
		if($('#ibh_nm').val() == ''){
			alert('수령자 이름을 입력해주세요.');
			return false;
		}
		
		if($('#ibh_pay').val() == '0'){
			alert('결제방식을 입력해주세요.');
			return false;
		}
		
		if($('#ibh_phone').val() == ''){
			alert('연락 가능한 번호를 입력해주세요.');
			return false;
		}
		
		if($('#email').val() == ''){
			alert('이메일주소를 입력해주세요.');
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