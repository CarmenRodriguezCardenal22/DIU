import React, { useState } from "react";
import { Link } from "react-router-dom";
import { signInWithGoogle } from "../firebase";
import { auth } from "../firebase";
import "./styles/SignIn.css";
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
        <div className={`bars ${strength}`}>
          <div></div>
        </div>
        <div className="strength">{strength && <>{strength} password</>}</div>
        <button className="control" type="submit">
          JOIN NOW
        </button>
      </form>
      <p className="text-center">
        Don't have an account? <Link to="SignUp">Sign up here</Link>
      </p>
    </div>
  );
};

export default SignIn;
