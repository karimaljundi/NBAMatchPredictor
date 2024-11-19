import React, { useEffect, useState } from 'react';
import PlayerList from './PlayerList';
import './Teams.css'; // Import the CSS file for styling

const Teams = () => {
  const [teams, setTeams] = useState({});
  const [expandedTeams, setExpandedTeams] = useState({});

  useEffect(() => {
    const fetchTeams = async () => {
      const response = await fetch('http://localhost:8080/api/v1/player');
      const data = await response.json();
      const groupedByTeam = data.reduce((acc, player) => {
        if (!acc[player.team]) {
          acc[player.team] = [];
        }
        acc[player.team].push(player);
        return acc;
      }, {});
      setTeams(groupedByTeam);
    };
    fetchTeams();
  }, []);

  const toggleExpand = (team) => {
    setExpandedTeams((prev) => ({
      ...prev,
      [team]: !prev[team],
    }));
  };

  return (
    <div className="teams">
      <h1>Players by Team</h1>
      <div className="card-container">
        {Object.keys(teams).map((team) => (
          <div className="card" key={team}>
            <h2 className="card-title">{team}</h2>
            <PlayerList players={teams[team]} limit={expandedTeams[team] ? teams[team].length : 5} />
            <button className="more-button" onClick={() => toggleExpand(team)}>
              {expandedTeams[team] ? 'Show Less' : 'More'}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Teams;