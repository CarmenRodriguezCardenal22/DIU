import React, { Component } from 'react';
import './App.css'
import Tabla from './components/Tabla'
import Formulario from './components/Formulario'
import { Row, Col, Container } from 'react-bootstrap';

class App extends Component {
  constructor() {
    super()
    this.state = {
      data: [], 
      artist: '', 
      song: ''
    }
  }

  passParams = (data) => {
    this.setState((prevState) => ({
      data: [...prevState.data, data] 
    }));
  };
  componentDidMount() {
    if (this.state.artist && this.state.song) {
      fetch(`https://api.lyrics.ovh/v1/${this.state.artist}/${this.state.song}`)
        .then((response) => {
          if (response.ok) {
            return response.json();
          }
          throw new Error('Error al obtener los datos.');
        })
        .then((data) => {
          this.setState({ data: [data] });
        })
        .catch((error) => {
          console.error('Error fetching lyrics:', error);
        });
    }
  }

  render() {
    return (
      <Container>
        <Row>
          <Col>
            <Formulario passParams={this.passParams} />
          </Col>
        </Row>
        <Row>
          <Col>
            <Tabla data={this.state.data}/>
          </Col>
        </Row>
      </Container>
    );
  }
}


export default App
