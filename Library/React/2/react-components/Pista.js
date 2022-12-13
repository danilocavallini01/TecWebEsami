'use strict';

class Pista extends React.Component {
    
    render() {
        let name = this.props.name;
        let position = this.props.position;
        
        var rows = [], i = 0, len = 10;
        while (++i <= len) rows.push(i);
        
        rows[position] = name

        return (
            <div className="pista">
                { rows.map(function (i) {
                    return <input type="text" readonly value={i}/>
                })}
            </div>
        );
    }
}
