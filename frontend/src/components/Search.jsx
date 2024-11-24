import React, { useEffect, useState } from 'react';
import { useLocation, Link, useNavigate } from 'react-router-dom';
import './Search.css'; // Import the CSS file for styling

const Search = () => {
  const [players, setPlayers] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [hasSearched, setHasSearched] = useState(false);
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const name = params.get('name');
    if (name) {
      setSearchTerm(name);
      fetchPlayers(name);
      setHasSearched(true);
    }
  }, [location]);

  const fetchPlayers = async (name) => {
    const response = await fetch(`http://3.17.143.133:8080/api/v1/player?name=${name}`);
    const data = await response.json();
    setPlayers(data);
  };

  const handleSearch = (e) => {
    e.preventDefault();
    if (searchTerm) {
      navigate(`/search?name=${searchTerm}`);
    }
  };

  return (
    <div className="search">
      <h1>Search for Players</h1>
      <form onSubmit={handleSearch} className="search-form">
        <input
          type="text"
          placeholder="Enter player name..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-input"
        />
        <button type="submit" className="search-button">Search</button>
      </form>
      {hasSearched && (
        <h2>Search Results for "{searchTerm}"</h2>
      )}
      {players.length > 0 ? (
        <div className="card-container">
          <div className="card">
            <h2>Players Found</h2>
            <ul className="player-list">
              {players.map((player) => (
                <li key={player.name} className="player-item">
                  <Link to={`/player/${player.name}`} className="player-link">
                    {player.name}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
        </div>
      ) : (
        hasSearched && <p>No players found with that name.</p>
      )}
    </div>
  );
};

export default Search;