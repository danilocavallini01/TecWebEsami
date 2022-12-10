var result = []

function checkCasualText() {
	let casual = document.querySelector("#content").value
	let casualUpper = casual.toUpperCase()

	if ( casual.charCodeAt(casual.length - 1) === 163 || casual.length >= 1000 ) {
		if ( casual.length < 1000 ) {
			casual = casual.substring(0 , casual.length - 1)
		}

		for ( var i = 0; i < casual.length - 1; i++) {
			let chr = casualUpper.charCodeAt(i)
			
			if ( chr < 65 || chr > 90) {
				return;
			}
		}

		sendToServlet(casual)
	}	
}

function sendToServlet(text) {
	var argument = "text=" + text
	
	fetch("./casualJsp.jsp", {
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
		elem.innerHTML = "Il testo e' : " + data.result + " di lunghezza : " + data.length
	} else {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	}
}