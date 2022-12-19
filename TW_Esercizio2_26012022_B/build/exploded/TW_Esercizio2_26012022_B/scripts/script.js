var result = []

function sendChar(elem) {
	var char = elem.value

	if ( char.length == 1 ) {
		sendToServlet(char, true)
		sendToServlet(char, false)
	}	
	
}

function sendToServlet(char, isFirstHalf) {
	var argument = "char=" + char + "&firstHalf=" + isFirstHalf
	
	fetch("CountChar", {
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
	if ( result.length == 1 ) {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Numero di caratteri nel file : " + (result[0] + data)
		result = []
	} else {
		result.push(data)
	}
}
