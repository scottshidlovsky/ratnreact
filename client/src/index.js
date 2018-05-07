import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App/App';
import registerServiceWorker from './registerServiceWorker';
import { injectGlobal } from 'styled-components';
import { createEpicMiddleware } from "redux-observable";
import { rootEpic } from './epics';
import {applyMiddleware, combineReducers, compose, createStore} from "redux";
import { Provider, ReactRedux } from 'react-redux';
import {authenticationReducer} from "./Authentication";

injectGlobal`
  * {
    font-family: 'Roboto', sans-serif;
  }
`;


const epicMiddleware = createEpicMiddleware(rootEpic);

const rootReducer = combineReducers({
  authentication: authenticationReducer
});

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(
  rootReducer,
  composeEnhancers(
    applyMiddleware(epicMiddleware)
  )
);


ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root'));
registerServiceWorker();
