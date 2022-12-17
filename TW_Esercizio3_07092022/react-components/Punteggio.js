'use strict';

class Punteggio extends React.Component {
    render() {
        let moves = this.props.moves
        let points = this.props.points
        return (
            <div>
                <p> { "Mosse: " + moves}</p>
                <br></br>
                <p> { "Punteggio: " + points}</p>
            </div>

        );
    }
}
