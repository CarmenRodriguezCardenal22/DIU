import React, { useState } from 'react';
import TutorialDataService from '../services/tutorial.service';

const AddTutorial = () => {
    const [id, setId] = useState('');
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [published, setPublished] = useState(false);
    const [submitted, setSubmitted] = useState(false);

    const onChangeId = (e) => {
        setId(e.target.value);
    };

    const onChangeTitle = (e) => {
        setTitle(e.target.value);
    };

    const onChangeDescription = (e) => {
        setDescription(e.target.value);
    };

    const onChangePublished = (e) => {
        setPublished(e.target.checked);
    };

    const saveTutorial = () => {
        const data = { id, title, description, published };

        TutorialDataService.create(data)
            .then(response => {
                const { id, title, description, published } = response.data;
                setId(id);
                setTitle(title);
                setDescription(description);
                setPublished(published ?? false);
                setSubmitted(true);
                console.log(response.data);
            })
            .catch(e => {
                console.error("Error al guardar el tutorial:", e);
            });
    };

    const newTutorial = () => {
        setId('');
        setTitle('');
        setDescription('');
        setPublished(false);
        setSubmitted(false);
    };

    return (
        <div className="submit-form">
            {submitted ? (
                <div>
                    <h4>Tutorial añadido con exito!</h4>
                    <button className="btn btn-success" onClick={newTutorial}>
                        Añadir
                    </button>
                </div>
            ) : (
                <div>
                    <div className="form-group">
                        <label htmlFor="id">Id</label>
                        <input
                            type="text" className="form-control" id="id" required value={id} onChange={onChangeId} name="id"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="title">Titulo</label>
                        <input
                            type="text" className="form-control" id="title" required value={title} onChange={onChangeTitle} name="title"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="description">Descripcion</label>
                        <input
                            type="text" className="form-control" id="description" required value={description} onChange={onChangeDescription} name="description"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="published">Publicado&nbsp;</label>
                        <input
                            type="checkbox" id="published" checked={published} onChange={onChangePublished} name="published"
                        />
                    </div>

                    <button onClick={saveTutorial} className="btn btn-success">
                        Submit
                    </button>
                </div>
            )}
        </div>
    );
};

export default AddTutorial;

