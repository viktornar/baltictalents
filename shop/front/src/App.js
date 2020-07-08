import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import 'matchmedia-polyfill';
import axios from 'axios';

import 'semantic-ui-css/semantic.min.css';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';


import Navigation from './Navigation';

import Welcome from './Welcome';
import Products from './Products';
import NewProduct from './Products/NewProduct';
import EditProduct from './Products/EditProduct';
import Purchase from './Purchase';



class App extends Component {
  state = { isAdmin: false };

  componentDidMount() {
    axios.get('/api/is-admin')
      .then(({ data }) => {
        this.setState({ isAdmin: data.isAdmin })
      })
      .catch((error) => {
        console.log(error.response);
      });
  }

  render() {
    return (
      <Router>
        <React.Fragment>
          <Navigation />
          <Switch>
            <Route exact path="/" component={ Welcome } />
            <Route path="/products/new" component={ NewProduct } />
            <Route path="/products/edit" component={ EditProduct } />
            <Route path="/products" component={ () => <Products isAdmin={ this.state.isAdmin } /> } />
            <Route path="/purchase" component={ Purchase } />
          </Switch>
        </React.Fragment>
      </Router>
    );
  }
}

export default App;
