import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import TutorialDataService from "../service/tutorials.service";

export default function TutorialList() {
  const { personId } = useParams();
  const [tutorials, setTutorials] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    TutorialDataService.getByPersonId(personId)
      .then(response => setTutorials(response.data || []))
      .catch(error => console.error("Error al obtener los tutoriales:", error));
  }, [personId]);

  return (
    <div className="p-4">
      <h2 className="text-lg font-bold mb-4">Tutoriales del contacto</h2>
      {tutorials.length > 0 ? (
        <ul className="space-y-2">
          {tutorials.map((tutorial, index) => (
            <li key={index} className="p-2 border rounded bg-gray-700">
              <strong>{tutorial.title}</strong>
              <p>{tutorial.description}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No se encontraron tutoriales para este contacto.</p>
      )}
      <button onClick={() => navigate(-1)} className="mt-4 px-4 py-2 bg-blue-500 rounded">Volver</button>
    </div>
  );
}
