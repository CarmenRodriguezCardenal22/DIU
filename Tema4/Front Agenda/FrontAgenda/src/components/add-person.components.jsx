import { useState } from "react";
import AgendaDataService from "../service/agenda.service"; 
import axios from "axios";

const AddPerson = ({ updateContacts }) => {
    const [dni, setDni] = useState('');
    const [name, setName] = useState('');
    const [lastname, setLastname] = useState('');
    const [street, setStreet] = useState('');
    const [city, setCity] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [birthday, setBirthday] = useState('');
    const [submitted, setSubmitted] = useState(false);

    const savePerson = () => {
        const data = { dni, name, lastname, street, city, postalCode, birthday };

        AgendaDataService.create(data)
            .then(() => {
                setSubmitted(true);
                updateContacts(); // Actualizar la barra de progreso
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
        setSubmitted(false);  
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>Contacto añadido con exito!</h4>
                    <button className="btn btn-success" onClick={newPerson}>
                        Añadir
                    </button>
                </div>
            ) : (

                // <div>
                //     <input type="text" placeholder="DNI" value={dni} onChange={(e) => setDni(e.target.value)} />
                //     <input type="text" placeholder="Nombre" value={name} onChange={(e) => setName(e.target.value)} />
                //     <input type="text" placeholder="LastName" value={lastname} onChange={(e) => setLastName(e.target.value)} />
                //     <input type="text" placeholder="Street" value={street} onChange={(e) => setStreet(e.target.value)} />
                //     <input type="text" placeholder="City" value={city} onChange={(e) => setCity(e.target.value)} />
                //     <input type="text" placeholder="Postal Code" value={postalCode} onChange={(e) => setPostalCode(e.target.value)} />
                //     <input type="date" placeholder="Birthday" value={birthday} onChange={(e) => setBirthday(e.target.value)} />
                //     <button type="submit" onClick={savePerson} className="btn btn-success"/>
                    
                    
                //     {/* <button onClick={savePerson}>Guardar</button> */}
                // </div>
                <div>
                   <div className="form-group">
                       <label htmlFor="dni">Dni</label>
                       <input
                           type="text" className="form-control" id="dni" required value={dni} onChange={(e) => setDni(e.target.value) }
                       />
                   </div>
                   <div className="form-group">
                       <label htmlFor="name">Name</label>
                       <input
                           type="text" className="form-control" id="name" required value={name} onChange={(e) => setName(e.target.value)}
                       />
                   </div>
                   <div className="form-group">
                       <label htmlFor="lastname">LastName</label>
                       <input
                           type="text" className="form-control" id="lastname" required value={lastname} onChange={(e) => setLastname(e.target.value)}
                       />
                   </div>
                   <div className="form-group">
                       <label htmlFor="street">Street</label>
                       <input
                           type="text" className="form-control" id="street" required value={street} onChange={(e) => setStreet(e.target.value)}
                       />
                   </div>
                   <div className="form-group">
                       <label htmlFor="city">City</label>
                       <input
                           type="text" className="form-control" id="city" required value={city} onChange={(e) => setCity(e.target.value)}
                       />
                   </div>
                   <div className="form-group">
                       <label htmlFor="postalCode">Postal Code</label>
                       <input
                           type="text" className="form-control" id="postalCode" required value={postalCode} onChange={(e) => setPostalCode(e.target.value)}
                       />
                   </div>
                   <div className="form-group">
                       <label htmlFor="birthday">Birthday</label>
                       <input
                           type="date" className="form-control" id="birthday" required value={birthday} onChange={(e) => setBirthday(e.target.value)}
                       />
                   </div>
                   <button type="submit" onClick={savePerson} className="btn btn-success">
                       Submit
                   </button>
                </div> 
            )
        }        
        </div>    
    );
};
export default AddPerson;

