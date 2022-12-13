var result = []
var fetcher = null

function sendPosition() {
	let x = parseInt(document.querySelector("#x").value);
	let y = parseInt(document.querySelector("#y").value);
	
	if ( !isNaN(x) && !isNaN(y) ) {
		if ( fetcher == null ) {
			fetcher = setInterval(() => {sendToServlet(x,y)}, 5000)
		} else {
			clearInterval(fetcher)
			fetcher = setInterval(() => {sendToServlet(x,y)}, 5000)
		}
		
	}
}

function sendToServlet(x,y) {
	var argument = "x=" + x + "&y=" + y
	
	fetch("Locator", {
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
	let result = ""
	for ( const pokemon of data ) {
		result += "<br>"
		result += pokemon.name + "<br>" + pokemon.description + " a x: " + pokemon.x + " y: " + pokemon.y
	}
	elem.innerHTML = result
}