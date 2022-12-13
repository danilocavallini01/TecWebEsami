'use strict';

class LeaderBoard extends React.Component {
    
    render() {
        let perso = this.props.perso;
        let vinto = this.props.vinto;

        return (
            <div className="leaderboard">
               <p> vinto : { vinto } , perso : {perso} </p>
            </div>
        );
    }
}
