var result = []

function win() {
	fetch("Winner", {
		method: "POST",
		headers: {
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		body: "",
	}
	)
	.then(response => response.json())
	.then(data => {})
}

function sendChar(elem) {
	let word = elem.value

	if ( word.charAt(word.length - 1) === "%" ) {
		let string = word.substring(0,word.length - 1)

		if ( string.length > 4 && string.length < 21) {
			sendToServlet(string, true)
			sendToServlet(string, false)
		}
	}	
}

function sendToServlet(word, isVocale) {
	var argument = "word=" + word + "&vocale=" + isVocale
	
	fetch("Encode", {
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
		elem.innerHTML = "Parole: <br>" + result[0].join("<br>") + "<br>" + data.join("<br>")
		result = []
	} else {
		result.push(data)
	}
}
