import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css'; // Import the CSS file for styling

const Home = () => {
  return (
    <div className="home">
      <header className="home-header">
        <h1>NBA Player Directory</h1>
        <Link to="/search" className="search-button">Search for Players</Link>
      </header>
      <main className="home-content">
        <section className="intro">
          <h2>Welcome to the NBA Player Directory</h2>
          <p>Explore players by their positions or teams. Click below to get started!</p>
        </section>
      </main>
    </div>
  );
};

export default Home;