'use strict';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      griglia: [],
      height: 0,
      width: 0,
      passi: 0,
      vinto: 0,
      perso: 0,
    }

    this.settings = this.settings.bind(this)
    this.openCell = this.openCell.bind(this)
    this.createMap = this.createMap.bind(this)
  }

  settings() {
    var row = parseInt(document.querySelector("#righe").value)
    var col = parseInt(document.querySelector("#colonne").value)


    if (row > 5 && col > 5) {
      this.createMap(row,col)
    }
  }

  openCell(e) {
    var cell = e.target
    let attr = cell.attributes
    
    let row = parseInt(attr.row.value)
    let col = parseInt(attr.col.value)

    var griglia = this.state.griglia

    
    if (griglia[row][col].value == "bomb") {
      alert("hai perso")
      this.setState({
        perso: this.state.perso + 1
      })
      this.createMap(this.state.height, this.state.width)
      return
    }

    griglia[row][col].cover = false

    this.setState({
      griglia: griglia
    })

    if (this.state.passi < 4) {
      this.setState({ passi: this.state.passi + 1 })
    } else {
      alert("hai vinto")  
      this.createMap(this.state.height, this.state.width)
      this.setState({
        vinto: this.state.vinto + 1
      })
    }
  }

  createMap(row, col) {
    let grid = []

    for (let i = 0; i < row; i++) {
      let rows = []
      let int1 = parseInt(Math.random() * col)
      let int2 = parseInt(Math.random() * col)

      for (let j = 0; j < col; j++) {
        if (j != int1 && j != int2) {
          rows.push({ cover : true, value : "empty"})
        } else {
          rows.push({ cover : true, value : "bomb"})
        }
      }

      grid[i] = rows
    }

    this.setState(
      {
        height: row,
        width: col,
        griglia: grid,
        passi: 0,
      }
    )
  }

  render() {
    return (
      <div className="container">
        <h1>Campo minato</h1>
        <label for="righe"></label>
        <input id="righe" type="text" ></input>
        <label for="colonne"></label>
        <input id="colonne" type="text" ></input>
        <input type="button" value={"Imposta"} onClick={this.settings}></input>

        {this.state.griglia.map((riga, i) => {
          return <div key={i} class="riga">
            {riga.map((col, j) => {
              return <input type="text" class={col.cover ? "cover" : (col.value == "bomb" ? "bomb" : "") } key={i + j} row={i} col={j} value={col.value} onClick={this.openCell} />
            })}
          </div>
        })
        }

        <LeaderBoard vinto={this.state.vinto} perso={this.state.perso} />
      </div>
    );
  }
}
