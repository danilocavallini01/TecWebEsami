'use strict';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      schedina: [],
      typeOfWin: "",
    }

    this.saveSchedina = this.saveSchedina.bind(this)
    this.onExtract = this.onExtract.bind(this)
  }

  saveSchedina(values) {

    let numbers = []

    for ( const value of values ) {
      numbers.push({ win : false , number: value})
    }

    this.setState({
      schedina: numbers,
      typeOfWin: ""
    })
  }
  
  onExtract(extracted) {
    let schedina = this.state.schedina
    let countWin = 0

    for ( let i = 0; i < schedina.length; i++ ) {
      let number = schedina[i].number

      if ( extracted.includes(number) ) {
        schedina[i].win = true
        countWin++
      } else {
        schedina[i].win = false
      }
    }
    
    let typeOfWin = this.computeWin(countWin)
    this.setState({
      schedina: schedina,
      typeOfWin: typeOfWin
    })
  }

  computeWin(countWin) {
    if ( countWin == 2 ) {
      return "ambo"
    } else if ( countWin == 3 ) {
      return "terno"
    } else if ( countWin == 4 ) {
      return "quaterna"
    } else if ( countWin == 5 ) {
      return "cinquina"
    } else {
      return ""
    }
  }


  render() {
    return (
      <div className="container">
        <h1>Compila</h1>
        <Compila onSave={this.saveSchedina} />
        <h1>Schedina</h1>
        <Schedina schedina={this.state.schedina} typeOfWin={this.state.typeOfWin}/>
        <h1>Estrai</h1>
        <Estrai onLaunch={this.onExtract} />
      </div>
    );
  }
}
