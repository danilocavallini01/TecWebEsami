'use strict';

class App extends React.Component {
  constructor() {
    super();

    this.state = {
      gameStarted: false,
      field: [],
      playerSymbol: "",
      win: false,
      whoWin: "",
      turn: "",
      ws: new WebSocket("ws://localhost:8080/TW_Esercizio3_26012022_B/actions"),
    }

    this.handleWsMessage = this.handleWsMessage.bind(this)
    this.cellSet = this.cellSet.bind(this)
    this.state.ws.onmessage =  this.handleWsMessage;
  }

  handleWsMessage(event) {
    var message = JSON.parse(event.data);
    if ( message.responseType == "map_response") {
      if ( this.state.gameStarted ) {
        this.setState({
          field: message.map,
          turn: message.playerTurn
        })
      } else {
        this.loadMap(message.map, message.playerSymbol, message.playerTurn)
      }
    } else if ( message.responseType == "win_response" ) {
      if ( message.win ) {
        this.setState({
          win: true,
          whoWin: message.whoWin,
          turn: null,
        })
      }
    }
  }

  loadMap(map, playerSymbol, playerTurn) {
    let field = []

    for ( let i = 0; i < 3 ; i++) {
      let row = []
      for ( let j = 0; j < 3; j++) {
        row.push(map[i][j])
      }

      field.push(row)
    }

    this.setState({
      gameStarted: true,
      field: field,
      turn: playerTurn,
      playerSymbol: playerSymbol,
    })
  }

  cellSet(event) {
    let elem = event.target
    let row = parseInt(elem.getAttribute("row"))
    let col = parseInt(elem.getAttribute("col"))

    var operationReq = {};
    operationReq.row = row;
    operationReq.col = col;
    
    this.send(operationReq);
  }

  send(data) {
    var json = JSON.stringify(data);
    this.state.ws.send(json);
  }


  render() {
    return (
      <div className="container">
        <h1>Tris</h1>
        { this.state.turn &&
          <div>
            <p> Il tuo simbolo e: {this.state.playerSymbol}</p>
            <p> e' il turno di {this.state.turn}</p>
          </div>
        }
        { this.state.gameStarted ?
          <Mappa field={this.state.field} onSet={this.cellSet} />
          :
          <p> Aspettando il secondo giocatore...</p>
        }
        {
          this.state.win &&
          <p> Il vincitore e' : {this.state.whoWin} </p>
        }
      </div>
    );
  }
}
