var result = []

function sendFileName(elem) {
	let casual = elem.value

	if ( casual.charCodeAt(casual.length - 1) === 32 ) {
		sendToServlet(casual.trim())
	}	
}

function sendToServlet(text) {
	var argument = "file=" + text
	
	fetch("Casual", {
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
		var elem = document.querySelector("#result")
		elem.innerHTML = "Il testo e' : " + data.result + " con  : " + data.count + " maiuscole"
	} else {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	}
}