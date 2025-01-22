import React, { Component } from 'react'
import './Formulario.css';
import { Row, Col, Form, FormGroup, Button } from 'react-bootstrap';

class Formulario extends Component {
    constructor() {
        super()
        this.state = {
            artist: '',
            song: ''
        }
    }
    handleChange = event => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({ [name]: value });
    }
    handleSubmit = (event) => {
        event.preventDefault();
        fetch(`https://api.lyrics.ovh/v1/${this.state.artist}/${this.state.song}`)
          .then((response) => {
            if (response.ok) {
              return response.json();
            }
            throw new Error('No se encontraron las letras de la canción.');
          })
          .then((data) => {
            if (data && data.lyrics) {
              this.props.passParams({
                artist: this.state.artist,
                song: this.state.song,
                lyrics: data.lyrics,
              });
            } else {
              alert('No se encontraron las letras de la canción.');
            }
          })
          .catch((error) => {
            console.error('Error fetching the lyrics:', error);
            alert('Error al obtener las letras de la canción.');
          });
      };

    render() {
        return (
        <Form onSubmit={this.handleSubmit}>
            <Row>
                <Col>
                    <Form.Group>
                        <Form.Label>Cantante: </Form.Label>
                        <Form.Control required placeholder="Enter singer" name="artist" value={this.state.artist} onChange={this.handleChange} /> 
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group>
                        <Form.Label>Titulo: </Form.Label>
                        <Form.Control required placeholder="Enter title" name="song" value={this.state.song} onChange={this.handleChange} />
                    </Form.Group>
                </Col>
            </Row>
            <Row>
                <FormGroup>
                    <Button type="submit" >Buscar </Button>
                </FormGroup>
            </Row>
        </Form>)
    }

}

export default Formulario