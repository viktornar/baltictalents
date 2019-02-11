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

class NewProduct extends Component {
  static carouselSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  }

  state = { name: '', description: '', price: '', images: [] };

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
    const { history } = this.props;

    axios.post('/api/products/new', { ...this.state }).then(() => {
      history.push('/products');
    });
  }

  render() {
    return (
      <Container>
        <Form onSubmit={this.handleSubmit}>
          <Form.Field 
            control={ Input }
            name='name'
            label='Product name'
            placeholder='Product name ...'
            onChange={ this.handleChange }
          />
          <Form.Field 
            control={ TextArea }
            name='description'
            label='Product description' 
            placeholder='Tell more about you product ...'
            onChange={ this.handleChange } 
          />
          <Form.Field 
            control={ Input }
            name='price'
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
              <Slider { ...NewProduct.carouselSettings }>
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
    )
  }
}

export default withRouter(NewProduct);