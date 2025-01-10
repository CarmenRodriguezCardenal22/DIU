import React from 'react'
import './calculadora.css'

function Calculadora(props) {
    const { n, texto, limpiar, igual, posNeg } = props;
  
    return (
      <div>
        <div>
          <input type="text" className="texto" value={n} readOnly />
        </div>
        <div className="div">
          <button onClick={limpiar}>AC</button>
          <button onClick={posNeg}>+/-</button>
          <button onClick={() => texto('%')}>%</button>
          <button className="op" onClick={() => texto('/')}>/</button>
        </div>
        <div className="div">
          <button onClick={() => texto('7')}>7</button>
          <button onClick={() => texto('8')}>8</button>
          <button onClick={() => texto('9')}>9</button>
          <button className="op" onClick={() => texto('*')}>*</button>
        </div>
        <div className="div">
          <button onClick={() => texto('4')}>4</button>
          <button onClick={() => texto('5')}>5</button>
          <button onClick={() => texto('6')}>6</button>
          <button className="op" onClick={() => texto('-')}>-</button>
        </div>
        <div className="div">
          <button onClick={() => texto('1')}>1</button>
          <button onClick={() => texto('2')}>2</button>
          <button onClick={() => texto('3')}>3</button>
          <button className="op" onClick={() => texto('+')}>+</button>
        </div>
        <div className="div">
          <button className="cero" onClick={() => texto('0')}>0</button>
          <button onClick={() => texto('.')}>.</button>
          <button className="op" onClick={igual}>=</button>
        </div>
      </div>
    );
  }
  
  export default Calculadora;