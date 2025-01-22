import React, { Component } from 'react'
import './Tabla.css'
import { Table } from 'react-bootstrap'

class Tabla extends Component {
    
    constructor() {
        super()
    }

    renderData(data, index) {
        return (
          <tr key={index}>
            <td>{index + 1}</td> {/* Usa el índice como ID */}
            <td>{data.artist}</td>
            <td>{data.song}</td>
            <td>{data.lyrics}</td> {/* Asegúrate de que 'lyrics' está presente */}
          </tr>
        );
      }

    render() {
        return (
            <Table responsive striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Artist</th>
                        <th>Song</th>
                        <th>Letra</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.data && this.props.data.map(this.renderData)}
                </tbody>
            </Table>)
    }
}

export default Tabla