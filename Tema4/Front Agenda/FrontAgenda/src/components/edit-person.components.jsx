import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import AgendaDataService from "../service/agenda.service";

const EditPerson = () => {
    const { id } = useParams();
    const navigate = useNavigate();

    const [dni, setDni] = useState('');
    const [name, setName] = useState('');
    const [lastname, setLastname] = useState('');
    const [street, setStreet] = useState('');
    const [city, setCity] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [birthday, setBirthday] = useState('');
    const [submitted, setSubmitted] = useState(false);

    useEffect(() => {
        AgendaDataService.get(id)
            .then(response => {
                const { dni, name, lastname, street, city, postalCode, birthday } = response.data;
                setDni(dni);
                setName(name);
                setLastname(lastname);
                setStreet(street);
                setCity(city);
                setPostalCode(postalCode);
                setBirthday(birthday);
            })
            .catch(e => {
                console.error("Error al obtener el contacto:", e);
            });
    }, [id]);

    const onChangeDni = (e) => {
        setDni(e.target.value);
    };

    const onChangeName = (e) => {
        setName(e.target.value);
    };

    const onChangeLastName = (e) => {
        setLastname(e.target.value);
    };

    const onChangeStreet = (e) => {
        setStreet(e.target.value);
    };

    const onChangeCity = (e) => {
        setCity(e.target.value);
    };

    const onChangePostalCode = (e) => {
        setPostalCode(e.target.value);
    };

    const onChangeBirthday = (e) => {
        setBirthday(e.target.value);
    };

    const updatePerson = () => {
        const data = { dni, name, lastname, street, city, postalCode, birthday };

        AgendaDataService.update(id, data)
            .then(response => {
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.error("Error al actualizar el contacto:", e);
            });
    };

    const newPerson = () => {
        navigate("/add");
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>Contacto actualizado con exito!</h4>
                    <button className="btn btn-success" onClick={newPerson}>
                        Añadir
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="dni">Dni</label>
                        <input type="text" className="form-control" id="dni" required value={dni} onChange={onChangeDni} name="dni" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input type="text" className="form-control" id="name" required value={name} onChange={onChangeName} name="name" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="lastname">LastName</label>
                        <input type="text" className="form-control" id="lastname" required value={lastname} onChange={onChangeLastName} name="lastname" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="street">Street</label>
                        <input type="text" className="form-control" id="street" required value={street} onChange={onChangeStreet} name="street" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="city">City</label>
                        <input type="text" className="form-control" id="city" required value={city} onChange={onChangeCity} name="city" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="postalCode">Postal Code</label>
                        <input type="text" className="form-control" id="postalCode" required value={postalCode} onChange={onChangePostalCode} name="postalCode" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="birthday">Birthday</label>
                        <input type="date" className="form-control" id="birthday" required value={birthday} onChange={onChangeBirthday} name="birthday" />
                    </div>

                    <button type="submit" onClick={updatePerson} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
};

export default EditPerson;