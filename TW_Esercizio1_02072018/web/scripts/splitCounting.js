var files = [];

function addFile(elem) {
	if ( elem.checked ) {
		files.push(elem.value)
	} else {
		files.splice(files.indexOf(elem),1)
	}
	
	if (files.length >= 3) {
		sendFiles()
	}
}

function sendFiles() {
	var argument = "file1="+files[0]+"&file2="+files[1]+"&file3="+files[2];
	
	fetch("./FileManager", {
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
		elem.innerHTML = "ServerTime: " + data.serverTime + " / BeanTime: " + data.beanTime + " | ServerCount: " + data.serverCount + " / BeanCount: " + data.beanCount
	})
	.catch(error => {
		var elem = document.querySelector("#result")
		elem.innerHTML = "Errore durante il recupero dei dati dal server"
	})

}