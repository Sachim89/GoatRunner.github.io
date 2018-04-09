$(document).ready(function() {
	// Validates the form before sending the requests
	$("#request").click(function() {
		validate();
	});

/*	// Informs the user to find the location in map
	$("#toLocate").hover(function() {
		$(this).html($('<span style="color:red;">Press enter in text box to locate in Map</span>'));
	});

	// Displays the rules for password when mouse pointer is over ?
	$("#fromLocate").hover(function() {
		$(this).html($('<span style="color:red;">Press enter check the route</span>'));
	});*/

	// Logout
	$("#logout").click(function() {
		logout();
	});
});

// Logout functionality
function logout() {
	if (confirm("Do you want to logout?")) {
		var id = localStorage.getItem("StudentID");
		$
				.ajax({
					url : "http://localhost:8080/GoatRunner/application/user/logout?userId="
							+ id,
					type : "GET",
					contentType : "application/json",
					success : function(resultData) {
						window.location.href = "http://localhost:8080/GoatRunner/HomePage.html"
					},
					error : function(data) {
						if (code == 400) {
							alert("Oops!! Somethings went wrong.. Please try after sometime");
						}
						if (code == 500) {
							alert("Oops!! Somethings went wrong.. Please try after sometime");
						}
					}
				});
	} else {
		window.location.href = "http://localhost:8080/GoatRunner/BookingPage.html"
	}
	localStorage.removeItem("studentId");
}

// Validate the given input data
function validate() {
	var isValid = true;
	var to = $("#toLocate").val();
	var from = $("#fromLocate").val();
	var num = $("#numPass").val();

	// Check the To and From address
	$('input').filter('[required]').each(function() {
		if ($(this).val() == "") {
			$('input').css("border", "2px solid red");
			$('input').css("box-shadow", "0 0 3px red");
			isValid = false;
			return false;
		}
	});

	if (!isValid) {
		alert("Some fields are missing");
	}

	// Send the valid data to Service layer
	if (isValid) {
		// Request the cab
		if (confirm("Confirm Booking?")) {
			// Store details in local storage for further processing
			var to = document.getElementById("toLocate").value;
			var from = document.getElementById("fromLocate").value;
			var numPass = document.getElementById("numPass").value;
			localStorage.setItem("toLocate", to);
			localStorage.setItem("fromLocate", from);
			localStorage.setItem("numPass", numPass);

			// Function call to send the required data
			callDistance();

			// Send the User ID to service layer
			/*
			 * var distance; var duration; var x =
			 * localStorage.getItem("StudentID"); var j = { "student_id" : x,
			 * "toAddress" : to, "fromAddress" : from, "numberPass" : numPass,
			 * "distance" : distance, "duration" : duration }
			 * 
			 * $.ajax({ url :
			 * "http://localhost:8080/GoatRunner/application/user/booking", type :
			 * "POST", data : JSON.stringify(j), contentType :
			 * "application/json", success : function(resultData) {
			 * window.location.href =
			 * "http://localhost:8080/GoatRunner/ConfirmedBooking.html" }, error :
			 * function(data) { if (code == 400) { alert("Oops!! Somethings went
			 * wrong.. Please try after sometime"); } if (code == 500) {
			 * alert("Oops!! Somethings went wrong.. Please try after
			 * sometime"); } } });
			 */
		} else {
			alert("You have cancelled the booking");

			var x = localStorage.getItem("StudentID");
			var to = document.getElementById("toLocate").value;
			var from = document.getElementById("fromLocate").value;
			var numPass = document.getElementById("numPass").value;

			var j = {
				"student_id" : x,
				"toAddress" : to,
				"fromAddress" : from,
				"numberPass" : numPass,
				"cancel" : true
			}

			$.ajax({
				url : "http://localhost:8080/GoatRunner/application/user/bookingCancel",
				type : "GET",
				data : JSON.stringify(j),
				contentType : "application/json",
				success : function(resultData) {
					window.location.href = "http://localhost:8080/GoatRunner/BookingPage.html"
				},
				error : function(data) {
					if (code == 400) {
						alert("Oops!! Somethings went wrong.. Please try after sometime");
					}
					if (code == 500) {
						alert("Oops!! Somethings went wrong.. Please try after sometime");
					}
				}
			});
		}
	}
}

// Get the current location id "Use current location" is requested
function Locationfunction() {
	if (navigator.geolocation) {
		navigator.geolocation
				.getCurrentPosition(function(p) {
					var LatLng = new google.maps.LatLng(p.coords.latitude,
							p.coords.longitude);
					var mapOptions = {
						center : LatLng,
						zoom : 13,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};
					var geocoder = geocoder = new google.maps.Geocoder();
					geocoder
							.geocode(
									{
										'latLng' : LatLng
									},
									function(results, status) {
										if (status == google.maps.GeocoderStatus.OK) {
											if (results[0]) {
												document
														.getElementById("fromLocate").value = results[0].formatted_address;
											}
										}
									});
					var map = new google.maps.Map(document
							.getElementById("map1"), mapOptions);
					var marker = new google.maps.Marker({
						position : LatLng,
						map : map,
					});

					google.maps.event.addListener(marker, "click", function(e) {
						var infoWindow = new google.maps.InfoWindow();
						infoWindow.open(map, marker);
					});
				});
	} else {
		alert('Geo Location feature is not supported in this browser.');
	}
}

// Check Route option shows the routes
function direction() {
	var directionsService = new google.maps.DirectionsService();
	var directionsDisplay = new google.maps.DirectionsRenderer();

	var map = new google.maps.Map(document.getElementById('map1'), {
		zoom : 7,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	directionsDisplay.setMap(map);
	directionsDisplay.setPanel(document.getElementById('panel'));

	var request = {
		origin : document.getElementById("fromLocate").value,
		destination : document.getElementById("toLocate").value,
		travelMode : google.maps.DirectionsTravelMode.DRIVING
	};

	directionsService.route(request, function(response, status) {
		if (status == google.maps.DirectionsStatus.OK) {
			directionsDisplay.setDirections(response);
		}
	});
}

// On Enter key get To-Address and point in the Map
function searchTo() {
	var lat_to, lng_to;
	if (event.keyCode == 13) {
		var to = document.getElementById("toLocate").value;
		var geocoder = geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'address' : to
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				lat_to = results[0].geometry.location.lat();
				lng_to = results[0].geometry.location.lng();
			}

			var myLatLng = {
				lat : lat_to,
				lng : lng_to
			};
			var map = new google.maps.Map(document.getElementById("map1"), {
				zoom : 13,
				center : myLatLng
			});
			var markerTo = new google.maps.Marker({
				position : myLatLng,
				map : map,
			});

			google.maps.event.addListener(marker, "click", function(e) {
				var infoWindow = new google.maps.InfoWindow();
				infoWindow.open(map, markerTo);
			});
		});
	}
}

// On Enter key get From-Address and point in the Map
function searchFrom() {
	var lat_from, lng_from;
	if (event.keyCode == 13) {
		var from = document.getElementById("fromLocate").value;
		var geocoder = geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'address' : from
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				lat_from = results[0].geometry.location.lat();
				lng_from = results[0].geometry.location.lng();
			}
			var myLatLng = {
				lat : lat_from,
				lng : lng_from
			};
			var map = new google.maps.Map(document.getElementById("map1"), {
				zoom : 13,
				center : myLatLng
			});
			var markerFrom = new google.maps.Marker({
				position : myLatLng,
				map : map,
			});
			google.maps.event.addListener(marker, "click", function(e) {
				var infoWindow = new google.maps.InfoWindow();
				infoWindow.open(map, markerFrom);
			});
		});

		var to = document.getElementById("toLocate").value;
		var from = document.getElementById("fromLocate").value;
		var numPass = document.getElementById("numPass").value;

		if (to != "" && from != "") {
			document.getElementById("direction").innerHTML = '<a href="#" onclick="direction()">Check Route</a>';
		} else {
			alert("Some fields missing");
		}
	}
}
// Function to send the service layer the distance and duration between the
// origin and destination
function callDistance() {

	var lat_from, lng_from;
	var from = document.getElementById("fromLocate").value;
	var geocoder = geocoder = new google.maps.Geocoder();
	geocoder
			.geocode(
					{
						'address' : from
					},
					function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							lat_from = results[0].geometry.location.lat();
							lng_from = results[0].geometry.location.lng();
						}
						var lat_to, lng_to;
						var to = document.getElementById("toLocate").value;
						var geocoder = geocoder = new google.maps.Geocoder();
						geocoder
								.geocode(
										{
											'address' : to
										},
										function(results, status) {
											if (status == google.maps.GeocoderStatus.OK) {
												lat_to = results[0].geometry.location
														.lat();
												lng_to = results[0].geometry.location
														.lng();
											}
											var origin = new google.maps.LatLng(
													lat_to, lng_to);
											var destination = new google.maps.LatLng(
													lat_from, lng_from);

											var service = new google.maps.DistanceMatrixService();
											service
													.getDistanceMatrix(
															{
																origins : [ origin ],
																destinations : [ destination ],
																travelMode : google.maps.TravelMode.DRIVING,
																unitSystem : google.maps.UnitSystem.METRIC,
																avoidHighways : false,
																avoidTolls : false
															},
															function callback(
																	response,
																	status) {
																if (status != google.maps.DistanceMatrixStatus.OK) {
																	alert('Error was: '
																			+ status);
																} else {
																	var origins = response.originAddresses;
																	var destinations = response.destinationAddresses;
																	var distance = response.rows[0].elements[0].distance.text;
																	var duration = response.rows[0].elements[0].duration.text;
																	var x = localStorage
																			.getItem("StudentID");
																	var numPass = document
																			.getElementById("numPass").value;
																	console
																			.log(x);
																	var j = {
																		"studentId" : x,
																		"noOfPassengers" : numPass,
																		"source" : origins,
																		"destination" : destinations,
																		"sourceLatitude" : lat_from,
																		"sourceLongitude" : lng_from,
																		"destinationLatitude" : lat_to,
																		"destinationLongitude" : lng_to
																	}
																	$(
																			'#loadingmessage')
																			.show();

																	// Send the
																	// results
																	// and store
																	// the
																	// required
																	// data in
																	// local
																	// storage
																	// for
																	// further
																	// process
																	$
																			.ajax({
																				url : "http://localhost:8080/GoatRunner/application/ride/book",
																				type : "POST",
																				data : JSON
																						.stringify(j),
																				contentType : "application/json",
																				success : function(
																						resultData) {
																					localStorage
																							.setItem(
																									"bookingId",
																									resultData.bookingId);
																					localStorage
																							.setItem(
																									"cabId",
																									resultData.cabId);
																					localStorage
																							.setItem(
																									"driverId",
																									resultData.driverId);
																					localStorage
																							.setItem(
																									"driverName",
																									resultData.driverName);
																					localStorage
																							.setItem(
																									"estimatedTime",
																									resultData.estimatedTime);
																					localStorage
																							.setItem(
																									"source",
																									resultData.source);
																					localStorage
																							.setItem(
																									"destination",
																									resultData.destination);
																					window.location.href = "http://localhost:8080/GoatRunner/ConfirmedBooking.html"
																				}
																			});
																}
															});
										});
					});
}
