import { useState, useEffect } from "react";
import AgendaDataService from "../service/agenda.service";
import TutorialDataService from "../service/tutorials.service";

const AddPerson = ({ updateContacts }) => {
    const [dni, setDni] = useState('');
    const [name, setName] = useState('');
    const [lastname, setLastname] = useState('');
    const [street, setStreet] = useState('');
    const [city, setCity] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [birthday, setBirthday] = useState('');
    const [submitted, setSubmitted] = useState(false);
    const [tutorials, setTutorials] = useState([]);
    const [selectedTutorials, setSelectedTutorials] = useState([]);

    useEffect(() => {
        TutorialDataService.getAll()
            .then(response => {
                setTutorials(response.data || []);
            })
            .catch(e => {
                console.error("Error al obtener los tutoriales:", e);
            });
    }, []);

    const toggleTutorialSelection = (id) => {
        setSelectedTutorials(prevSelected =>
            prevSelected.includes(id)
                ? prevSelected.filter(tutorialId => tutorialId !== id)
                : [...prevSelected, id]
        );
    };

    const savePerson = () => {
        const data = { dni, name, lastname, street, city, postalCode, birthday, tutorials: selectedTutorials };

        AgendaDataService.create(data)
            .then(() => {
                setSubmitted(true);
                updateContacts();
            })
            .catch(e => {
                console.error("Error al guardar el contacto:", e);
            });
    };

    const newPerson = () => {
        setDni('');
        setName('');
        setLastname('');
        setStreet('');
        setCity('');
        setPostalCode('');
        setBirthday('');
        setSelectedTutorials([]);
        setSubmitted(false);
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>Contacto añadido con éxito!</h4>
                    <button className="btn btn-success" onClick={newPerson}>
                        Añadir otro
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="dni">DNI</label>
                        <input type="text" className="form-control" id="dni" required value={dni} onChange={(e) => setDni(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Nombre</label>
                        <input type="text" className="form-control" id="name" required value={name} onChange={(e) => setName(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="lastname">Apellido</label>
                        <input type="text" className="form-control" id="lastname" required value={lastname} onChange={(e) => setLastname(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="street">Calle</label>
                        <input type="text" className="form-control" id="street" required value={street} onChange={(e) => setStreet(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="city">Ciudad</label>
                        <input type="text" className="form-control" id="city" required value={city} onChange={(e) => setCity(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="postalCode">Código Postal</label>
                        <input type="text" className="form-control" id="postalCode" required value={postalCode} onChange={(e) => setPostalCode(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="birthday">Fecha de Nacimiento</label>
                        <input type="date" className="form-control" id="birthday" required value={birthday} onChange={(e) => setBirthday(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label>Tutoriales</label>
                        {tutorials.map((tutorial) => (
                            <div key={tutorial.id}>
                                <input type="checkbox" checked={selectedTutorials.includes(tutorial.id)} onChange={() => toggleTutorialSelection(tutorial.id)} />
                                <label>{tutorial.title}</label>
                            </div>
                        ))}
                    </div>

                    <button type="submit" onClick={savePerson} className="btn btn-success">
                        Guardar
                    </button>
                </div>
            )}
        </div>
    );
};

export default AddPerson;
