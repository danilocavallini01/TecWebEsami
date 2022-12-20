'use strict';

class App extends React.Component {
  constructor() {
    super();

    this.state = {
      group: -1,
      chat: [],
      ws: new WebSocket("ws://localhost:8080/TW_Esercizio3_20102022/actions"),
    }

    this.handleWsMessage = this.handleWsMessage.bind(this)
    this.setIdGroup = this.setIdGroup.bind(this)
    this.sendText = this.sendText.bind(this)

    this.state.ws.onmessage = this.handleWsMessage
  }

  handleWsMessage(event) {
    var message = JSON.parse(event.data);

    if ( message.responseType == "set_group_response" ) {
      if ( message.success ) {
        console.log(message)
        this.setState({
          group: message.id,
          chat: message.groupChat,
        })
      }
    } else if ( message.responseType == "send_text_response" ) {
      let chat = this.state.chat
      chat.push(message.text)

      this.setState({
        chat: chat
      })
    }
  }

  setIdGroup(event) {
    let id = parseInt(event.target.value)
  
    if ( isNaN(id) ) {
      return;
    }

    let setGroupRequest = {}
    setGroupRequest.type = "set_group_request"
    setGroupRequest.id = id;

    this.send(setGroupRequest)
  }

  sendText(elem) {
    let text = elem.value
    let sendTextRequest = {}
    sendTextRequest.type = "send_text_request"
    sendTextRequest.text = text

    this.send(sendTextRequest)
  }

  send(data) {
    var json = JSON.stringify(data);
    this.state.ws.send(json);
  }


  render() {
    return (
      <div className="container">
        <h1>Tris</h1>
        {
          this.state.group == -1 ?
            <div>
              <label>
                Inserisci id del gruppo
                <input type="number" onChange={this.setIdGroup}></input>
              </label>
            </div>
            :
            <p> Id gruppo : {this.state.group}</p>
        }

        {
          this.state.group != -1 &&
          <Chat chat={this.state.chat} onText={this.sendText}/>
        }

      </div>
    );
  }
}
