import React, { Component } from 'react';
import './App.css'
import Tabla from './components/Tabla'
import Formulario from './components/Formulario'

class App extends Component {
  constructor() {
    super()
    this.state = {
      data: [], 
      userId: '', 
      title: '', 
      body: ''
    }
  }

  passParams= (data) => {
    let dataNew = this.state.data; 
    dataNew.push(data) 
    this.setState({
      data: dataNew
    });
  }
  componentDidMount() {
    fetch('https://api.lyrics.ovh/v1/${artist}/${song}')
       .then((response) => { 
            if (response.ok) {
                return response.json(); 
            } else {
                throw new Error(response.statusText);
            }
        })
        .then((data) => { 
            this.setState({
                data: data
            });
        })
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
