import React, { useState } from 'react';
//import { signInWithGoogle, generateUserDocument } from './firebase'; // Importa las funciones de Firebase
//import AuthForm from './AuthForm'; // Componente reutilizable para formularios

const SignIn = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Aquí añades la lógica para el login con email y contraseña (si tienes Firebase Auth configurado)
      console.log("Iniciando sesión...");
    } catch (error) {
      setError(error.message);
    }
  };

  const handleGoogleSignIn = () => {
    signInWithGoogle()
      .then((user) => generateUserDocument(user))
      .catch((error) => setError(error.message));
  };

  return (
    <div className="login-card">
      <h2>Iniciar Sesión</h2>
      <AuthForm
        onSubmit={handleSubmit}
        fields={[
          { label: 'Email', type: 'email', value: email, onChange: setEmail },
          { label: 'Contraseña', type: 'password', value: password, onChange: setPassword },
        ]}
      />
      <button className="google-btn" onClick={handleGoogleSignIn}>Iniciar sesión con Google</button>
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default SignIn;
