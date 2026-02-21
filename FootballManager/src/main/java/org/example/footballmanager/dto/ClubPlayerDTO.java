package org.example.footballmanager.dto;

import lombok.Getter;
import org.example.footballmanager.model.player.Player;
import org.example.footballmanager.utils.enums.PlayerStatus;

@Getter
public class ClubPlayerDTO {
    private Long id;
    private Player player;
    private int shirtNumber;
    private double salary;
    private PlayerStatus clubStatus;

    public ClubPlayerDTO(Long id, Player player, int shirtNumber, double salary, PlayerStatus clubStatus) {
        this.id = id;
        this.player = player;
        this.shirtNumber = shirtNumber;
        this.salary = salary;
        this.clubStatus = clubStatus;
    }
}
