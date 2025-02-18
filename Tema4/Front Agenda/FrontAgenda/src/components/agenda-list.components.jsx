import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import AgendaDataService from "../service/agenda.service";

export default function ContactList() {
    const [people, setPeople] = useState([]);
    const [selectedPerson, setSelectedPerson] = useState(null);
    const navigate = useNavigate();

    // Obtener lista de contactos al cargar la página
    useEffect(() => {
        fetchPeople();
    }, []);

    const fetchPeople = () => {
        AgendaDataService.getAll()
            .then(response => {
                setPeople(response.data || []);
            })
            .catch(error => {
                console.error("Error al obtener los contactos:", error);
            });
    };

    // Función para eliminar un contacto
    const deletePerson = () => {
        if (!selectedPerson) {
            alert("Seleccione un contacto para eliminar.");
            return;
        }

        if (window.confirm(`¿Está seguro de eliminar a ${selectedPerson.name}?`)) {
            AgendaDataService.delete(selectedPerson.dni) // Se usa ID en lugar de `dni`
                .then(() => {
                    setPeople(people.filter(person => person.dni !== selectedPerson.dni));
                    setSelectedPerson(null);
                })
                .catch(error => {
                    console.error("Error al eliminar el contacto:", error);
                });
        }
    };

    // Función para redirigir a la pantalla de edición
    const editPerson = () => {
        if (!selectedPerson || !selectedPerson.dni) {
            alert("Seleccione un contacto válido para editar.");
            return;
        }
        navigate(`/edit-person/${selectedPerson.dni}`);
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
                                key={person.dni || index} // Se usa `person.id`, y si es undefined, se usa `index`
                                className={`cursor-pointer hover:bg-gray-700 ${selectedPerson?.id === person.id ? "bg-gray-600" : ""}`}
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

                <div className="mt-6 flex gap-4">
                    <button className="px-4 py-2 bg-blue-500 rounded" onClick={() => navigate("/add")}>Nuevo</button>
                    <button className="px-4 py-2 bg-yellow-500 rounded" onClick={() => navigate(`/edit/${selectedPerson.dni}`)}>Editar</button>
                    <button className="px-4 py-2 bg-red-500 rounded" onClick={deletePerson} disabled={!selectedPerson}>Borrar</button>
                </div>
            </div>
        </div>
    );
}
