import React, { useState } from 'react';

const strengthLabels = ["weak", "medium", "strong"];

const SignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [strength, setStrength] = useState('');

  // Lógica para determinar la fortaleza de la contraseña
  const checkPasswordStrength = (password) => {
    let strength = "";
    const lengthCriteria = password.length >= 5;
    const numberCriteria = /[0-9]/.test(password);
    const specialCharCriteria = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const uppercaseCriteria = /[A-Z]/.test(password);

    if (lengthCriteria && (numberCriteria || specialCharCriteria) && uppercaseCriteria) {
      strength = "strong";
    } else if (lengthCriteria && (numberCriteria || specialCharCriteria)) {
      strength = "medium";
    } else if (lengthCriteria) {
      strength = "weak";
    }

    setStrength(strength);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Aquí añades la lógica para el registro con email y contraseña
      console.log("Registrando...");
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div className="login-card">
      <h2>Registrarse</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <input
          autoComplete="off"
          spellCheck="false"
          className="control"
          type="email"
          name="userEmail"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
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
          onChange={(e) => {
            setPassword(e.target.value);
            checkPasswordStrength(e.target.value);  // Llamar a la función para evaluar la fortaleza
          }}
          required
        />
        <div className={`bars ${strength}`}>
          <div></div>
        </div>
        <div className="strength">{strength && <>{strength} password</>}</div>
        <button className="control" type="submit">
          Registrar
        </button>
      </form>
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default SignUp;
