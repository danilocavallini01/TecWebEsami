
var results = [];

function checkIfValid() {
	var matrixA = document.querySelector("#matrixA").value.split(",")
	var matrixB = document.querySelector("#matrixB").value.split(",")

	console.log(matrixA,matrixB)
	if (matrixA.length >= 4 && matrixB.length >= 4) {
		var inputs = document.querySelectorAll("input[type=submit]")
		for (var input of inputs) {
			input.classList.remove("hide")
		}
	}
}

function matrixDiff(isMultiThread = true) {

	if (isMultiThread) {
		var matrixA = document.querySelector("#matrixA").value.split(",")
		var matrixB = document.querySelector("#matrixB").value.split(",")

		for (var i = 0; i < 4; i++) {
			sendToServlet(isMultiThread, i, 1, 1, matrixA[i], matrixB[i])
		}
	} else {
		var matrixA = document.querySelector("#matrixA").value
		var matrixB = document.querySelector("#matrixB").value

		sendToServlet(isMultiThread, 0,2,2, matrixA, matrixB)
	}
}

function sendToServlet(isMultiThread, position,row,col, matrixA, matrixB) {

	var argument = "rows=" + row + "&cols=" + col + "&matrixA=" + matrixA + "&matrixB=" + matrixB

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
			if (isMultiThread) {
				checkResult(position, data)
			} else {
				var elem = document.querySelector("#result")
				elem.innerHTML = data
			}
		})
		.catch(error => {
			var elem = document.querySelector("#result")
			elem.innerHTML = "Errore durante il recupero dei dati dal server"
		})
}

function checkResult(position, data) {
	results[position] = data;

	if (results.length >= 4) {
		var matrix = [[results[0], results[1]], [results[2], results[3]]]
		var elem = document.querySelector("#result")
		elem.innerHTML = matrix.join(",")
	}
}