var results = []


function prenota() {
	var idMax = document.querySelector("#idAlberghi").value
	var id = document.querySelector("#id").value
	var checkIn = document.querySelector("#check-in").value
	var checkOut = document.querySelector("#check-out").value

	if ( id != "" && checkIn != "" && checkOut != "") {
		if ( id > idMax || parseInt(checkIn) > parseInt(checkOut) ) {
			var elem = document.querySelector("#result")
			elem.innerHTML = "Errore nell'inserimento dati"
		} else {
			sendToServlet(id,checkIn,checkOut)
		}	
	}	
}

function sendToServlet(id,checkIn,checkOut) {
	var argument = "id=" + id + "&checkin=" + checkIn + "&checkout=" + checkOut
	fetch("Prenota", {
		method: "POST",
		headers: {
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		body: argument,
	}
	)
	.then(response => response.json())
	.then(data => {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Prezzo totale : " + data
	})
	.catch(error => {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	})
}