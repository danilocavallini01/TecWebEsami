
var results = [];

function sendRedirect() {
	var redirectNum = document.querySelector("#redirect")

	sendToServlet(redirectNum)
}


function sendToServlet(redirectNum) {

	var argument = "redirectNum=" + redirectNum;

	fetch("MatrixDiff", {
		method: "POST",
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: argument,
	}
	)
		.then(response => response.json())
		.then(data => {
			checkResult(data)
		})
		.catch(error => {
			var elem = document.querySelector("#result")
			elem.innerHTML = "Errore durante il recupero dei dati dal server"
		})
}

function checkResult(data) {
	results.p

	if (results.length >= 4) {
		var elem = document.querySelector("#result")
		elem.innerHTML = matrix.join(",")
	}
}