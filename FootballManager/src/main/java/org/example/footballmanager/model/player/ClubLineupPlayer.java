package org.example.footballmanager.model.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.model.club.ClubLineup;
import org.example.footballmanager.utils.enums.MatchPosition;

@Setter
@Getter
@Entity
@Table(name = "club_lineup_players")
public class ClubLineupPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClubLineup lineup;
    @ManyToOne
    private ClubPlayer clubPlayer;
    @Enumerated(EnumType.STRING)
    private MatchPosition position;
}
