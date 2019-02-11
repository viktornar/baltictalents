import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import 'matchmedia-polyfill';

import 'semantic-ui-css/semantic.min.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import Welcome from './Welcome';
import Navigation from './Navigation';
import Products from './Products';
import NewProduct from './Products/NewProduct';
import EditProduct from './Products/EditProduct';
import Purchase from './Purchase';

class App extends Component {
  render() {
    return (
      <Router>
        <React.Fragment>
          <Navigation />
          <Switch>
            <Route exact path="/" component={ Welcome } />
            <Route path="/products/new" component={ NewProduct } />
            <Route path="/products/edit" component={ EditProduct } />
            <Route path="/products" component={ Products } />
            <Route path="/purchase" component={ Purchase } />
          </Switch>
        </React.Fragment>
      </Router>
    );
  }
}

export default App;
