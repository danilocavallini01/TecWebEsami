'use strict';

class Schedina extends React.Component {
    
    render() {
        let schedina = this.props.schedina
        let typeOfWin = this.props.typeOfWin

        return (
            <div>
                {
                    schedina.map((numero) => {
                        return <input type="text" readonly class={ numero.win ? typeOfWin : ""} value={numero.number} />
                    })
                }
            </div>
            
        );
    }
}
