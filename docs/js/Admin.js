$(document).ready(function() {
	// On click of the cab number, get the details
	$("#cab1").click(function() {
		fetchCab(11);
	});

	$("#cab2").click(function() {
		fetchCab(12);
	});

	$("#cab3").click(function() {
		fetchCab(3);
	});

	$("#cab4").click(function() {
		fetchCab(4);
	});

	$("#cab5").click(function() {
		fetchCab(5);
	});

	$("#cab6").click(function() {
		fetchCab(6);
	});
});

// Function call to the backend to retrive the details associated with the
// particular cab
function fetchCab(cabNo) {
	console.log("Entered");

	$.ajax({
		url : "http://localhost:8080/GoatRunner/application/admin/view?cabNo="
				+ cabNo,
		type : "GET",
		contentType : "application/json",
		success : function(result) {
			$("#rides tr").remove();
			var data = JSON.parse(result);
			var tr;
			for (var i = 0; i < data.length; i++) {
				var counter = data[i];
				tr = $('<tr/>');
				tr.append("<td>" + counter.bookingId + "</td>");
				tr.append("<td>" + counter.studentId + "</td>");
				tr.append("<td>" + counter.source + "</td>");
				tr.append("<td>" + counter.destination + "</td>");
				tr.append("<td>" + counter.driverId + "</td>");
				$('table').append(tr);
			}
		},
		error : function(data) {
			console.log("Error");
		}
	});
}
