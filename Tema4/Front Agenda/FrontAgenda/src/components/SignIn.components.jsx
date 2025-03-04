import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./styles/SignIn.css";
import logo from "../assets/logo.png";


const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [strength, setStrength] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Aquí añades la lógica para el login con email y contraseña (si tienes Firebase Auth configurado)
      console.log("Iniciando sesión...");
    } catch (error) {
      setError(error.message);
    }
  };

  const onChangeHandler = (e) => {
    const { name, value } = e.target;
    if (name === 'userEmail') setEmail(value);
    if (name === 'userPassword') {
      setPassword(value);
      checkPasswordStrength(value);  // Llamar a la función para evaluar la fortaleza
    }
  };

  return (
    <div className="login-card">
      <img src={logo} alt="Logo" />
      <h2>Sign In</h2>
      {error && <p className="error-message">{error}</p>}
      <form className="login-form" onSubmit={handleSubmit}>
        <input
          autoComplete="off"
          spellCheck="false"
          className="control"
          type="email"
          name="userEmail"
          placeholder="Email"
          value={email}
          onChange={onChangeHandler}
          required
        />
        <input
          autoComplete="off"
          spellCheck="false"
          className="control"
          type="password"
          name="userPassword"
          placeholder="Contraseña"
          value={password}
          onChange={onChangeHandler}
          required
        />
        <button className="control" type="submit">
          Iniciar Sesión
        </button>
      </form>
    </div>
  );
};

export default SignIn;
