import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/NavBar';
import Home from './components/Home';
import Positions from './components/Positions';
import Teams from './components/Teams';
import PlayerDetails from './components/PlayerDetails';
import Search from './components/Search';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/positions" element={<Positions />} />
          <Route path="/teams" element={<Teams />} />
          <Route path="/player/:playerName" element={<PlayerDetails />} />
          <Route path="/search" element={<Search />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
