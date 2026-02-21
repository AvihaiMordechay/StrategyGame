package org.example.footballmanager.model.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.utils.enums.MatchPosition;

@Setter
@Getter
@Entity
@Table(name = "match_players")
public class MatchPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClubPlayer clubPlayer;

    @Enumerated(EnumType.STRING)
    private MatchPosition position;
}
