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
                            return <input type="text" row={i} col={j} readonly class={!col.open ? "cover" : ( col.value == "T" ? "treasure-cell" : "empty-cell") } value={col.value} onClick={this.props.onClick} />
                        })
                        }
                    </div>
                })
                }
            </div>
        );
    }
}
