'use strict';

class Configurazione extends React.Component {
    constructor() {
        super();

        this.saveConfig = this.saveConfig.bind(this)
    }

    saveConfig() {
        let height = parseInt(document.querySelector("#altezza").value)
        let width = parseInt(document.querySelector("#larghezza").value)

        if ( isNaN(height) || height < 5 ) {
            return
        }

        if ( isNaN(width) || height < 5 ) {
            return
        }

        this.props.onSave(height,width)
    }

    render() {
        return (
            <div className="center">
                <input id="altezza" type="number" min="5"></input>
                <input id="larghezza" type="number" min="5"></input>
                <br></br>
                <input type="button" onClick={this.saveConfig} value="Salva"/>
            </div>
        );
    }
}
