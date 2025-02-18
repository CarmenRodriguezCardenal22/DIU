import React, { Component } from "react";
import { Link, Route, Routes} from "react-router-dom";
import AgendaList from "./components/agenda-list.components.jsx";
import AddPerson from "./components/add-person.components.jsx";
import SignIn from "./components/SignIn.components.jsx";
import SignUp from "./components/SignUp.components.jsx";
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";

class App extends Component {
  render() {
    return (
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to="/FrontAgenda" className="nav-link">
                  Contactos
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/add" className="nav-link">
                  Add
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/SignIn" className="nav-link">
                  Loggin
                </Link>
              </li>
            </div>
          </nav>

          <div className="container mt-3">
            <Routes>
              <Route path="/" element={<AgendaList />} />
              <Route path="/FrontAgenda" element={<AgendaList />} />
              <Route path="/add" element={<AddPerson />} />
              <Route path="/SignIn" element={<SignIn />} />
              <Route path="/SignUp" element={<SignUp />} />
            </Routes>
          </div>
        </div>
    );
  }
}

export default App;
