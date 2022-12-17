function addArticolo(elem) {
	var articolo = elem.value
	console.log(articolo.charCodeAt(articolo.length - 1))
	if ( articolo.length > 64 || articolo.charCodeAt(articolo.length - 1) == 37 ) {
		sendToServlet(articolo.substring(0,articolo.length - 1))
	}
}


function sendToServlet(articolo) {

	var argument = "art=" + articolo

	fetch("Choose", {
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
		var elem = document.querySelector("#result_cap")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	})
}

function checkResult(data) {
	document.querySelector("#name").value = data.name
	document.querySelector("#content").value = data.content
}