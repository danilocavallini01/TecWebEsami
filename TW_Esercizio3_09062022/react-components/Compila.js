'use strict';

class Compila extends React.Component {
    constructor() {
        super();

        this.saveSchedina = this.saveSchedina.bind(this)
    }

    saveSchedina() {
        let inputs = document.querySelectorAll("input[compila]")
        let values = []

        for ( const input of inputs ) {
            let value = parseInt(input.value)

            if ( isNaN(value) || value < 1 || value > 10 ) {
                return
            }

            values.push(value)
        }

        this.props.onSave(values)
    }

    render() {
        let arr = [1,2,3,4,5];

        return (
            <div className="center">
                {
                    arr.map((val) => {
                        return <input compila="true" type="number" max="10"></input>
                    })
                }
                <br></br>
                <input type="button" onClick={this.saveSchedina} value="Salva"/>
            </div>
        );
    }
}
