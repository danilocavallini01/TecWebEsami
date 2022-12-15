'use strict';

class Configurazione extends React.Component {
    constructor() {
        super();
        this.state = {
            width: 0,
            length: 0,
            moves: 0
        }

        this.setWidth = this.setWidth.bind(this)
        this.setLength = this.setLength.bind(this)
        this.setMoves = this.setMoves.bind(this)
        this.sendConfig = this.sendConfig.bind(this)
    }

    setWidth(event) {
        let value = parseInt(event.target.value)
        if (isNaN(value) || value > 8) {
            return;
        }

        this.setState({
            width: value
        })
    }

    setLength(event) {
        let value = parseInt(event.target.value)
        if (isNaN(value) || value > 8) {
            return;
        }

        this.setState({
            length: value
        })
    }

    setMoves(event) {
        this.setState({
            moves: event.target.value
        })
    }

    sendConfig() {
        let opts = {
            width: this.state.width,
            length: this.state.length,
            moves: this.state.moves,
        }

        this.props.onSubmit(opts)
    }

    render() {
        return (
            <div className="center">
                <p> Larghezza </p>
                <input type="text" onChange={this.setWidth} />
                <p> Lunghezza </p>
                <input type="text" onChange={this.setLength} />
                <p> NumLanci </p>
                <input type="text" onChange={this.setMoves} />
                <br/>
                <input type="button" onClick={this.sendConfig} value="Salva"/>
            </div>
        );
    }
}
