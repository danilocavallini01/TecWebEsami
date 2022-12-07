
var results = [];

function checkInput() {
	var campo = parseInt(document.querySelector("#campo").value)
	var giorno = parseInt(document.querySelector("#giorno").value)
	var orario = parseInt(document.querySelector("#orario").value)

	console.log(campo,giorno,orario)
	if ( campo >= 1 && campo <= 6 && giorno >= 1 && giorno <= 365 && orario >= 0 && orario <= 23) {
		sendToServlet(campo,giorno,orario)
	}
}


function sendToServlet(campo,giorno,orario) {

	var argument = "campo=" + campo + "&giorno=" + giorno + "&orario=" + orario

	fetch("Acquista", {
		method: "POST",
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
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
	elem.innerHTML = data
}