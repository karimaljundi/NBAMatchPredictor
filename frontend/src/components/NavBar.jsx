import React from 'react';
import { Link } from 'react-router-dom';
import './NavBar.css'; // Import the CSS file for styling

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-logo">NBA Player Directory</Link>
        <ul className="navbar-menu">
          <li><Link to="/" className="navbar-item">Home</Link></li>
          <li><Link to="/search" className="navbar-item">Search</Link></li>
          <li><Link to="/positions" className="navbar-item">Positions</Link></li>
          <li><Link to="/teams" className="navbar-item">Teams</Link></li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;