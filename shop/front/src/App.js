import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import './App.css';
import Products from './Products';
import Navigation from './Navigation';
import Welcome from './Welcome';

class App extends Component {
  render() {
    return (
      <Router>
        <React.Fragment>
          <Navigation />
          <Route exact path="/" component={Welcome} />
          <Route path="/products" component={Products} />
        </React.Fragment>
      </Router>
    );
  }
}

export default App;
