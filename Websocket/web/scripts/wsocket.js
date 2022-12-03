const socket = new WebSocket("ws://localhost:8080/07_TecWeb/actions");

function send( data) {
    var json = JSON.stringify(data);

    socket.send(json);
}


socket.onmessage =  function (event){
	
	 var message = JSON.parse(event.data);
	 if(message.valid)
		 {
		 	var display = document.querySelector("#display");
		    display.value = message.risultato
		 }else{
			 alert("hai superato il limite massimo di richieste per sessione");
		 }
	
}

function myFunction()
{
	var op1 = document.querySelector("#op1").value;
	var op2 = document.querySelector("#op2").value;
	if(isNaN(op1) || isNaN(op2))
		{
			alert("uno dei due operandi non è un numero");
			return;
		}
	var operazione = document.querySelector("#operazione").value

	if ( operazione !== "+" && operazione !== "-" && operazione !== "*" && operazione !== "/") {
		alert("uno dei due operandi non e' un numero");
		return;
	}

	var operationReq = {};
	operationReq.op1 = op1;
	operationReq.op2 = op2;
	operationReq.operazione = operazione;
	
	send(operationReq);
	
}
