import React, { Component } from "react";
import { Route, Routes } from "react-router-dom";
import AgendaList from "./components/agenda-list.components.jsx";
import AddPerson from "./components/add-person.components.jsx";
import SignIn from "./components/SignIn.components.jsx";
import EditPerson from "./components/edit-person.components.jsx";
import TutorialList from "./components/TutorialsList.jsx";
import Navbar from "./components/Navbar.jsx";
import ProgressBar from "./components/ProgressBar.jsx";
import AgendaDataService from "./service/agenda.service.js"; // Importamos el servicio
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOnline: navigator.onLine, // Estado para detectar conexión a internet
      contactsCount: 0, // Estado para la cantidad de contactos
    };
  }

  componentDidMount() {
    window.addEventListener("online", this.updateOnlineStatus);
    window.addEventListener("offline", this.updateOnlineStatus);
    this.updateContacts(); // Cargar el número de contactos al inicio
  }

  componentWillUnmount() {
    window.removeEventListener("online", this.updateOnlineStatus);
    window.removeEventListener("offline", this.updateOnlineStatus);
  }

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

  render() {
    return (
      <div>
        <Navbar />

        {!this.state.isOnline && (
          <div style={{ background: "red", color: "white", padding: "10px" }}>
            ⚠️ No tienes conexión a Internet. Verifica tu conexión.
          </div>
        )}

        <ProgressBar progress={this.state.contactsCount} />

        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<AgendaList updateContacts={this.updateContacts} />} />
            <Route path="/edit/:id" element={<EditPerson />} />
            <Route path="/SignIn" element={<SignIn />} />
            <Route path="/FrontAgenda" element={<AgendaList updateContacts={this.updateContacts} />} />
            <Route path="/add" element={<AddPerson updateContacts={this.updateContacts} />} />
            <Route path="/tutorials/:personId" element={<TutorialList />} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;
