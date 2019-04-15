var map;
var service;
var infowindow;

function initMap() {
	infowindow = new google.maps.InfoWindow();

	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 15
	});

	var request = {
			query : $('#addr').val(),
			fields : [ 'name', 'geometry' ],
	};

	service = new google.maps.places.PlacesService(map);

	service.findPlaceFromQuery(request, function(results, status) {
		if (status === google.maps.places.PlacesServiceStatus.OK) {
			for (var i = 0; i < results.length; i++) {
				createMarker(results[i]);
			}

			map.setCenter(results[0].geometry.location);
		}
	});
}

function createMarker(place) {
	var marker = new google.maps.Marker({
		map : map,
		position : place.geometry.location
	});

	google.maps.event.addListener(marker, 'click', function() {
		infowindow.setContent(place.name);
		infowindow.open(map, this);
	});
}
