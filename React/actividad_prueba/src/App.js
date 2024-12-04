import logo from './logo.svg';
import './App.css';
import HelloComponent from './components/HelloComponent';
import React,{Component} from 'react';

class App extends Component {
  constructor(){
    super();
    this.state={
        name: "Carmen"
    }
  }
  changeName=(event)=>{
    this.setState({
        name: event.target.value
    })
  }
  render(){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <HelloComponent nombre={this.state.name}></HelloComponent>
          <input value={this.state.name} onChange={this.changeName}></input>
        </header>
      </div>
    );
  }
}

/*function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <HelloComponent></HelloComponent>
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}*/

export default App;
