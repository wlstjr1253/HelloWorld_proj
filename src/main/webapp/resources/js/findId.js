
$(document).ready(function(){
	//email 제출 되었을 때
	$('#findIdForm').submit(function(event){
		//email 입력 안했을 때 
		if ($('#user_email').val()=='') {
			alert('이메일을 입력하세요');
			$('#user_email').focus();
			return false;
		}
		
		//이메일 입력 한 경우
		$.ajax({
			type:'post',
			data:{user_email:$('#user_email').val()},
			url:'findId.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){

				//해당 이메일로 회원정보가 없는 경우
				if (data.result=='emailNull') {
					alert('가입된 회원이 아닙니다');
				//이메일로 회원 정보를 가져오는 경우
				}else if(data.result=='emailNotNull'){
					var output ='<label>아이디는 '+data.user_id+' 입니다</label>';
				//문서 객체에 추가
				$('#output').append(output);
				}else{
					alert('Email 체크 오류');
				}
			},
			error:function(){
				alert('Email 확인 중 네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
});
