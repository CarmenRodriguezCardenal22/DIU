import React, { Component } from "react";
import { Link, Route, Routes, Navigate } from "react-router-dom";
import AgendaList from "./components/agenda-list.components.jsx";
import AddPerson from "./components/add-person.components.jsx";
import SignIn from "./components/SignIn.components.jsx";
import SignUp from "./components/SignUp.components.jsx";
import EditPerson from "./components/edit-person.components.jsx";
import TutorialList from "./components/TutorialsList.jsx";
import PrivateRoute from "./components/PrivateRoute.jsx";
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar.jsx";

class App extends Component {
  render() {
    return (
      <div>
        <Navbar />
        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<AgendaList />} />
            <Route path="/edit/:id" element={<EditPerson />} />
            <Route path="/SignIn" element={<SignIn />} />
            <Route path="/SignUp" element={<SignUp />} /> 
            <Route path="/FrontAgenda" element={<AgendaList />} />
            <Route path="/add" element={<AddPerson />} />
            <Route path="/tutorials/:personId" element={<TutorialList />} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;
