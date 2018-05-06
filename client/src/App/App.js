import React, { Component } from 'react';
import './App.css';
import Nav from "./Nav";
import { BrowserRouter } from 'react-router-dom';
import { Route } from 'react-router-dom';
import Login from '../Login/Login';



class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <Nav/>
          <Route path="/login" component={Login} />

        </div>
      </BrowserRouter>
    );
  }
}

export default App;
