'use strict';

class Punteggio extends React.Component {

    render() {
        let logs = this.props.log
        let finish = this.props.finish
        let result = 0
        
        if ( finish ) {
            for ( const log of logs ) {
                result += log
            }
        }
        return (
            <div>
                { finish && 
                    <p>Punteggio totale: {result}</p>
                }
                <ul>
                {
                    logs.map((log,i) => {
                        return <li>{ (i + 1) + ":" + log }</li>
                    })
                }
                </ul>
            </div>

        );
    }
}
