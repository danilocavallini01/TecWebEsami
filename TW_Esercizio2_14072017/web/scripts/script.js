
var results = []

function toUpperCaseFile() 
{
	var filename = document.querySelector("#name").value
	var content = document.querySelector("#content").value
	
	if ( content.length < 1000 || content.length > 2000) {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore : contenuto troppo ridotto"
		return;
	}
	sendToServlet(filename,content.substring(0,content.length/2))
	sendToServlet(filename,content.substring(content.length/2,content.length))
}

function sendToServlet(filename,content) {
	var argument = "name="+filename+"&content="+content;
	
	fetch("UpperCaseFile", {
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
	console.log(results)
	if ( results.length >= 1 ) {
		results.push(data)
		var finalResult = 0;
		for ( var singleResult of results ) {
			console.log(finalResult)
			finalResult += singleResult
		}

		results = []
		var elem = document.querySelector("#result")
		elem.innerHTML = "Risultato : " + finalResult
	} else {
		results.push(data)
	}
}