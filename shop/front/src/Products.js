import React from 'react';
import axios from 'axios';

import './Products.css';

export default class Products extends React.Component {
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
      });
  }

  render() {
    const { products } = this.state;

    return (
      <React.Fragment>
        <table className="Products">
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
      </React.Fragment>
    );
  }
}
