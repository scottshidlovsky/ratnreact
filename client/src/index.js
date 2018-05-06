import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App/App';
import registerServiceWorker from './registerServiceWorker';
import { injectGlobal } from 'styled-components';

injectGlobal`
  * {
    font-family: 'Roboto', sans-serif;
  }
`;

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
