package org.example.footballmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.example.footballmanager.dto.PlayerDTO;
import org.example.footballmanager.model.player.Player;
import org.example.footballmanager.repository.PlayerRepository;
import org.example.footballmanager.utils.generator.PlayerGenerator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerGenerator playerGenerator;

    public PlayerService(PlayerRepository playerRepository, PlayerGenerator playerGenerator) {
        this.playerRepository = playerRepository;
        this.playerGenerator = playerGenerator;
    }

    public void addPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setFirstName(playerDTO.getFirstName());
        player.setLastName(playerDTO.getLastName());
        player.setAge(playerDTO.getAge());
        player.setHasClub(false);
        player.setAttributes(playerDTO.getAttributes());
        player.setDefaultPosition(playerDTO.getPosition());
        playerRepository.save(player);
    }

    public void generatePlayers() {
        try {
            List<PlayerDTO> players = playerGenerator.generatePlayers();
            for (PlayerDTO dto : players) {
                addPlayer(dto);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate players", e);
        }
    }
}
