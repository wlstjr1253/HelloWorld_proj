$(function(){
	$('#page_4').click();
	
	var fr_rsrv_seat_type = $('#fr_rsrv_seat_type').val();//좌석 등급
	var fsi_fir_pc = $('#fsi_fir_pc').val();//퍼스트 금액
	var fsi_bus_pc = $('#fsi_bus_pc').val();//비즈니스 금액
	var fsi_eco_pc = $('#fsi_eco_pc').val();//이코노미 금액
	var fsi_fir_mil = $('#fsi_fir_mil').val();//퍼스트 마일리지
	var fsi_bus_mil = $('#fsi_bus_mil').val();//비즈니스 마일리지
	var fsi_eco_mil = $('#fsi_eco_mil').val();//이코노미 마일리지
	var adult = $('#fr_adult_pp').val();//성인 수
	var kid = $('#fr_kid_pp').val();//어린이 수
	var fr_submit = false;
	fr_seat_money_1();
	fr_seat_money_2();
	fr_seat_money_3();
	fr_seat_money_4();
	fr_seat_mil();

	function fr_seat_money_1(){	
		var type;
		
		if(fr_rsrv_seat_type == 'FIR'){
			type = fsi_fir_pc;
			$('#fr_seat_money_1').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'BUS'){
			type = fsi_bus_pc;
			$('#fr_seat_money_1').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'ECO'){
			type = fsi_eco_pc;
			$('#fr_seat_money_1').val(type +' 원');
			fr_submit = true;
		}	
	}
	function fr_seat_money_2(){	
		var type;
		
		if(fr_rsrv_seat_type == 'FIR'){
			type = fsi_fir_pc * adult;
			$('#fr_seat_money_2').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'BUS'){
			type = fsi_bus_pc * adult;
			$('#fr_seat_money_2').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'ECO'){
			type = fsi_eco_pc * adult;
			$('#fr_seat_money_2').val(type +' 원');
			fr_submit = true;
		}	
	}
	function fr_seat_money_3(){	
		var type;
		
		if(fr_rsrv_seat_type == 'FIR'){
			type = fsi_fir_pc;
			$('#fr_seat_money_3').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'BUS'){
			type = fsi_bus_pc;
			$('#fr_seat_money_3').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'ECO'){
			type = fsi_eco_pc;
			$('#fr_seat_money_3').val(type +' 원');
			fr_submit = true;
		}	
	}
	function fr_seat_money_4(){	
		var type;
		
		if(fr_rsrv_seat_type == 'FIR'){
			type = fsi_fir_pc * kid;
			$('#fr_seat_money_4').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'BUS'){
			type = fsi_bus_pc * kid;
			$('#fr_seat_money_4').val(type +' 원');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'ECO'){
			type = fsi_eco_pc * kid;
			$('#fr_seat_money_4').val(type +' 원');
			fr_submit = true;
		}	
	}
	function fr_seat_mil(){	
		var type;
		
		if(fr_rsrv_seat_type == 'FIR'){
			type = (fsi_fir_mil * adult + fsi_fir_mil * kid);
			$('#fr_seat_mil').val(type +' 점');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'BUS'){
			type = (fsi_bus_mil * adult + fsi_bus_mil * kid);
			$('#fr_seat_mil').val(type +' 점');
			fr_submit = true;		
		}else if(fr_rsrv_seat_type == 'ECO'){
			type = (fsi_eco_mil * adult + fsi_eco_mil * kid);
			$('#fr_seat_mil').val(type +' 점');
			fr_submit = true;
		}	
	}
});