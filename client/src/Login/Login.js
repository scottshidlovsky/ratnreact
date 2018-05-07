import React from 'react';
import { FieldInput, Button, FieldError } from '../Control/Input';
import styled from 'styled-components';
import { authenticate } from "../Authentication";
import {connect} from "react-redux";
import {Redirect} from 'react-router-dom';

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
      this.props.authenticate(this.state.username, this.state.password);
    }
  };

  handleUsernameChange = (event) => {
    this.setState({username: event.target.value});
  };

  handlePasswordChange = (event) => {
    this.setState({password: event.target.value});
  };

  renderLoginForm = () => {
    return (
      <form className={this.props.className} onSubmit={this.handleSubmit}>
        <FieldInput onChange={this.handleUsernameChange} type="text" label="Username" hasError={this.state.hasError}></FieldInput>
        <FieldInput onChange={this.handlePasswordChange} type="password" label="Password" hasError={this.state.hasError}></FieldInput>
        {
          this.state.hasError ? <FieldError>{this.state.errorMessage}</FieldError> : null
        }
        {
          this.props.loginFailed ? <FieldError>Invalid username or password.</FieldError> : null
        }
        <LoginButton type="submit">Login</LoginButton>
      </form>
    )
  };

  render() {
    return (
      <div>
      {
        this.props.authenticated ? <Redirect to="/" /> : this.renderLoginForm()
      }
      </div>
    )
  }
}

const styledForm = styled(LoginForm)`
  width: 300px;
  margin: 0 auto;
  padding: 20px;
  background: rgba(0, 0, 0, 0.05);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
`;

const mapStateToProps = (state) => {
  return {
    loginFailed: state.authentication.error,
    authenticated: state.authentication.authenticated
  }
};

export default connect(mapStateToProps, { authenticate })(styledForm);