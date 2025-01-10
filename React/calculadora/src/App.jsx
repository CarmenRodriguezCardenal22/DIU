import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Calculadora from './components/calculadora'
import {evaluate} from 'mathjs';

function App() {
  const [n, setN] = useState(''); 
  const [isResult, setIsResult] = useState(false); 

  const texto = (valor) => {
    if (isResult && !isNaN(valor)) {
      setN(valor);
    } else {
      setN((prev) => prev + valor);
    }
    setIsResult(false); 
  };

  const limpiar = () => {
    setN('');
    setIsResult(false); 
  };

  const igual = () => {
    try {
      const resultado = evaluate(n);
      setN(String(resultado));
      setIsResult(true); 
    } catch {
      setN('Error'); 
      setIsResult(false);
    }
  };

  const posNeg = () => {
    if (n && !isNaN(n)) {
      setN((prev) => String(-Number(prev))); 
    }
  };

  return (
    <>
      <div className="contenedor">
        <Calculadora
          n={n}
          texto={texto}
          limpiar={limpiar}
          igual={igual}
          posNeg={posNeg}
        />
      </div>
    </>
  );
}


export default App;