var results = []

function checkWord() {
	var text = document.querySelector("#text").value
	if ( text.length > 9 ) {
		return;
	}


	if ( text.charCodeAt(text.length) === 36) {
		text = text.replace("$","")
		for ( var i = 0; i < text.length; i++ ) {
			if ( text.charCodeAt(i) < 97 && text.charCodeAt(i) > 122 ) {
				return
			} 
		}

		sendToServlet(text,1)
		sendToServlet(text,0)
	}
}

function sendToServlet(text,isVocali) {
	var argument = "text="+text+"&vocali="+isVocali
	
	fetch("Anagram", {
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
		results.push(result+"<br>,")
	}

	if ( results.length >= 20 ) {

		var elem = document.querySelector("#result")
		elem.innerHTML = "Risultato : " + results.join(",")
	}
}