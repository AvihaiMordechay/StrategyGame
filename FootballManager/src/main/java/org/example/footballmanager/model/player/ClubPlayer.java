package org.example.footballmanager.model.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.model.club.Club;
import org.example.footballmanager.utils.enums.PlayerStatus;

@Setter
@Getter
@Entity
@Table(name = "club_players")
public class ClubPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Club club;

    private int shirtNumber;
    private double salary;

    @Enumerated(EnumType.STRING)
    private PlayerStatus clubStatus; // TODO: SEE IN THE FUTURE IF THIS CAN BE REMOVED
}