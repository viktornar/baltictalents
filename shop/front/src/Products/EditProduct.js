import React, { Component } from 'react';
import axios from 'axios';
import { 
  Container, 
  Button, 
  Form, 
  Input, 
  TextArea,
  Segment,
  Image,
} from 'semantic-ui-react';
import { withRouter } from 'react-router';
import Slider from 'react-slick';

import ImageField from './ImageField';

class EditProduct extends Component {
  static carouselSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  }

  state = { name: '', description: '', price: '', images: [] };

  componentDidMount() {
    const { location } = this.props;
    this.fetchProductById(location.state.productId);
  }

  fetchProductById(productId) {
    axios(`/api/products/${productId}`)
      .then(({ data }) => {
        this.setState({ ...data });
      })
      .catch((error) => {
        console.log(error.response);
      });
  }
  
  handleChange = (e, { name, value }) => {
    if (name === 'images') {
      this.setState({ 
        images: [...this.state.images, { name: value.name, url: value.url }]
      });
    } else {
      this.setState({ [name]: value });
    }
  };

  handleSubmit = () => {
    const { history, location: { state: { productId } } } = this.props;

    axios.put(`/api/products/edit/${productId}`, { ...this.state }).then(() => {
      history.push('/products');
    });
  }

  render() {
    const { name, description, price } = this.state;

    return (
      <div>
        <Container>
          <Form onSubmit={this.handleSubmit}>
            <Form.Field 
              control={ Input }
              name='name'
              value={ name }
              label='Product name'
              placeholder='Product name ...'
              onChange={ this.handleChange }
            />
            <Form.Field 
              control={ TextArea }
              name='description'
              value={ description }
              label='Product description' 
              placeholder='Tell more about you product ...'
              onChange={ this.handleChange } 
            />
            <Form.Field 
              control={ Input }
              name='price'
              value={ price }
              step='0.01'
              label='Product price'
              placeholder='Product price ...'
              type='number'
              onChange={ this.handleChange }
            />
            <Form.Field
              control={ ImageField }
              name='images'
              label='Product image'
              placeholder='Product image ...'
              onChange={ this.handleChange }
            />
            {
              this.state.images.length > 0 && 
              <Segment style={ { padding: '40px' } } >
                <Slider { ...EditProduct.carouselSettings }>
                  {
                    this.state.images.map((image, imageIdx) => (
                      <Image key={ imageIdx } src={ image.url } />
                    ))
                  }
                </Slider>
              </Segment>
            }
            <Form.Field control={ Button }>
              Submit
            </Form.Field>
          </Form>
        </Container>
      </div>
    )
  }
}

export default withRouter(EditProduct);
