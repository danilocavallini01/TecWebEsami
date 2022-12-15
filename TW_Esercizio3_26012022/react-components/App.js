'use strict';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      width: 0,
      length: 0,
      moves: 0,
      totalMoves: 0,
      media: 0,
      finish: false,

      log: [],
      griglia: []
    }

    this.getConfig = this.getConfig.bind(this)
    this.openCell  = this.openCell.bind(this)
  }

  getConfig(data) {
    let griglia = []
    for ( let i = 0; i < data.width; i++ ) {
      let row = []
      for ( let j = 0; j < data.length; j++ ) {
        let num = parseInt(Math.random() * 6)
        let fish = { num: num, cover: true }
        row.push(fish)
      }
      griglia.push(row)
    }

    this.setState({
      width: data.width,
      length: data.length,
      moves: data.moves,
      totalMoves: data.moves,
      log: [],
      griglia: griglia,
      points: 0,
      finish: false,
    })
  }

  openCell(event) {
    
    if ( this.finish ) return;

    var cell = event.target
    let attr = cell.attributes
    
    let row = parseInt(attr.row.value)
    let col = parseInt(attr.col.value)

    var griglia = this.state.griglia

    if ( griglia[row][col].cover ) {

      let totalFish = 0;
      
      totalFish += griglia[row][col].num
      griglia[row][col].cover = false

      if ( row >= 1) {
        totalFish += griglia[row-1][col].num
        griglia[row-1][col].cover = false
      }
       

      if ( row < this.state.length - 1) {
        totalFish += griglia[row+1][col].num
        griglia[row+1][col].cover = false
      }
       

      if ( col >= 1) {
        totalFish += griglia[row][col-1].num
        griglia[row][col-1].cover = false
      }
       

      if ( col < this.state.width - 1) {
        totalFish += griglia[row][col+1].num
        griglia[row][col+1].cover = false
      }
      

      let moves = this.state.moves
      let log = this.state.log
      log.push(totalFish)

      if ( moves <= 1 ) {
        this.setState({
          moves: 0,
          griglia: griglia,
          points: this.state.points + totalFish,
          media: (this.state.points + totalFish) / this.state.totalMoves,
          log: log,
          finish: true,
        })


      } else {
        this.setState({
          moves: this.state.moves - 1,
          griglia: griglia,
          points: this.state.points + totalFish,
          log: log
        })
      }
    }
  }

  render() {
    return (
      <div className="container">
        <h1>Pesca Al Lago</h1>

        <Configurazione onSubmit={this.getConfig} />
        <Lago griglia={this.state.griglia} onClick={this.openCell} />
        <Punteggio log={this.state.log} finish={this.state.finish} />
      </div>
    );
  }
}
