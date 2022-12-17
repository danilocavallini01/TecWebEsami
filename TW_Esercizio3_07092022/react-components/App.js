'use strict';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      width: 0,
      height: 0,
      field: [],
      moves: 0,
      points: 0,
      end: false,
    }

    this.saveConfig = this.saveConfig.bind(this)
    this.checkCell = this.checkCell.bind(this)
  }

  saveConfig(height, width) {
    let field = []
    for (let i = 0; i < width; i++) {
      let arr = []
      for (let j = 0; j < height; j++) {
        arr.push({ open: false, value: null })
      }
      field.push(arr)
    }

    let i = Math.floor(Math.random() * width)
    let j = Math.floor(Math.random() * height)

    field[i][j] = { open: false, value: "T" }

    this.setState({
      moves: 0,
      width: width,
      height: height,
      field: field,
      end: false,
    })
  }

  checkCell(event) {
    if (this.state.end) return;

    let elem = event.target
    let field = this.state.field
    let moves = this.state.moves
    let points = this.state.points
    let end = false

    let i = parseInt(elem.getAttribute("row"))
    let j = parseInt(elem.getAttribute("col"))

    if (field[i][j].open) return;

    if (field[i][j].value == "T") {
      if (moves > 10) {
        points += 2
      } else {
        points += 5
      }
      end = true
    } else {
      moves++
    }

    field[i][j].open = true

    this.setState({
      field: field,
      moves: moves,
      points: points,
      end: end,
    })
  }

  render() {
    return (
      <div className="container">
        <h1>Configurazione</h1>
        <Configurazione onSave={this.saveConfig} />
        <h1>Mappa del tesoro</h1>
        <Mappa field={this.state.field} onClick={this.checkCell} />
        <h1>Punteggio</h1>
        <Punteggio moves={this.state.moves} points={this.state.points} />
      </div>
    );
  }
}
