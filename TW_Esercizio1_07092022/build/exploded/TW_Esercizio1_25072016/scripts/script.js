
var results = [];
var currentCap = null;

function chooseChapter() {
	var cap = parseInt(document.querySelector("#cap").value)

	if ( cap >= 1 && cap <= 10) {
		sendToServletChoose(cap)
	}
}

function saveChapter() {
	var content = document.querySelector("#content").value

	if ( currentCap != null ) {
		sendToServletContent(content)
	} 
}


function sendToServletChoose(cap) {

	var argument = "cap=" + cap

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

function sendToServletContent(content) {

	var argument = "content=" + content + "&id=" + currentCap.id + "&version=" +  currentCap.version

	fetch("Content", {
		method: "POST",
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: argument,
	}
	)
	.then(response => response.json())
	.then(data => {
		checkResultContent(data)
	})
	.catch(error => {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	})
}

function checkResult(data) {
	currentCap = data;
	document.querySelector("#content").value = currentCap.content
}

function checkResultContent(data) {
	if ( data === "errore") {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore versione non aggiornata"
	} else {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Capitolo salvato correttamente"
		
		currentCap = data
	}
}