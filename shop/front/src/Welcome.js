import React, { Component } from 'react'

export default class Welcome extends Component {
  constructor(props) {
    super(props);
    console.log('[Welcome] - init');
  }
  
  componentDidMount() {
    console.log('[Welcome] - component did mount');
  }
  
  componentWillUnmount() {
    console.log('[Welcome] - component will unmount');
  }
  
  componentDidUpdate() {
    console.log('[Welcome] - component did update');
  }

  render() {
    console.log('[Welcome] - component is rendering')

    return (
      <div>
        Welcome to our shop :)
      </div>
    )
  }
}
