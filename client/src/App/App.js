import React, { Component } from 'react';
import './App.css';
import Nav from "./Nav";
import { BrowserRouter } from 'react-router-dom';
import { Route, Redirect } from 'react-router-dom';
import Login from '../Login/Login';
import Dashboard from '../Dashboard';
import {authenticationFailure} from "../Authentication";
import {connect} from "react-redux";



class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <Nav/>
          <Route path="/login" component={Login} />
          <Route exact path="/" render={() => (
            !this.props.authenticated ? (
              <Redirect to="/login"/>
            ) : (
              <Dashboard/>
            )
          )}/>
        </div>
      </BrowserRouter>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    authenticated: state.authentication.authenticated
  }
}

export default connect(mapStateToProps)(App);
