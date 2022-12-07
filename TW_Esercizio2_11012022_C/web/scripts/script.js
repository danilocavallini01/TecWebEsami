var result = []

function checkMatrix() {
	var matrix = document.querySelector("#content").value.split(",")
	
	let nums = []

	if ( matrix.length >= 16 ) {
		for ( const num of matrix ) {
			let parsed = parseInt(num)

			
			if ( isNaN(parsed) ) {
				return;
			}
			if ( nums.includes(parsed) ) {
				return;
			}

			nums.push(parsed)		
		}
		console.log(matrix)

		sendToServlet(matrix, "./righe.jsp")
		sendToServlet(matrix, "Colonne")
		sendToServlet(matrix, "./diagonale.jsp")
	}

	
}

function sendToServlet(matrix, url) {
	var argument = "matrixs=" + JSON.stringify(matrix)
	
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
	if ( data.ok ) {
		result.push(data) 

		if ( result.length >= 3 ) {

			if ( result[0].sum == result[1].sum && result[1].sum == result[2].sum ) {
				var elem = document.querySelector("#result")
				elem.innerHTML = "Il quadrato e' magico"
			} else {
				var elem = document.querySelector("#result")
				elem.innerHTML = "Il quadrato non e' magico"
			}
			
			result = []
		}
	} else {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Il quadrato non e' magico"
	}
}