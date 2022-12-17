const socket = new WebSocket("ws://localhost:8080/TW_Esercizio3_14042022/actions");

var messages = []

function send(data) {
	let json = JSON.stringify(data);

	socket.send(json);
}


socket.onmessage = (event) => {
	let messages = JSON.parse(event.data);

	if ( messages instanceof Array ) {
		for ( const message of messages ) {
			addMessage(message)
		}
	} else {
		addMessage(messages)
	}
}

function addMessage(msg) {
	let elem = document.createElement("li")
	elem.innerHTML = msg
	
	document.querySelector("#messages").appendChild(elem)
}

function sendText() {
	let value = document.querySelector("#content").value

	send(value);
}
