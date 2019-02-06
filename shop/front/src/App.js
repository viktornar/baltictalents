import React, { Component } from 'react';
import axios from 'axios';

import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { products: [] };
  }

  componentDidMount() {
    axios('/api/products')
      .then(({ data }) => {
        this.setState({
          ...this.state,
          products: data,
        });
      })
      .catch((error) => {
        console.log(error.response);
      })
  }

  render() {
    const { products } = this.state;
    return (
      <div>
        <table>
          <thead>
            <tr>
              <th>
                Product id
              </th>
              <th>
                Product name
              </th>
              <th>
                Product description
              </th>
              <th>
                Product price
              </th>
            </tr>
          </thead>
          <tbody>
            {
              products.map((product, productId) => {
                return (
                  <tr key={ productId }>
                    <td>{ product.id }</td>
                    <td>{ product.name }</td>
                    <td>{ product.description }</td>
                    <td>{ product.price }</td>
                  </tr>
                );
              })
            }
          </tbody>
        </table>
      </div>
    );
  }
}

export default App;
