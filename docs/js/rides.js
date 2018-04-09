$(document).ready(function() {
	// Function call to get the cab number assigned for the
	// driver
	$("#getcab").click(function() {
		getCab();
	});
	var driverId = localStorage.getItem("userid");
	console.log(driverId);

	// Function to determine the location of the driver
	var Lat;
	var Lon;
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(p) {
			Lat = p.coords.latitude;
			Lon = p.coords.longitude;
		});
	}

	// Performing the Polling request in order to display the
	// uptodate results of the rides

	var ajax_call = function() {
		$.ajax({
			url : "http://localhost:8080/GoatRunner/application/driver/get_rides?driver_Id="
					+ driverId
					+ "&latitude="
					+ Lat
					+ "&longitude=" + Lon,
			type : "POST",
			contentType : "application/json",
			success : function(result) {
				$("#ridesTable tbody").remove();
				// $('#ridesTable').show();
				var data = JSON.parse(result);
				
				// console.log(data);
				/*
				 * console.log("Data is : " +
				 * data[0].bookingid);
				 */
				var tr;
				for (var i = 0; i < data.length; i++) {
					var counter = data[i];

					tr = $('<tr/>');
					tr.append("<td>"+ counter.bookingId+ "</td>");
					tr.append("<td>"+ counter.studentId+ "</td>");
					tr.append("<td>" + counter.source+ "</td>");
					tr.append("<td>"+ counter.destination+ "</td>");
					tr.append('<td><select id="mySelect"> <option value="0">In Progress<option value="1">Completed<option value="1">Cancelled</select></td></tr>');
					$('table').append(tr);

					$("#mySelect").on('change',function() {
						getStatus(counter.bookingId);
					});
				}
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
	}

	var interval = 5000;// 1000 * 60 * 1;
	setInterval(ajax_call, interval);
});

// Function to display the value in the drop down box
function myFunction() {
	var x = document.getElementById("mySelect").value;
	document.getElementById("demo").innerHTML = "You selected: " + x;
}

// Function call to get the Cab number
function getCab() {
	var driverId = localStorage.getItem("userid");
	var password = localStorage.getItem("driverPassword");
	console.log(password);
	$.ajax({
		url : "http://localhost:8080/GoatRunner/application/driver/get_cab?driver_Id="
				+ driverId + "&password=" + password,
		type : "POST",
		contentType : "application/json",
		success : function(result) {
			var data = JSON.parse(result);
			// console.log(data.driverid);
			// localStorage.setItem("bookingId",resultData.bookingId);
			localStorage.setItem("cabno", data.cabno);

			// console.log(localStorage.getItem("cabno"));
			document.getElementById("Cabid").value = localStorage
					.getItem("cabno");
			// console.log("success");
		},
		error : function(data) {
			if (data == 400) {
				alert("Oops!! Somethings went wrong.. Please try after sometime");
			}
		}
	});
}

// Function call to get the status of the cab
function getStatus(bookingId) {
	console.log("getStatus");
	console.log(bookingId);
	// var bookingId = localStorage.getItem("bookingId");
	$.ajax({
		url : "http://localhost:8080/GoatRunner/application/ride/complete?bookingId="
				+ bookingId,
		type : "POST",
		contentType : "application/json",
		success : function(result) {
			console.log("success");

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
}