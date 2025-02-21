import React, { useState, useEffect, useContext } from 'react';
import monedasService from './../service/monedas.service.js';
import { MonedasContext } from './../providers/MonedasContext.jsx';
import './../styles/ProgressBar.css';

function ProgressBar() {
  const { progreso, setProgress } = useState(MonedasContext);

  const retrieveMonedas = async () => {
      try {
        const data = await monedasService.getAll();
        setMonedas(data);
        setProgress(data.length);
      } catch (e) {
        console.log(e);
      }
    };
  
    useEffect(() => {
      retrieveMonedas();
    }, []);

    const handleChange = () => {
      console.log(progreso)
      setProgress(progreso / 100)
    };

  return (
    <div className="container mt-3">
      <div className="progress">
        <div
          className="progress-bar bg-warning progress-bar-striped progress-bar-animated bg-primary"
          role="progressbar"
          // style={handleChange}
          
          aria-valuenow={handleChange}
          aria-valuemin="0"
          aria-valuemax="100"
        >
        </div>
        <div>
          {progreso < 100 ? (
            <p>Reserva no llena</p>
          ) : (
            <p>Reserva llena</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default ProgressBar;