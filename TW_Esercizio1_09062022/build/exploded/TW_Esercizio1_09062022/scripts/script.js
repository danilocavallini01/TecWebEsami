
var results = [];

function sendText() {
	var textarea = document.querySelector("#content").value
	var upper = textarea.toUpperCase()
	if ( textarea.length > 100 && textarea.length < 1000 ) {
		for ( var i = 0; i < textarea.length; i++) {
			var char = upper.charCodeAt(i)
			if ( !((char >= 48 && char <= 57 ) || (char >= 65 && char <= 90 )) ) {
				document.querySelector("#textSubmit").classList.add("hide")
				return;
			}
		}
		document.querySelector("#textSubmit").classList.remove("hide")
		document.querySelector("#textJson").value = JSON.stringify(textarea)
	} else {
		document.querySelector("#textSubmit").classList.add("hide")
	}
	
}