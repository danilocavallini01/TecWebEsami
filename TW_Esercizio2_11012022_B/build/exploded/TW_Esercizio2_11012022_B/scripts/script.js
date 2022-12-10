
var results = [];

function addCruciverbaChar(elem) {
	var char = elem.value
	
	if ( char.charCodeAt(0) < 65 || char.charCodeAt(0) > 90) {
		return;
	}

	var row = parseInt(elem.attributes[1].value)
	var col = parseInt(elem.attributes[2].value)

	sendToServlet(char,row,col)
}


function sendToServlet(char,row,col) {

	var argument = "char=" + char + "&row=" + row + "&col=" + col

	fetch("CheckParola", {
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
	elem.innerHTML = "Parole trovate: " +  data.join("\n")
}