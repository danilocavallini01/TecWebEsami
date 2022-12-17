var preloaded = []

function checkSurname() {
	var surname = document.querySelector("#name").value

	for ( const tennista of preloaded ) {
		if ( tennista.surname == surname ) {
			setStatistiche(tennista)
			return
		}
	}

	sendToServlet(surname)
}

function sendToServlet(surname) {
	var argument = "surname=" + surname
	
	fetch("TennistiStats", {
		method: "POST",
		headers: {
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		body: argument,
	}
	)
	.then(response => response.json())
	.then(data => {
		checkResult(data, surname)
	})
	.catch(error => {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	})
}

function checkResult(data, surname) {
	if ( data ) {
		preloaded = data

		for ( const tennista of data ) {
			if ( tennista.surname == surname ) {
				setStatistiche(tennista)
				return
			}
		}
	} else {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Non partecipa agli US Open 2022"
	}
}

function setStatistiche(tennista, surname) {
	var elem = document.querySelector("#result")
	elem.innerHTML = tennista.surname + " -> rankingATP: " + tennista.rankingATP + " titoli: " + tennista.titols + " vinte: " + tennista.wins + " perse: " + tennista.lose
}