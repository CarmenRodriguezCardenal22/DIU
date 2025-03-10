 import { Component } from "react";
import "./../styles/Contador.css";
import { render } from "react-dom";

 //Con clases
 export default class Contador extends Component {
  render(){
    return(
      <div className="container-counter">
        <div className="counter">
            <h1> {this.props.num} </h1>
        </div>
        <div className="buttons">
            <button className="incButton" onClick={this.props.incrementar}> Incrementar </button>
            <button onClick={this.props.decrementar}> Decrementar </button>
            <button onClick={this.props.resetear}> Resetear </button>
        </div>
    </div>
    )
  }
 }

 
 //Con funciones
 
 /*function Contador(props) {
  const {num, incrementar, decrementar, resetear} = props;
  return (

    <div className="container-counter">
        <div className="counter">
            <h1> {num} </h1>
        </div>
        <div className="buttons">
            <button className="incButton" onClick={incrementar}> Incrementar </button>
            <button onClick={decrementar}> Decrementar </button>
            <button onClick={resetear}> Resetear </button>
        </div>
    </div>
  )
}

export default Contador*/