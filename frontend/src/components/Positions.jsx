import React, { useEffect, useState } from 'react';
import PlayerList from './PlayerList';
import './Positions.css'; // Import the CSS file for styling

const Positions = () => {
  const [positions, setPositions] = useState({});
  const [expandedPositions, setExpandedPositions] = useState({});

  useEffect(() => {
    const fetchPositions = async () => {
      const response = await fetch('http://3.17.143.133:8080/api/v1/player');
      const data = await response.json();
      const groupedByPosition = data.reduce((acc, player) => {
        if (!acc[player.pos]) {
          acc[player.pos] = [];
        }
        acc[player.pos].push(player);
        return acc;
      }, {});
      setPositions(groupedByPosition);
    };
    fetchPositions();
  }, []);

  const toggleExpand = (pos) => {
    setExpandedPositions((prev) => ({
      ...prev,
      [pos]: !prev[pos],
    }));
  };

  return (
    <div className="positions">
      <h1>Players by Position</h1>
      <div className="card-container">
        {Object.keys(positions).map((pos) => (
          <div className="card" key={pos}>
            <h2 className="card-title">{pos}</h2>
            <PlayerList players={positions[pos]} limit={expandedPositions[pos] ? positions[pos].length : 5} />
            <button className="more-button" onClick={() => toggleExpand(pos)}>
              {expandedPositions[pos] ? 'Show Less' : 'More'}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Positions;