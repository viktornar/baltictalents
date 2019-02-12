import React from 'react';
import axios from 'axios';
import { Container, Card } from 'semantic-ui-react';
import { withRouter } from 'react-router';

import Product from './Product';
import Item from './Item';
import AddProduct from './AddProduct';
import ItemsCart from './ItemsCart';
import './Products.scss';

class Products extends React.Component {
  constructor(props) {
    super(props);

    this.state = { products: [], isMobile: this.mediaQuery(), items: [] };
    this.resizeHandler = null;
  }

  componentDidMount() {
    this.addResizeHandler();
    this.fetchProducts();
  }

  componentWillUnmount() {
    window.removeEventListener('resize', this.resizeHandler);
  }

  mediaQuery() {
    if (matchMedia('(max-width: 960px)').matches) {
      return true;
    }

    return false;
  }

  addResizeHandler() {
    this.resizeHandler = window.addEventListener('resize', () => {
      this.setState({ isMobile: this.mediaQuery() });
    });
  }

  fetchProducts() {
    axios('/api/products')
      .then(({ data }) => {
        this.setState({ products: data });
      })
      .catch((error) => {
        console.log(error.response);
      });
  }

  handleProductDelete = (productId) => {
    axios.delete(`/api/products/${productId}`)
      .then(() => { this.fetchProducts(); });
  }

  handleItemAdd = (product) => () => {
    this.setState({
      items:[
        ...this.state.items,
        {
          name: product.name,
          price: product.price,
          description: product.description,
          productId: product.id,
        }
      ]
    });
  }

  handleItemsRemove = () => {
    this.setState({
      items:[],
    });
  }

  handleItemsPurchase = () => {
    const { history } = this.props;
    const { items } = this.state;

    history.push({
      pathname: '/purchase',
      state: { items }
    })
  }

  render() {
    const { products, isMobile, items } = this.state;
    const { isAdmin } = this.props;

    return (
      <div className='Products'>
        {
          isAdmin ? (
            <Container>
              <Card.Group itemsPerRow={ isMobile || products.length <= 1 ? 1 : 3 }>
                <React.Fragment>
                  <AddProduct />
                  {
                    products.map((product, productIdx) => {
                      return (
                          <Product
                            key={ productIdx }
                            product={ product }
                            onDelete={ this.handleProductDelete }
                          />
                        );
                    })
                  }
                </React.Fragment>
              </Card.Group>
            </Container>
          ) : (
            <Container>
              <Card.Group itemsPerRow={ isMobile || products.length <= 1 ? 1 : 3 }>
                <React.Fragment>
                  {
                    items.length > 0 && (
                      <ItemsCart
                        itemsCount={ items.length }
                        onPurchase={ this.handleItemsPurchase }
                        onClear={ this.handleItemsRemove }
                      />
                    )
                  }
                  {
                    products.map((product, productIdx) => {
                      return (
                        <Item
                          key={ productIdx }
                          product={ product }
                          onItemAdd={ this.handleItemAdd }
                        />
                      );
                    })
                  }
                </React.Fragment>
              </Card.Group>
            </Container>
          )
        }

      </div>
    );
  }
}

export default withRouter(Products);
