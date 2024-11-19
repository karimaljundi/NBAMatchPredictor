package com.nba.nba_predictor.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    void deleteByName(String name);
    Optional<Player> getPlayersByName(String name);
}
