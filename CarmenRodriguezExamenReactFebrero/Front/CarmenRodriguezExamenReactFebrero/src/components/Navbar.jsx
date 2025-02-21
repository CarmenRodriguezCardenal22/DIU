import React, { useContext, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { MonedasContext } from './../providers/MonedasContext.jsx';
import { Link, useNavigate } from 'react-router-dom';
import './../styles/Navbar.css';

function Navbar() {
  const [progress, setProgress] = useState(0);
  const navigate = useNavigate();

  const handleNavigation = (e) => {
    if (progress < 100) {
      navigate('/conversor');
    } else {
      e.preventDefault();
      alert('No puedes acceder a esta página porque el progreso es mayor o igual a 5.');
    }
  };

  return (
    <nav className="navbar navbar-expand-lg bg-white fixed-top">
      <div className="container-fluid">
        <button
          className="navbar-toggler d-lg-none"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link to="/conversor" className="nav-link text-dark" onClick={handleNavigation}>
                Conversor
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/productos" className="nav-link text-dark">
                Productos
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;