import React from 'react';
import './../styles/Navbar.css'; // Si tienes estilos específicos para la navbar, los importas aquí
import { Link } from 'react-router-dom'; // Importa Link para navegar entre rutas

const Navbar = ({ user, onLogout }) => {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        {/* Enlaces existentes */}
        <li className="nav-item">
          <Link to="/FrontAgenda" className="nav-link">Contactos</Link>
        </li>
        <li className="nav-item">
          <Link to="/add" className="nav-link">Add</Link>
        </li>
        
        {/* Mostrar el botón de login solo si el usuario no está logueado */}
        {!user && (
          <li className="nav-item">
            <Link to="/SignIn" className="nav-link">Login</Link>
          </li>
        )}

        {/* Mostrar el botón de "Cerrar sesión" solo si el usuario está logueado */}
        {user && (
          <li className="nav-item" style={{ marginLeft: 'auto' }}>
            <button className="nav-link" onClick={onLogout} style={{ background: 'none', border: 'none', cursor: 'pointer', color: 'blue' }}>
              Cerrar sesión
            </button>
          </li>
        )}
      </div>
    </nav>
  );
};

export default Navbar;

