import React, { useState } from "react";
import { Link } from "react-router-dom";
import { signInWithGoogle } from "../firebase";
import { auth } from "../firebase";
import "./SignIn.css";
import logo from "../assets/logo.png";

const strengthLabels = ["weak", "medium", "strong"];

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [strength, setStrength] = useState("");

  const signInWithEmailAndPasswordHandler = async (event) => {
    event.preventDefault();
    try {
      await auth.signInWithEmailAndPassword(email, password);
    } catch (error) {
      setError("Error signing in with password and email!");
      console.error("Error signing in with password and email", error);
    }
  };

  const onChangeHandler = (event) => {
    const { name, value } = event.currentTarget;
    if (name === "userEmail") setEmail(value);
    else if (name === "userPassword") setPassword(value);
  };

  const getStrength = (password) => {
    let strengthIndicator = -1;
    let upper = false,
      lower = false,
      numbers = false;

    for (let index = 0; index < password.length; index++) {
      const char = password.charCodeAt(index);
      if (!upper && char >= 65 && char <= 90) {
        upper = true;
        strengthIndicator++;
      }
      if (!numbers && char >= 48 && char <= 57) {
        numbers = true;
        strengthIndicator++;
      }
      if (!lower && char >= 97 && char <= 122) {
        lower = true;
        strengthIndicator++;
      }
    }
    setStrength(strengthLabels[strengthIndicator] ?? "");
  };

  const handlePasswordChange = (event) => {
    const value = event.target.value;
    setPassword(value);
    getStrength(value);
  };

  return (
    <div className="login-card">
      <img src={logo} alt="Logo" />
      <h2>Sign In</h2>
      {error && <div className="error-message">{error}</div>}
      <form className="login-form" onSubmit={signInWithEmailAndPasswordHandler}>
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
          name="userPassword"
          spellCheck="false"
          className="control"
          type="password"
          placeholder="Password"
          value={password}
          onChange={handlePasswordChange}
          required
        />
        <div className={`bars ${strength}`}>
          <div></div>
        </div>
        <div className="strength">{strength && <>{strength} password</>}</div>
        <button className="control" type="submit">
          JOIN NOW
        </button>
      </form>
      <p className="text-center">or</p>
      <button className="google-btn" onClick={signInWithGoogle}>
        Sign in with Google
      </button>
      <p className="text-center">
        Don't have an account? <Link to="SignUp">Sign up here</Link>
        <br />
        <Link to="passwordReset">Forgot Password?</Link>
      </p>
    </div>
  );
};

export default SignIn;
