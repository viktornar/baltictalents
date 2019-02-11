import React, { Component } from 'react'
import { Button, Card, Image } from 'semantic-ui-react';

export default class ItemsCart extends Component {
  render() {
    const { itemsCount, onClear, onPurchase } = this.props;
    return (
      <Card>
        <Card.Content>
          <Card.Header>Your cart</Card.Header>
        </Card.Content>
        <Card.Content>
          <Image src={ `https://dummyimage.com/600x400/118039/fff&text=${itemsCount}` }/>
        </Card.Content>
        <Card.Content extra>
            <div className='ui two buttons'>
                <Button basic color='green' onClick={ onPurchase }>
                  Purchace
                </Button>
                <Button basic color='red' onClick={ onClear }>
                  Clear
                </Button>
            </div>
        </Card.Content>
      </Card>
    )
  }
}
