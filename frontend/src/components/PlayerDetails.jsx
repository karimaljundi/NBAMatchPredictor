import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './PlayerDetails.css'; // Import the CSS file for styling

const PlayerDetails = () => {
  const { playerName } = useParams();
  const [player, setPlayer] = useState(null);

  useEffect(() => {
    const fetchPlayerDetails = async () => {
      const response = await fetch(`http://localhost:8080/api/v1/player?name=${playerName}`);
      const data = await response.json();
      setPlayer(data[0]); // Assuming the API returns an array
    };
    fetchPlayerDetails();
  }, [playerName]);

  if (!player) return <div>Loading...</div>;
console.log(player);
  return (
    <div className="player-details">
      <h1>{player.name}</h1>
      <div className="player-info">
        <h2>Basic Information</h2>
        <div className="info-card">
          <p><strong>Position:</strong> {player.pos}</p>
          <p><strong>Team:</strong> {player.team}</p>
          <p><strong>Age:</strong> {player.age}</p>
          <p><strong>Games Played:</strong> {player.games_played}</p>
          <p><strong>Games Started:</strong> {player.games_started}</p>
          <p><strong>Year:</strong> {player.year}</p>
        </div>
      </div>

      <div className="player-stats">
        <h2>Statistics</h2>
        <table>
          <thead>
            <tr>
              <th>Stat</th>
              <th>Value</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Points</td>
              <td>{player.points}</td>
            </tr>
            <tr>
              <td>Assists</td>
              <td>{player.assists}</td>
            </tr>
            <tr>
              <td>Rebounds</td>
              <td>{player.rebounds}</td>
            </tr>
            <tr>
              <td>Steals</td>
              <td>{player.steals}</td>
            </tr>
            <tr>
              <td>Blocks</td>
              <td>{player.blocks}</td>
            </tr>
            <tr>
              <td>Field Goal Percentage</td>
              <td>{(player.field_goal_percentage * 100).toFixed(2)}%</td>
            </tr>
            <tr>
              <td>Three Points Percentage</td>
              <td>{(player.three_points_percentage * 100).toFixed(2)}%</td>
            </tr>
            <tr>
              <td>Free Throw Percentage</td>
              <td>{(player.free_throw_percentage * 100).toFixed(2)}%</td>
            </tr>
            <tr>
              <td>Minutes Played</td>
              <td>{player.minutes_played}</td>
            </tr>
            <tr>
              <td>Turnovers</td>
              <td>{player.turnovers}</td>
            </tr>
            <tr>
              <td>Personal Fouls</td>
              <td>{player.personal_fouls}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div className="player-awards">
        <h2>Awards</h2>
        <p>{player.awards ? player.awards : "No awards available."}</p>
      </div>
    </div>
  );
};

export default PlayerDetails;