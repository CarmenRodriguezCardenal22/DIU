import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import AgendaList from "./components/agenda-list.components.jsx";
import './App.css';

class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link to={"/FrontAgenda"} className="navbar-brand">
              Tutoriales
            </Link>
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/FrontAgenda"} className="nav-link">
                  Tutorials
                </Link>
              </li>
              <li className="nav-item">
                <Link to={"/add"} className="nav-link">
                  Add
                </Link>
              </li>
            </div>
          </nav>

          <div className="container mt-3">
            <Switch>
              {/* Asegúrate de usar Switch para v5.x */}
              <Route exact path={["/", "/FrontAgenda"]} component={AgendaList} />
              {/* Aquí puedes agregar otras rutas en el futuro */}
            </Switch>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
