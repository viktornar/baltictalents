import React from 'react';
import { withRouter } from 'react-router';
import { Container, Image, Menu } from 'semantic-ui-react';
import logo from './fast-banana.svg';

function Navigation({ history }) {
  return (
    <div style={ { marginBottom: '20px' } }>
      <Menu>
        <Container>
          <Menu.Item as="a" header>
            <Image
              size="small"
              src={ logo } 
              onClick={ () => { history.push('/'); } }
            />
          </Menu.Item>

          <Menu.Menu position="right">
            <Menu.Item 
              as="a" name="products"
              onClick={ () => { history.push('/products'); } }
            >
              Products
            </Menu.Item>
          </Menu.Menu>
        </Container>
      </Menu>
    </div>
  )
}

export default withRouter(Navigation)