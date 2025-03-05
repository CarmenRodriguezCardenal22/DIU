import React from 'react';
import './../styles/Navbar.css'; // Si tienes estilos específicos para la navbar, los importas aquí
import { Link } from 'react-router-dom'; // Importa Link para navegar entre rutas

const Navbar = () => {
  return (
        <nav className="navbar">
          <div className="navbar-container">
            <li className="nav-item">
              <Link to="/FrontAgenda" className="nav-link">Contactos</Link>
            </li>
            <li className="nav-item">
              <Link to="/add" className="nav-link">Add</Link>
            </li>
            <li className="nav-item">
              <Link to="/SignIn" className="nav-link">Login</Link>
            </li>
          </div>
        </nav>
  );
};

export default Navbar;
