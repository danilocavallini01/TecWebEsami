'use strict';

class Chat extends React.Component {
    constructor() {
        super();
        this.send = this.send.bind(this)
    }

    send() {
        this.props.onText(document.querySelector("#send"))
    }

    render() {
        let chat = this.props.chat

        return (
            <div>
                <ul>
                    {
                        chat.map((row) => {
                            return <p> {row} </p>
                        })
                    }
                    <label> Scrivi:
                        <input type="text" id="send" />
                    </label>
                    <input type="button" onClick={this.send} value="Invia"/>
                </ul>
            </div>
        );
    }
}
