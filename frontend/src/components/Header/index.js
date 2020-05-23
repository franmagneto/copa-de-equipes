import React from 'react';
import './styles.css';

function Header({ title, children }) {
  return (
    <div className="header-container">
      <h1>Copa</h1>
      <h2>{title}</h2>
      <hr />
      <h3>{children}</h3>
    </div>
  );
}

export default Header;
