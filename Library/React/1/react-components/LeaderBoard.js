'use strict';

class LeaderBoard extends React.Component {
    
    render() {
        let leaderboard = this.props.leaderboard;
        let name1 = this.props.name1
        let punti1 = this.props.punti1
        let name2 = this.props.name2
        let punti2 = this.props.punti2

        return (
            <div className="leaderboard">
                <p> Punti { name1 + " : " + punti1 } / { name2 + " : " + punti2 }  </p>
                <p> LeaderBoard </p>
                <ul>
                    { leaderboard.map(function (object) {
                        return  <li> Lap { name1 + " : " + object[0] } / { name2 + " : " + object[1] }  </li>
                    })}
                </ul>
                
            </div>
        );
    }
}
