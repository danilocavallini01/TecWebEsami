'use strict';

class App extends React.Component {
  constructor(){
    super();
    this.state = {
    }

    this.getConfig = this.getConfig.bind(this)
  }

  getConfig(data) {
    console.log(data)
  }

  render() {
      return (
        <div className="container">
            <h1>Pesca Al Lago</h1>

            <Configurazione onSubmit={this.getConfig} />
        </div>
      );
  }
}
