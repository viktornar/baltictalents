import React from 'react'
import { Link } from 'react-router-dom';

export default function Navigation() {
  return (
    <div>
      <ul>
        <li>
          <Link to="/">Welcome</Link>
        </li>
        <li>
          <Link to="/products">Products</Link>
        </li>
      </ul>
      <hr />
    </div>
  )
}
