'use strict';

class Mappa extends React.Component {
    render() {
        let field = this.props.field

        return (
            <div>
                {
                field.map((row, i) => {
                    return <div class="flex-row">
                        {
                        row.map((col ,j) => {
                            return <input type="text" row={i} col={j} value={col} onChange={this.props.onSet} />
                        })
                        }
                    </div>
                })
                }
            </div>
        );
    }
}
