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

//장바구니 삭제
$(document).on('click','.delete-btn',function(){
	//댓글 번호
	var re_num = $(this).attr('data-num');
	//작성자 아이디
	var id = $(this).attr('data-id');
	
	$.ajax({
		type:'post',
		url:'deleteReply.do',
		data:{re_num:re_num,id:id},
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(data){
			if(data.result == 'logout'){
				alert('로그인해야 삭제할 수 있습니다.');
			}else if(data.result == 'success'){
				alert('삭제 완료!');
				//새로 목록 호출
				selectData(1,$('#num').val());
			}else if(data.result == 'wrongAccess'){
				alert('타인의 글을 삭제할 수 없습니다.');
			}else{
				alert('댓글 삭제시 오류 발생');
			}
		},
		error:function(){
			alert('댓글 삭제시 네트워크 오류');
		}
	});
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