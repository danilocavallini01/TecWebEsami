
var results = []

function calculateIntegral() 
{
	var start = parseFloat(document.querySelector("#start").value)
	var end = parseFloat(document.querySelector("#end").value)
	console.log(start,end)
	offset = parseFloat((end - start) / 4.0)
	for ( var i = 0.0 ; i < 4.0; i += 1.0 ) {
		sendToServlet(start + offset * (i),start + offset * (i+1))
	}
}

function sendToServlet(start,end) {
	var argument = "start="+start+"&end="+end;
	
	fetch("Integral", {
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
	if ( results.length >= 3 ) {
		results.push(data)
		var finalResult = 0.0;
		for ( var singleResult of results ) {
			console.log(finalResult)
			finalResult += singleResult
		}
		console.log(finalResult)
		results = []
		var elem = document.querySelector("#result")
		elem.innerHTML = "Risultato : " + finalResult
	} else {
		results.push(data)
	}
}