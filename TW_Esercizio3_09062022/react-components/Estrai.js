'use strict';

class Estrai extends React.Component {
    constructor() {
        super();

        this.state = {
            schedina: [ "" , "" , "" , "", ""]
        }

        this.estrai = this.estrai.bind(this)
    }

    estrai() {
        let extractedNumbers = []
        for ( let i = 0; i < 5; i++) {
            let random = Math.floor(Math.random() * 10 + 1)
            extractedNumbers.push(random)
        }

        this.setState({
            schedina: extractedNumbers
        })

        this.props.onLaunch(extractedNumbers)
    }

    render() {
        let schedina = this.state.schedina
        return (
            <div>
                {
                    schedina.map((number) => {
                        return <input type="text" readonly value={number} />
                    })
                }
                 <input type="button" onClick={this.estrai} value="Salva"/>
            </div>

        );
    }
}
