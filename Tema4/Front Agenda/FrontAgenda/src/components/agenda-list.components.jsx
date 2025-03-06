import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import './../styles/List.css';
import AgendaDataService from "../service/agenda.service";
import { auth } from "../firebase"; // Importamos la autenticación de Firebase

export default function ContactList({ updateContacts }) {
    const [people, setPeople] = useState([]);
    const [selectedPerson, setSelectedPerson] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetchPeople();
    }, []);

    const fetchPeople = () => {
        AgendaDataService.getAll()
            .then(response => {
                setPeople(response.data || []);
                updateContacts(); // Actualizar la barra de progreso
            })
            .catch(error => console.error("Error al obtener los contactos:", error));
    };

    // Verificar si el usuario está logueado
    const isUserLoggedIn = () => {
        return auth.currentUser !== null;
    };

    // Manejo de la eliminación de una persona
    const deletePerson = () => {
        if (!isUserLoggedIn()) {
            alert("Debes iniciar sesión para eliminar un contacto.");
            navigate("/SignIn"); // Redirigir a la página de inicio de sesión
            return;
        }

        if (!selectedPerson) return alert("Seleccione un contacto para eliminar.");

        if (window.confirm(`¿Está seguro de eliminar a ${selectedPerson.name}?`)) {
            AgendaDataService.delete(selectedPerson.dni)
                .then(() => {
                    setPeople(people.filter(person => person.dni !== selectedPerson.dni));
                    setSelectedPerson(null);
                    updateContacts(); // Actualizar la barra de progreso
                })
                .catch(error => console.error("Error al eliminar el contacto:", error));
        }
    };

    // Consultar los tutoriales de una persona
    const consultarTutorials = () => {
        if (!isUserLoggedIn()) {
            alert("Debes iniciar sesión para consultar tutoriales.");
            navigate("/SignIn"); // Redirigir a la página de inicio de sesión
            return;
        }

        if (!selectedPerson?.dni) return alert("Seleccione un contacto válido.");
        navigate(`/tutorials/${selectedPerson.dni}`);
    };

    // Editar los detalles de una persona
    const editPerson = () => {
        if (!isUserLoggedIn()) {
            alert("Debes iniciar sesión para editar un contacto.");
            navigate("/SignIn"); // Redirigir a la página de inicio de sesión
            return;
        }

        if (!selectedPerson?.dni) return alert("Seleccione un contacto válido para editar.");
        navigate(`/edit/${selectedPerson.dni}`);
    };

    return (
        <div className="flex h-screen w-full bg-gray-800 text-white">
            <div className="w-2/5 border-r border-gray-600 p-4">
                <table className="w-full text-left">
                    <thead>
                        <tr className="border-b border-gray-600">
                            <th className="p-2">Nombre</th>
                            <th className="p-2">Apellidos</th>
                        </tr>
                    </thead>
                    <tbody>
                        {people.map((person, index) => (
                            <tr
                                key={person.dni || index}
                                className={`cursor-pointer hover:bg-gray-700 ${selectedPerson?.dni === person.dni ? "bg-gray-600" : ""}`}
                                onClick={() => setSelectedPerson(person)}
                            >
                                <td className="p-2">{person.name}</td>
                                <td className="p-2">{person.lastname}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            <div className="w-3/5 p-4">
                <h2 className="text-lg font-bold">Detalles del contacto</h2>
                {selectedPerson ? (
                    <div className="mt-4">
                        <p><strong>DNI:</strong> {selectedPerson.dni}</p>
                        <p><strong>Nombre:</strong> {selectedPerson.name}</p>
                        <p><strong>Apellidos:</strong> {selectedPerson.lastname}</p>
                        <p><strong>Dirección:</strong> {selectedPerson.street}</p>
                        <p><strong>Ciudad:</strong> {selectedPerson.city}</p>
                        <p><strong>Código Postal:</strong> {selectedPerson.postalCode}</p>
                        <p><strong>Cumpleaños:</strong> {selectedPerson.birthday}</p>
                    </div>
                ) : (
                    <p className="mt-4">Seleccione una persona para ver los detalles.</p>
                )}

                <div className="mt-6 gap-4">
                    <button className="px-4 py-2 bg-yellow-500 rounded" onClick={editPerson} disabled={!selectedPerson}>Editar</button>
                    <button className="px-4 py-2 bg-red-500 rounded" onClick={deletePerson} disabled={!selectedPerson}>Borrar</button>
                    <button className="px-4 py-2 bg-blue-500 rounded" onClick={consultarTutorials} disabled={!selectedPerson}>Consultar Tutoriales</button>
                </div>
            </div>
        </div>
    );
}
