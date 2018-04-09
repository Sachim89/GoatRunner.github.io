$(document).ready(function() {
		$("#loginButton").click(function() {
			validate();
		});

	/*	// Displays the rules for Student ID when mouse pointer is over ?
		$("#idrules").hover(function() {
			$(this).html($('<span style="color:red;">Student ID should be 9 digits</span>'))
		});

		// Displays the rules for password when mouse pointer is over ?
		$("#passrules").hover(function() {
			$(this).html($('<span style="color:red;">Password should be minimum 6 characters</span>'))
		});*/

	});

function validate() {
	var id = $("#StudentID").val();
	var password = $("#password").val();
	localStorage.setItem("StudentID", id);

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

	// Send the valid data to Service layer
	if (password.length >= 6 && id.length == 9) {
		var studentId = parseInt(document.getElementById("StudentID").value);
		var password = document.getElementById("password").value;
		localStorage.setItem("StudentID", studentId);
		$('#loadingmessage').show();

		$.ajax({
			url : "http://localhost:8080/GoatRunner/application/user/login?userId="
					+ studentId + "&password=" + password,
			type : "GET",
			contentType : "application/json",
			success : function(resultData) {
				var data = JSON.parse(resultData);
				console.log(data.student_id);

				localStorage.setItem("Name", data.name);
				localStorage.setItem("studentId", data.student_id);
				localStorage.setItem("password", data.password);
				localStorage.setItem("Phone", data.phone_number);
				localStorage.setItem("Address", data.address);
				localStorage.setItem("Fav", data.favourite_location);
				localStorage.setItem("Email", data.email_id);
				localStorage
						.setItem("Security", data.security_question);
				localStorage.setItem("Answer", data.answer);

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
	}
}
