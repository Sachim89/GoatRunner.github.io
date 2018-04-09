$(document).ready(function() {

	// Get the values from backend and display in HTML
	$('#StudentID').val(localStorage.getItem("StudentID"));
	$('#name').val(localStorage.getItem("Name"));
	$('#email').val(localStorage.getItem("Email"));
	$('#password').val(localStorage.getItem("password"));
	$("#address").val(localStorage.getItem("Address"));
	$("#phone_number").val(localStorage.getItem("Phone"));
	$("#fav_location").val(localStorage.getItem("Fav"));
	$("#questions").val(localStorage.getItem("Security"));
	$("#answer").val(localStorage.getItem("Answer"));

	// Check for new values and update
	$("#UpdateProfile").click(function() {
		validate();
	});

	// Logout functionality
	$("#logout").click(function() {
		logout();
	});

	// Back button
	$('#back').click(function() {
		parent.history.back();
		return false;
	});
});

// Logout functionality
function logout() {
	if (confirm("Do you want to logout?")) {
		var id = localStorage.getItem("#StudentID");

		$.ajax({
			url : "http://localhost:8080/GoatRunner/application/user/logout?userId="
					+ id,
			type : "POST",
			data : JSON.stringify(j),
			contentType : "application/json",
			success : function(resultData) {
				window.location.href = "http://localhost:8080/GoatRunner/ConfirmedBooking.html"
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
		window.location.href = "http://localhost:8080/GoatRunner/HomePage.html"
	}
	// localStorage.removeItem("StudentID");
}

function validate() {
	var isValid = true;
	var id = document.getElementById("StudentID").value;

	var password = document.getElementById("password").value;
	console.log("Data is: " + id + password);

	// Check the Password and Student ID
	$('input').filter('[required]').each(function() {
		if ($(this).val() == "") {
			$('input').css("border", "2px solid red");
			$('input').css("box-shadow", "0 0 3px red");
			$('select').css("border", "2px solid red");
			$('select').css("box-shadow", "0 0 3px red");
			isValid = false;
			return false;
		}
	});

	if (password != "" || id != "") {
		if (id.length < 9 && password.length < 6) {
			alert("Student ID should be 9 digits and Password should be minimum 6 characters");
		} else if (password.length < 6) {
			alert("Password should be minimum 6 characters");
		} else if (id.length < 9) {
			alert("Student ID should be 9 digits");
		} else if (id.length > 9) {
			alert("Student ID should be 9 digits");
		}
	}

	if (!isValid) {
		alert("Some fields are missing");
	}

	// Send the valid data to Service layer
	if (isValid) {

		var studID = document.getElementById("StudentID").value;
		var name = document.getElementById("name").value;
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var address = document.getElementById("address").value;
		var phone = document.getElementById("phone_number").value;
		var fav_location = document.getElementById("fav_location").value;
		var quest;
		/*document.getElementById("questions").addEventListener("change",
				function() {
					quest = this.value;
				});
		var answer = document.getElementById("answer").value;*/

		var j = {
			"name" : name,
			"student_id" : studID,
			"password" : password,
			"phone_number" : phone,
			"address" : address,
			"favourite_location" : fav_location,
			"email_id" : email
			/*"security_question" : quest,
			"answer" : answer*/
		};
		$.ajax({
			url : "http://localhost:8080/GoatRunner/application/update/update_details",
			type : "POST",
			data : JSON.stringify(j),
			contentType : "application/json",
			success : function(resultData) {
				var data = JSON.parse(resultData);

				$('#StudentID').val(data.student_id);
				$('#name').val(data.name);
				$('#email').val(data.email_id);
				$('#password').val(data.password);
				$("#address").val(data.address);
				$("#phone_number").val(data.phone_number);
				$("#fav_location").val(data.favourite_location);
			//	$("#questions").val(data.security_question);
				// $("#answer").val(data.answer);
				window.location.href = "http://localhost:8080/GoatRunner/LoginPage.html";
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
