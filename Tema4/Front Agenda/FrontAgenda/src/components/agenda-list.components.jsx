import { useState } from "react";

export default function ContactList() {
  const [selectedPerson, setSelectedPerson] = useState(null);
  const people = [];

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
            {people.map((person) => (
              <tr
                key={person.id}
                className="cursor-pointer hover:bg-gray-700"
                onClick={() => setSelectedPerson(person)}
              >
                <td className="p-2">{person.firstName}</td>
                <td className="p-2">{person.lastName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="w-3/5 p-4">
        <h2 className="text-lg font-bold">Detalles del contacto</h2>
        {selectedPerson ? (
          <div className="mt-4">
            <p><strong>Nombre:</strong> {selectedPerson.firstName}</p>
            <p><strong>Apellidos:</strong> {selectedPerson.lastName}</p>
            <p><strong>Dirección:</strong> {selectedPerson.street}</p>
            <p><strong>Ciudad:</strong> {selectedPerson.city}</p>
            <p><strong>Código Postal:</strong> {selectedPerson.postalCode}</p>
            <p><strong>Cumpleaños:</strong> {selectedPerson.birthday}</p>
          </div>
        ) : (
          <p className="mt-4">Seleccione una persona para ver los detalles.</p>
        )}

        <div className="mt-6 flex gap-4">
          <button className="px-4 py-2 bg-blue-500 rounded">Nuevo</button>
          <button className="px-4 py-2 bg-yellow-500 rounded">Editar</button>
          <button className="px-4 py-2 bg-red-500 rounded">Borrar</button>
        </div>
      </div>
    </div>
  );
}
