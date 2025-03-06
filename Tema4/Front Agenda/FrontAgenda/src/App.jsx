import React, { Component } from "react";
import { Route, Routes, Navigate } from "react-router-dom"; // Importamos Navigate
import AgendaList from "./components/agenda-list.components.jsx";
import AddPerson from "./components/add-person.components.jsx";
import SignIn from "./components/SignIn.components.jsx";
import SignUp from "./components/SignUp.components.jsx";
import EditPerson from "./components/edit-person.components.jsx";
import TutorialList from "./components/TutorialsList.jsx";
import Navbar from "./components/Navbar.jsx";
import ProgressBar from "./components/ProgressBar.jsx";
import AgendaDataService from "./service/agenda.service.js"; // Importamos el servicio
import { auth, logOut } from './firebase.js'; // Importamos funciones de firebase
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOnline: navigator.onLine, // Estado para detectar conexión a internet
      contactsCount: 0, // Estado para la cantidad de contactos
      user: null, // Estado para mantener la información del usuario
    };
  }

  componentDidMount() {
    window.addEventListener("online", this.updateOnlineStatus);
    window.addEventListener("offline", this.updateOnlineStatus);
    this.updateContacts(); // Cargar el número de contactos al inicio
    this.checkUserAuthentication(); // Verifica si el usuario está logueado al iniciar la app
  }

  componentWillUnmount() {
    window.removeEventListener("online", this.updateOnlineStatus);
    window.removeEventListener("offline", this.updateOnlineStatus);
  }

  // Verificar si el usuario está logueado
  checkUserAuthentication = () => {
    auth.onAuthStateChanged((user) => {
      if (user) {
        this.setState({ user });
      } else {
        this.setState({ user: null });
      }
    });
  };

  // Actualizar la conexión a internet
  updateOnlineStatus = () => {
    this.setState({ isOnline: navigator.onLine });
  };

  // Función para actualizar la cantidad de contactos
  updateContacts = () => {
    AgendaDataService.getAll()
      .then(response => {
        this.setState({ contactsCount: response.data.length || 0 });
      })
      .catch(error => console.error("Error al obtener los contactos:", error));
  };

  // Función para cerrar sesión
  handleLogout = () => {
    logOut()
      .then(() => {
        this.setState({ user: null });
      })
      .catch((error) => {
        console.error("Error al cerrar sesión:", error);
      });
  };

  render() {
    const { user, isOnline, contactsCount } = this.state;

    return (
      <div>
        <div className="ancho">
          <Navbar user={user} onLogout={this.handleLogout} />
        </div>
        <div>
          {!isOnline && (
            <div style={{ background: "red", color: "white", padding: "10px" }}>
              ⚠️ No tienes conexión a Internet. Verifica tu conexión.
            </div>
          )}

          <ProgressBar progress={contactsCount} />

          <div className="container mt-3">
            <Routes>
              <Route path="/" element={<AgendaList user={user} updateContacts={this.updateContacts} />} />
              <Route path="/edit/:id" element={user ? <EditPerson /> : <Navigate to="/SignIn" />} />
              <Route path="/SignIn" element={<SignIn />} />
              <Route path="/SignUp" element={<SignUp />} />
              <Route path="/FrontAgenda" element={<AgendaList user={user} updateContacts={this.updateContacts} />} />
              <Route path="/add" element={user ? <AddPerson updateContacts={this.updateContacts} /> : <Navigate to="/SignIn" />} />
              <Route path="/tutorials/:personId" element={user ? <TutorialList /> : <Navigate to="/SignIn" />} />
            </Routes>
          </div>
        </div>
        
      </div>
    );
  }
}

export default App;
