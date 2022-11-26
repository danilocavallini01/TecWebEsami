
var results = []

function login() 
{
	var user = parseFloat(document.querySelector("#user").value)
	var psw = parseFloat(document.querySelector("#psw").value)

	loginAjax(user,psw)
}

function loginAjax(user,psw) {
	fetch("Login", {
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