const socket = new WebSocket("ws://localhost:8080/TW_Esercizio2_26012022/actions");
var matrix = []
var result = []

for ( let i = 0; i < 3; i++ ) {
	matrix.push([-1,-1,-1])
}

function send(data) {
	var json = JSON.stringify(data);

	socket.send(json);
}


socket.onmessage = (event) => {
	var message = JSON.parse(event.data);
	matrix = message
	
	for ( let i = 1; i < 4; i++ ) {
		for ( let j = 1; j < 4; j++ ) {
			if ( matrix[i - 1][j - 1] != 0 ) {
				document.querySelector("input[row='" + i + "'][col='" + j +"']").value = matrix[i-1][j-1];
			}	
		}
	}
}

function checkMagicSquare() {
	sendToServlet(matrix, "Righe")
	sendToServlet(matrix, "ColonneDiagonali")
}

function sendToServlet(matrix, url) {
	var argument = "matrix=" + JSON.stringify(matrix)
	
	fetch(url, {
		method: "POST",
		headers: {
			'Content-Type' : 'application/x-www-form-urlencoded'
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
	if ( data ) {
		result.push(data)
		
		if ( result.length == 2 ) {
			if ( result[0].ok && result[1].ok ) {
				var elem = document.querySelector("#result")
				elem.innerHTML = "IL quadrato è magico somma : " + result[0].number
			} else {
				var elem = document.querySelector("#result")
				elem.innerHTML = "IL quadrato non è magico"
			}
		}
	} else {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	}
}

function update(elem) {
	var number = parseInt(elem.value)
	var row = parseInt(elem.attributes.row.value) - 1
	var col = parseInt(elem.attributes.col.value) - 1

	if (isNaN(number)) {
		alert("non è un numero");
		return;
	}
	
	if ( matrix[row][col] != 0 ) {
		return;
	}
	
	for ( const mRow of matrix ) {
		for ( const value of mRow) {
			if ( number == value ) {
				return;
			}
		}
	}

	var operationReq = {};
	operationReq.matrix = matrix;
	operationReq.row = row;
	operationReq.col = col;
	operationReq.number = number;

	send(operationReq);

}
