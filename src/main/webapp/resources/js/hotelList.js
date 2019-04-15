$(function(){
	
	function changeSearch(i) {
        let $bookingBox = $('.booking-box');
    	let $flight = $('.button', $bookingBox).eq(0);
    	let $hotel = $('.button', $bookingBox).eq(1);
        
        let $flightForm = $('.flight-srch', $bookingBox);
        let $hotelForm = $('.hotel-srch', $bookingBox);

        $flight.on('click', function(e){
            e.preventDefault();
            $(this).addClass('on');
            $hotel.removeClass('on');
            $flightForm.show();
            $hotelForm.hide();
        });

        $hotel.on('click', function(e){
            e.preventDefault();
            $(this).addClass('on');
            $flight.removeClass('on');
            $flightForm.hide();
            $hotelForm.show();
        });
    	
    	if(i == 0){
    		$flight.click();
    	} else {
    		$hotel.click();
    	}
    }
	
	changeSearch(1);
});