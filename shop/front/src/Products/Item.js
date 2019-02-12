import React from 'react';
import { withRouter } from 'react-router';
import { Button, Card, Image } from 'semantic-ui-react';
import Slider from 'react-slick';

class Item extends React.Component {
  static carouselSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  }

  render() {
    const { product, onItemAdd } = this.props;

    return (
      <Card>
        <Card.Content
          style={ {
            padding: '40px',
          } }>
          <Slider { ...Item.carouselSettings }>
            {
              product.images && product.images.map((image, imageIdx) => (
                <Image key={ imageIdx } src={ image.url } />
              ))
            }
          </Slider>
        </Card.Content>
        <Card.Content>
          <Card.Header>{ product.name }</Card.Header>
          <Card.Meta>{ `${product.price} Eur.` }</Card.Meta>
          <Card.Description>
            { product.description }
          </Card.Description>
        </Card.Content>
        <Card.Content extra>
            <div className='ui two buttons'>
                <Button basic color='green' onClick={ onItemAdd(product) }>
                    Add item to cart
                </Button>
            </div>
        </Card.Content>
      </Card>
    );
  }
}

export default withRouter(Item);
