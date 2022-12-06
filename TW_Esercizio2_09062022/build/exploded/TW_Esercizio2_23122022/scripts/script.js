
var matrixs = []
var num = 0
var maxMatrix = -1;
var results = []

function checkMatrix() {
	if ( maxMatrix == - 1 ) {
		maxMatrix = parseInt(document.querySelector("#num").value)
	}
	
	var matrix = document.querySelector("#content").value.split(",")
	console.log(matrix)
	if ( matrix.length >= 64 ) {
		matrixs[num++] = matrix;
		var label = document.querySelector("#text")
		label.innerHTML = "Contenuto della " + (num + 1) + " matrice (separato da ,)"
		document.querySelector("#content").value = ""
	}
	
	if ( num == maxMatrix ) {
		for ( var ajaxReq = 0; ajaxReq < 4; ajaxReq++ ) {
			var parallelMatrix = []

			for ( var i = 0; i < matrixs.length; i++ ) {
				parallelMatrix[i] = []
				for ( var j = 0; j < 16; j++ ) {
					parallelMatrix[i].push(parseInt(matrixs[i][j + ajaxReq * 16]))
				}
			}
			sendToServlet(parallelMatrix);
		}
		
		maxMatrix = -1;
	}
}

function sendToServlet(matrix) {
	var argument = "matrixs=" + JSON.stringify(matrix)
	
	fetch("MatrixSum", {
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
	for ( const result of data ) {
		results.push(result)
	}

	if ( results.length >= 64 ) {
		var finalResult = ""
		for ( var i = 0; i < 64; i++) {
			if ( i % 8 == 0 ) {
				finalResult += results[i] + "<br>"
			} else {
				finalResult += results[i] + ","
			}
			console.log(finalResult)
		}

		matrixs = []
		results = []
		num = []
		var elem = document.querySelector("#result")
		elem.innerHTML = "Risultato : " + finalResult
	}
}