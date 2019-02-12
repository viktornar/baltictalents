import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Card, Button } from 'semantic-ui-react';

class AddProduct extends Component {
  handleOnProductAdd = () => {
    const { history } = this.props;
    history.push('/products/new')
  }

  render() {
    return (
      <Card>
        <Card.Content>
          <Card.Header>Create new product</Card.Header>
        </Card.Content>
        <Card.Content extra>
            <div className='ui two buttons'>
              <Button basic color='green' onClick={ this.handleOnProductAdd }>
                Add
              </Button>
            </div>
        </Card.Content>
    </Card>
    )
  }
}

export default withRouter(AddProduct);
