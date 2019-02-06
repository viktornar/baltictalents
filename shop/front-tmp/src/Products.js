import React, { Component } from 'react'
import axios from 'axios';

import './Products.css';

export default class Products extends Component {
  constructor(props) {
    super(props);
    this.state = {
      products: [],
    };
  }

  componentDidMount() {
    axios("/api/products")
      .then(({ data }) => {
        this.setState({ ...this.state, products: data });
      })
      .catch((error) => {
        console.log(error.response);
      });
  }

  render() {
    const { products } = this.state;

    return (
      <div className="Products">
        <table className="Products__table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Description</th>
              <th>Images</th>
            </tr>
          </thead>
          <tbody>
            {
              products.map(({ id, name, description, images }, productId) => (
                <tr key={productId}>
                  <td>{id}</td>
                  <td>{name}</td>
                  <td>{description}</td>
                  <td>
                    <table>
                      {
                        images.map(({ id, name, url }, imageId) => (
                          <tr key={imageId} >
                            <td>{id}</td>
                            <td>{name}</td>
                            <td>{description}</td>
                          </tr>
                        ))
                      }
                    </table>
                  </td>
                </tr>
              ))
            }
          </tbody>
        </table>
      </div>
    )
  }
}
