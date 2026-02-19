package org.example.footballmanager.controller;

import org.example.footballmanager.dto.PlayerDTO;
import org.example.footballmanager.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.addPlayer(playerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/generatePlayers")
    public ResponseEntity<?> generatePlayers() {
        playerService.generatePlayers();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
