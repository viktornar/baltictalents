import React, { Component } from 'react';
import { Container } from 'semantic-ui-react';

export default class Purchase extends Component {
  render() {
    const { location: { state: { items } } } = this.props;
    console.log(items);

    return (
      <Container>
        <p>Welcome to purchse page :)</p>
        {
          items.length > 0 && (
            <ul>
              {
                items.map((item, itemIdx) => (
                  <li key={itemIdx}>
                    { `${item.name} - ${item.description}` }
                  </li>
                ))
              }
            </ul>
          )
        }
      </Container>
    )
  }
}
