import React from 'react';
import { FieldInput, Button, FieldError } from '../Control/Input';
import styled from 'styled-components';

const LoginButton = styled(Button)`
  align-self: flex-end;
`;

class LoginForm extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username: null,
      password: null,
      hasError: false,
      errorMessage: ''
    };
  }

  handleSubmit = (event) => {
    event.preventDefault();
    if (!this.state.username || !this.state.password) {
      this.setState({hasError: true, errorMessage: 'Username and password are required.'})
    } else {
      return fetch('/api/login', {
        body: JSON.stringify({username: 'test', password: 'password'}), // must match 'Content-Type' header
        headers: {
          'content-type': 'application/json'
        },
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
      })
        .then(response => {
          console.log('tesponse', response.json().then(j => { console.log( j)}));
        }) // parses response to JSON
    }
  };

  handleUsernameChange = (event) => {
    this.setState({username: event.target.value});
  };

  handlePasswordChange = (event) => {
    this.setState({password: event.target.value});
  };

  render() {
    return (
      <form className={this.props.className} onSubmit={this.handleSubmit}>
        <FieldInput onChange={this.handleUsernameChange} type="text" label="Username" hasError={this.state.hasError}></FieldInput>
        <FieldInput onChange={this.handlePasswordChange} type="password" label="Password" hasError={this.state.hasError}></FieldInput>
        {
          this.state.hasError ? <FieldError>{this.state.errorMessage}</FieldError> : null
        }
        <LoginButton type="submit">Login</LoginButton>
      </form>
    )
  }
}

export default styled(LoginForm)`
  width: 300px;
  margin: 0 auto;
  padding: 20px;
  background: rgba(0, 0, 0, 0.05);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
`