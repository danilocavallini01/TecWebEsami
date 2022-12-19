function checkPrice(elem) {
	let price = elem.value

	if ( price.charCodeAt(price.length - 1) !== 8364 ) {
		return;
	}	

	price = parseInt(price.substring(0, price.length - 1));

	if ( isNaN(price)) {
		return;
	}

	sendToServlet(price)
}

function sendToServlet(price) {
	var argument = "price=" + price
	
	fetch("Purchase", {
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
	var elem = document.querySelector("#result")
	if ( data.success ) {
		elem.innerHTML = "Prezzo salvato con successo"
	} else {
		if ( data.winner == null ) {
			elem.innerHTML = "Prezzo inferiore a quello piu' alto"
		} else {
			elem.innerHTML = "Articolo già venduto, vincitore : " + data.winner + " al prezzo: " + data.price
		}
	}
}