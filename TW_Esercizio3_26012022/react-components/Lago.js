'use strict';

class Lago extends React.Component {
    
    render() {
        let griglia = this.props.griglia
        
        return (
            <div>
                {
                    griglia.map((riga, i) => {
                    return <div key={i} class="riga">
                        {
                        riga.map((col, j) => {
                            return <input type="text" class={col.cover ? "cover" : "" } key={i + j} row={i} col={j} value={col.num} readonly onClick={this.props.onClick} />
                        })
                        }
                    </div>
                    })
                }
            </div>
            
        );
    }
}
