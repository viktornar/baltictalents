import React, { PureComponent } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import Navigation from './Navigation';
import Products from './Products';
import Index from './Index';

import './App.css';

class App extends PureComponent {
  render() {
    return (
      <Router>
        <div>
          <Navigation />

          <hr />

          <Route exact path="/" component={Index} />
          <Route path="/products" component={() => <Products />} />
        </div>
      </Router>
    );
  }
}

export default App;
