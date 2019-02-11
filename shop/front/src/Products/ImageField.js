import React, { Component } from 'react'

export default class ImageField extends Component {
  state = { name: '', url: 'https://dummyimage.com/600x400/b9bf88/1a171a.png' };

  handleOnChange = (e, { name, value }) => {
    this.setState({ [name]: value });
  };

  handleOnClick = (e) => {
    const { onChange, name: fielName } = this.props;
    const { name: imageName, url } = this.state;

    if (onChange) {
      onChange(e, { 
        name: fielName, 
        value: { 
          name: imageName, 
          url: url,
        } 
      });
    }

    this.setState({ name: '' });
  }

  render() {
    const { placeholder } = this.props;
    const { name } = this.state;

    return (
      <div className='field'>
        <div className='ui action input'>
          <input type='text' id='image' placeholder={ placeholder }
            value={ name }
            onChange={ 
              (e) => this.handleOnChange(e, { name: 'name', value: e.target.value }) 
            } 
          />
          <div className='ui button' onClick={ this.handleOnClick } >Add image</div>
        </div>
      </div>
    )
  }
}
