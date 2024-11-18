package com.nba.nba_predictor.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;
    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }
    public List<Player> getPlayersByTeam(String teamName){
        return playerRepository.findAll().stream().filter(player -> teamName.equals(player.getTeam())).collect(Collectors.toList());
    }
    public List<Player> getPlayersByName(String playerName){
        return playerRepository.findAll().stream().filter(player -> playerName.toLowerCase().equals(player.getPname())).collect(Collectors.toList());
    }
    public List<Player> getPlayersByPos(String pos){
        return playerRepository.findAll().stream().filter(player -> pos.toLowerCase().equals(player.getPos())).collect(Collectors.toList());
    }
    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }
    public Player updatePlayer(Player player){
        Optional<Player> existPlayer = playerRepository.findByName(player.getPname());
        if (existPlayer.isPresent()){
            Player updatingPlayer = existPlayer.get();
            updatingPlayer.setPname(existPlayer.get().getPname());
            updatingPlayer.setTeam(existPlayer.get().getTeam());
            updatingPlayer.setAge(existPlayer.get().getAge());
            updatingPlayer.setAwards(existPlayer.get().getAwards());
            updatingPlayer.setPos(existPlayer.get().getPos());
            updatingPlayer.setRank(existPlayer.get().getRank());
            playerRepository.save(updatingPlayer);
            return updatingPlayer;
        }
        return null;


    }

    @Transactional
    public void deletePlayer(String playerName){
        playerRepository.deleteByName(playerName);

    }
}
