package com.nba.nba_predictor.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/player")
public class PlayerController {
    private final PlayerService playerService;
    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String pos){
        if (team!=null && pos!=null){
            return playerService.getPlayersByTeamAndPosition(team, pos);
        } else if (name!=null) {
            return playerService.getPlayersByName(name);
        }else if (pos!=null){
            return playerService.getPlayersByPos(pos);
        }
        else if (team!=null){
            return playerService.getPlayersFromTeam(team);
        }
        else{
            return playerService.getPlayers();
        }
    }
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player createPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createPlayer, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player updatePlayer = playerService.updatePlayer(player);
        if (updatePlayer!=null) {
            return new ResponseEntity<>(updatePlayer, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deleteByName( @PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Deleted player complete", HttpStatus.OK);
    }
}
