// SignIn.components.jsx
import React, { useState } from "react";
import { signIn } from "../firebase";
import { useNavigate, Link } from "react-router-dom";
import logo from "../assets/logo.png";

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const user = await signIn(email, password);
    if (user) {
      navigate("/");  // Redirige al usuario a la página principal
    }
  };

  return (
    <div className="login-card">
      <img src={logo} alt="Logo" />
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Correo electrónico"
          required
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Contraseña"
          required
        />
        <button type="submit">Iniciar sesión</button>
      </form>
      <p className="text-center">
        Don't have an account? <Link to="/SignUp">Sign up here</Link>
      </p>
    </div>
  );
};

export default SignIn;
