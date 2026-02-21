package org.example.footballmanager.model.match;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.model.player.MatchPlayer;
import org.example.footballmanager.utils.enums.Formation;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "match_lineups")
public class MatchLineup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Match match;

    @Enumerated(EnumType.STRING)
    private Formation formation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchPlayer> players;
}