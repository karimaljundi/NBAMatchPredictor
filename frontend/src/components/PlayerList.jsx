import React from 'react';
import { Link } from 'react-router-dom';
import './PlayerList.css'; // Import the CSS file for styling

const PlayerList = ({ players, limit }) => {
    console.log(players);
  return (
    <ul className="player-list">
      {players.slice(0, limit).map((player) => (
        <li key={player.name} className="player-item">
          <Link to={`/player/${player.name}`} className="player-link">
            {player.name}
          </Link>
          {' - ' + player.pos + ' - ' + player.team}
        </li>
      ))}
    </ul>
  );
};

export default PlayerList;