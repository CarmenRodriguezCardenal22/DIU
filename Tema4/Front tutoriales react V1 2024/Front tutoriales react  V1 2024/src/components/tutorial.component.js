import React, { useState, useEffect } from 'react';
import TutorialDataService from '../services/tutorial.service';
import { useParams, useHistory } from 'react-router-dom';

const EditTutorial = () => {
    const { id } = useParams();
    const history = useHistory();
    const [currentTutorial, setCurrentTutorial] = useState({
        id: '',
        title: '',
        description: '',
        published: false
    });
    const [message, setMessage] = useState('');

    useEffect(() => {
        getTutorial(id);
    }, [id]);

    const onChangeTitle = (e) => {
        const title = e.target.value;
        setCurrentTutorial(prevState => ({
            ...prevState,
            title: title
        }));
    };

    const onChangeDescription = (e) => {
        const description = e.target.value;
        setCurrentTutorial(prevState => ({
            ...prevState,
            description: description
        }));
    };

    const onChangePublished = (e) => {
        const published = e.target.checked;
        setCurrentTutorial(prevState => ({
            ...prevState,
            published: published
        }));
    };

    const getTutorial = (id) => {
        TutorialDataService.get(id)
            .then(response => {
                setCurrentTutorial(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const updateTutorial = () => {
        TutorialDataService.update(currentTutorial.id, currentTutorial)
            .then(response => {
                setMessage('Tutorial actualizado con exito!');
            })
            .catch(e => {
                console.log(e);
            });
    };

    const deleteTutorial = () => {
        TutorialDataService.delete(currentTutorial.id)
            .then(response => {
                history.push('/tutorials');
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div>
            {currentTutorial ? (
                <div className="edit-form">
                    <h4>Tutorial</h4>
                    <form>
                        <div className="form-group">
                            <label htmlFor="id">Id</label>
                            <input type="text" className="form-control" id="id" value={currentTutorial.id} readOnly/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="title">Titulo</label>
                            <input type="text" className="form-control" id="title" value={currentTutorial.title} onChange={onChangeTitle}/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="description">Descripcion</label>
                            <input type="text" className="form-control" id="description" value={currentTutorial.description} onChange={onChangeDescription}/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="published">Publicado&nbsp;</label>
                            <input type="checkbox" id="published" checked={currentTutorial.published} onChange={onChangePublished}/>
                        </div>
                    </form>

                    <button className="badge badge-danger mr-2" onClick={deleteTutorial}>
                        Borrar
                    </button>

                    <button type="submit" className="badge badge-success" onClick={updateTutorial}>
                        Actualizar
                    </button>
                    <p>{message}</p>
                </div>
            ) : (
                <div>
                    <br />
                    <p>Por favor selecciona un tutorial</p>
                </div>
            )}
        </div>
    );
};

export default EditTutorial;
