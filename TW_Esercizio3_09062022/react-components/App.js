'use strict';

class App extends React.Component {
  constructor(){
    super();
    this.state = {
        name1: "verstappen",
        position1: 0,
        name2: "hemilton",
        position2: 0,
        leaderboard: [],
        punti1: 0,
        punti2: 0,
    }

    this.startRace()
  }

  startRace() {
    setInterval(() => {this.race()},5000)
  }

  race() {
    let newPosition1 = parseInt(Math.random() * 3) + 1
    let newPosition2 = parseInt(Math.random() * 3) + 1 
    if ( newPosition1 + this.state.position1 >= 10 & newPosition2 + this.state.position2 >= 10) {
      this.setState({
        position1: 0,
        position2: 0,
      })
      return;
    }

    if ( newPosition1 + this.state.position1 >= 10 || newPosition2 + this.state.position2 >= 10) {

      var points = [];

      if ( newPosition1 + this.state.position1 >= 10) {
        points.push(this.state.punti1 + 10)
        points.push(this.state.punti2)
      }

      if ( newPosition2 + this.state.position2 >= 10) {
        points.push(this.state.punti1)
        points.push(this.state.punti2 + 10)
      }

      let leaderboard = this.state.leaderboard
      leaderboard.push(points)

      this.setState({
        position1: 0,
        position2: 0,
        leaderboard: leaderboard,
        punti1: points[0],
        punti2: points[1],
      })
    } else {
      this.setState({
        position1: newPosition1 + this.state.position1,
        position2: newPosition2 + this.state.position2
      })
    }
  }

  render() {
      return (
        <div className="container">
            <h1>Piste</h1>

            <Pista name={this.state.name1} position={this.state.position1} />
            <Pista name={this.state.name2} position={this.state.position2} />
            <LeaderBoard name1={this.state.name1} punti1={this.state.punti1} name2={this.state.name2} punti2={this.state.punti2} leaderboard={this.state.leaderboard} />
        </div>
      );
  }
}
