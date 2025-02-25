import React from 'react';
import './styles/Navbar.css'; // Si tienes estilos específicos para la navbar, los importas aquí
import { Link } from 'react-router-dom'; // Importa Link para navegar entre rutas

const Navbar = () => {
  return (
    <div className="navbar">
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <div className="navbar-nav mr-auto">
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
    </div>
  );
};

export default Navbar;
