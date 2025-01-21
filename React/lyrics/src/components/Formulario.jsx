import React, { Component } from 'react'

class Formulario extends Component {
    constructor() {
        super()
        this.state = {
            userId: '',
            title: '',
            body: ''
        }
    }
    handleChange = event => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({ [name]: value });
    }
    handleSubmit = event => {
        event.preventDefault();
        fetch('https://api.lyrics.ovh/v1/${artist}/${song}', {
            method: 'POST', 
            body: JSON.stringify({ 
              title: this.state.title,
              body: this.state.body,
              userId: this.state.userId
            }),
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }
          })
          .then((response) => { 
            if (response.ok) {
                return response.json();
            } else {
                throw new Error(response.statusText);
            }
        })
        .then(data => { 
            this.props.passParams(data); 
        })
    }

    render() {
        return (
        <Form onSubmit={this.handleSubmit}>
            <Row>
                <Col>
                    <Form.Group>
                        <Form.Label>User ID</Form.Label>
                        <Form.Control required type="number" min="1" placeholder="Enter user ID" name="userId" value={this.state.userId} onChange={this.handleChange} />
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group>
                        <Form.Label>Title:</Form.Label>
                        <Form.Control required placeholder="Enter title" name="title" value={this.state.title} onChange={this.handleChange} />
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group>
                        <Form.Label>Body:</Form.Label>
                        <Form.Control required placeholder="Enter body" name="body" value={this.state.body} onChange={this.handleChange} />
                    </Form.Group>
                </Col>
            </Row>
            <Row>
                <FormGroup>
                    <Button type="submit" >Add </Button>
                </FormGroup>
            </Row>
        </Form>)
    }

}

export default Formulario