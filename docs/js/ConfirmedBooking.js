$(document).ready(function() {
	var to;
	var from;

	console.log("Entered");

	// Get the the values from the local storage and display in
	// the user profile
	$('#bookingID').val(localStorage.getItem("bookingId"));
	$('#driverName').val(localStorage.getItem("driverName"));
	$('#driverNumber').val(localStorage.getItem("driverId"));
	$('#cabNumber').val(localStorage.getItem("cabId"));
	$("#time").val(localStorage.getItem("estimatedTime"));
	to = localStorage.getItem("source");
	from = localStorage.getItem("destination");
	console.log(to);
	console.log(from);

	// Logout functionality
	$("#logout").click(function() {
		logout();
	});

	// Details needed for processing
	var id = localStorage.getItem("StudentID");

	var bookid = localStorage.getItem("bookingId");
	console.log(bookid);
	// var numpass = localStorage.getItem("numPass");
	$('#loadingmessage').show();
	// Cancelling the request
	$("#cancel").click(function() {
	
		$.ajax({
			url : "http://localhost:8080/GoatRunner/application/ride/cancel?bookingId="
					+ bookid,
			type : "POST",
			contentType : "application/json",
			success : function(
					resultData) {
				window.location.href = "http://localhost:8080/GoatRunner/BookingPage.html"
			},
			error : function(data) {
				if (data == 400) {
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
				if (data == 500) {
					alert("Oops!! Somethings went wrong.. Please try after sometime");
						}
					}
				});
		});

	// var time = setTimeout(disable, 120000);
	// document.getElementById("log").innerHTML = "Logged in as:
	// "+ x + " ";

	/*
	 * function disable() { p =
	 * document.getElementById("count"); x =
	 * document.getElementById("cancel"); x.disabled = true;
	 * p.disabled = true; x.parentNode.removeChild(x);
	 * p.parentNode.removeChild(p); }
	 */
	// Funtionalities that uses Directions API to display the
	// route
	var directionsService = new google.maps.DirectionsService();
	var directionsDisplay = new google.maps.DirectionsRenderer();

	var map = new google.maps.Map(document
			.getElementById('map1'), {
		zoom : 7,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	directionsDisplay.setMap(map);
	directionsDisplay
			.setPanel(document.getElementById('panel'));

	var request = {
		origin : from,
		destination : to,
		travelMode : google.maps.DirectionsTravelMode.DRIVING
	}

	directionsService.route(request,
			function(response, status) {
				if (status == google.maps.DirectionsStatus.OK) {
					directionsDisplay.setDirections(response);
				}
			});
});

// Logout functionality
function logout() {
	if (confirm("Do you want to logout?")) {
		var id = localStorage.getItem("StudentID");

		$.ajax({
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
		window.location.href = "http://localhost:8080/GoatRunner/ConfirmedBooking.html"
	}
	localStorage.removeItem("StudentID");
}
