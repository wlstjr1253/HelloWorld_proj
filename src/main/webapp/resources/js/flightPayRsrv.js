$(function(){
	
	$('#fr_rsrv_seat_type').change(function(){
		var name = "";
		if($(this).val() == 'FIR') name = "First Class";
		if($(this).val() == 'BUS') name = "Business Class";
		if($(this).val() == 'ECO') name = "Economy Class";
		$('#seat_class_type').text(name);
		total_pc_fr();
	});
	
	var fsi_fir_pc = $('#fsi_fir_pc').val();//퍼스트 금액
	var fsi_bus_pc = $('#fsi_bus_pc').val();//비즈니스 금액
	var fsi_eco_pc = $('#fsi_eco_pc').val();//이코노미 금액
	var fr_submit = false;

	function total_pc_fr(){
		var total_pc;
		var adult = $('#fr_adult_pp').val();//성인 수
		var kid = $('#fr_kid_pp').val();//어린이 수
		var seat_type = $('#fr_rsrv_seat_type').val();//좌석 등급
		fr_submit = false;
		
		if(seat_type == 'FIR'){
			total_pc = (adult * fsi_fir_pc + kid * fsi_fir_pc);
			$('#total_pc_fr').val(total_pc + ' 원');
			$('#fr_total_pc').val(total_pc)
			fr_submit = true;
		}else if(seat_type == 'BUS'){
			total_pc = (adult * fsi_bus_pc + kid * fsi_bus_pc);
			$('#total_pc_fr').val(total_pc + ' 원');
			$('#fr_total_pc').val(total_pc)
			fr_submit = true;
		}else if(seat_type == 'ECO'){
			total_pc = (adult * fsi_eco_pc + kid * fsi_eco_pc);
			$('#total_pc_fr').val(total_pc + ' 원');
			$('#fr_total_pc').val(total_pc)
			fr_submit = true;
		}
		
	}
	
	$('.cal').on('change', function(){
		$('#total_pc_fr').val('0 원');
		$('#fr_total_pc').val(0);
		total_pc_fr();
		
		$('#total_pp_fr').val('0 명');
		total_pp_fr();
	});

	$('#rsrv_form').on('submit', function(){
		if(!fr_submit) {
			alert('예약 정보(인원)를 다시 입력해주세요.');
			return false;
		}
	});
	
	function total_pp_fr(){
		var total_pp;
		var adult = $('#fr_adult_pp').val();//성인 수
		var kid = $('#fr_kid_pp').val();//어린이 수

		total_pp = Number(adult) + Number(kid);
		$('#total_pp_fr').val(total_pp + ' 명');
	}
	
});