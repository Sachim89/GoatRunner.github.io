$(document).ready(function () {
	//Validate the form before sedning update request
	$("#UpdateButton").click(function(){
		validate();
	});
	
	//Displays the rules for Student ID when mouse pointer is over ?
	$("#idrules").hover(function(){
		$(this).html($('<span style="color:red;">Student ID should be 9 digits</span>'))
	});
	
	//Displays the rules for password when mouse pointer is over ?
	$("#passrules").hover(function(){
		$(this).html($('<span style="color:red;">Password should be minimum 6 characters</span>'))
	});

});

function validate()
{
	var id = $("#StudentID").val();
	var password = $("#password").val();
	if(id == "" && password == "")
	{
		$('input[type="number"]').css("border","2px solid red");
		$('input[type="number"]').css("box-shadow","0 0 3px red");
		$('input[type="password"]').css("border","2px solid red");
		$('input[type="password"]').css("box-shadow","0 0 3px red")
	}
	if(password == "" && id != "")
	{
		$('input[type="number"]').css("border","");
		$('input[type="number"]').css("box-shadow","");
		$('input[type="password"]').css("border","2px solid red");
		$('input[type="password"]').css("box-shadow","0 0 3px red");
	}
	if(password != "" && id == "")
	{
		$('input[type="password"]').css("border","");
		$('input[type="password"]').css("box-shadow","");
		$('input[type="number"]').css("border","2px solid red");
		$('input[type="number"]').css("box-shadow","0 0 3px red");
	}
	if(password !="" || id != ""){	
			if(id.length < 9 && password.length < 6){
				alert("Student ID should be 9 digits and Password should be minimum 6 characters");
			}
			else if(password.length < 6){
				alert("Password should be minimum 6 characters");
			}
			else if(id.length < 9){
				alert("Student ID should be 9 digits");
			}
			else if(id.length > 9){
				alert("Student ID should be 9 digits");
			}
	}
	if(password.length >= 6 && id.length == 9){
		var studentId = document.getElementById("StudentID").value;
		var Newpassword = document.getElementById("password").value;
		
		$.ajax({
			url : "http://localhost:8080/GoatRunner/application/update/update_password?userId="
					+ studentId + "&password=" + Newpassword,
			type : "POST",
			contentType :"application/json",
			success : function(resultData) {
				window.location.href = "http://localhost:8080/GoatRunner/LoginPage.html";
			},
			error: function(data){
				if(code == 400){
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
				if(code == 500){
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
			}
		});
		
	}
}

