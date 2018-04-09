$(document).ready(function() {
	$("#submit").click(function() {
		validate();
	});
});

// Function to validate the input data
function validate() {
	var id = $("#userid").val();
	var password = $("#password").val();
	localStorage.setItem("userid", id);
	localStorage.setItem("driverPassword", password);
	$('#loadingmessage').show();
	// Check the Password and Student ID
	if (id == "" && password == "") {
		$('input[type="number"]').css("border", "2px solid red");
		$('input[type="number"]').css("box-shadow", "0 0 3px red");
		$('input[type="password"]').css("border", "2px solid red");
		$('input[type="password"]').css("box-shadow", "0 0 3px red")
	}
	if (password == "" && id != "") {
		$('input[type="number"]').css("border", "");
		$('input[type="number"]').css("box-shadow", "");
		$('input[type="password"]').css("border", "2px solid red");
		$('input[type="password"]').css("box-shadow", "0 0 3px red");
	}
	if (password != "" && id == "") {
		$('input[type="password"]').css("border", "");
		$('input[type="password"]').css("box-shadow", "");
		$('input[type="number"]').css("border", "2px solid red");
		$('input[type="number"]').css("box-shadow", "0 0 3px red");
	}
	if (password != "" || id != "") {
		if (id.length < 3 && password.length < 3) {
			alert("Driver ID should be 9 digits and Password should be minimum 3 characters");
		} else if (password.length < 3) {
			alert("Password should be minimum 6 characters");
		} else if (id.length < 3) {
			alert("Driver ID should be 3 digits");
		} else if (id.length > 3) {
			alert("Driver ID should be 3 digits");
		}
	}

	// Send the valid data to Service layer
	if (password.length >= 3 && id.length == 3) {
		var driverId = parseInt(document.getElementById("userid").value);
		var password = document.getElementById("password").value;
		localStorage.setItem("driverId", driverId);
		localStorage.setItem("driverPassword", password);
		
		$.ajax({
			url : "http://localhost:8080/GoatRunner/application/driver/login_driver?driver_Id="
					+ driverId + "&password=" + password,
			type : "GET",
			contentType : "application/json",
			success : function(driver) {
				var data = JSON.parse(driver);
				localStorage.setItem("Name", data.name);
				window.location.href = "http://localhost:8080/GoatRunner/Rides.html"
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
}
