import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import AgendaDataService from "../service/agenda.service";

const EditPerson = () => {
    const { id } = useParams();
    const navigate = useNavigate();

    const [person, setPerson] = useState({
        dni: "",
        name: "",
        lastname: "",
        street: "",
        city: "",
        postalCode: "",
        birthday: ""
    });

    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState(null);
    const [isModified, setIsModified] = useState(false);

    useEffect(() => {
        AgendaDataService.get(id)
            .then(response => {
                setPerson(response.data);
            })
            .catch(e => {
                console.error("Error al obtener el contacto:", e);
                setError("No se pudo cargar el contacto.");
            });
    }, [id]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setPerson(prevState => ({
            ...prevState,
            [name]: value
        }));
        setIsModified(true);
    };

    const updatePerson = () => {
        AgendaDataService.update(id, person)
            .then(response => {
                setSubmitted(true);
                console.log("Contacto actualizado:", response.data);
            })
            .catch(e => {
                console.error("Error al actualizar el contacto:", e);
                setError("No se pudo actualizar el contacto.");
            });
    };

    const newPerson = () => {
        navigate("/FrontAgenda");
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>¡Contacto actualizado con éxito!</h4>
                    <button className="btn btn-success" onClick={newPerson}>
                        Volver
                    </button>
                </div>
            ) : (
                <div>
                    <h2>Editar Contacto</h2>
                    {error && <div className="alert alert-danger">{error}</div>}

                    <div className="form-group">
                        <label htmlFor="dni">DNI</label>
                        <input type="text" className="form-control" id="dni" required value={person.dni} onChange={handleInputChange} name="dni" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="name">Nombre</label>
                        <input type="text" className="form-control" id="name" required value={person.name} onChange={handleInputChange} name="name" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="lastname">Apellido</label>
                        <input type="text" className="form-control" id="lastname" required value={person.lastname} onChange={handleInputChange} name="lastname" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="street">Calle</label>
                        <input type="text" className="form-control" id="street" required value={person.street} onChange={handleInputChange} name="street" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="city">Ciudad</label>
                        <input type="text" className="form-control" id="city" required value={person.city} onChange={handleInputChange} name="city" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="postalCode">Código Postal</label>
                        <input type="text" className="form-control" id="postalCode" required value={person.postalCode} onChange={handleInputChange} name="postalCode" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="birthday">Fecha de Nacimiento</label>
                        <input type="date" className="form-control" id="birthday" required value={person.birthday} onChange={handleInputChange} name="birthday" />
                    </div>

                    <button type="submit" onClick={updatePerson} className="btn btn-success" disabled={!isModified}>
                        Actualizar
                    </button>
                </div>
            )}
        </div>
    );
};

export default EditPerson;

