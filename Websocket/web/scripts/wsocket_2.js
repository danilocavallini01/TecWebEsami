const socket2 = new WebSocket("ws://localhost:8080/07_TecWeb/actions2");

function send2( data) {
    var json = JSON.stringify(data);

    socket2.send(json);
}

socket2.onmessage =  function (event){
    var message = JSON.parse(event.data);

	var op = document.querySelector(message.update)
    op.value = message.valore
}


function update(id) {
	var op = document.querySelector(id).value;
    console.log(op)
	var updateReq = {};

    updateReq.update = id;
    updateReq.valore = op;

    send2(updateReq)
}

