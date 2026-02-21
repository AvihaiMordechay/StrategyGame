package org.example.footballmanager.model.club;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.model.player.ClubLineupPlayer;
import org.example.footballmanager.utils.enums.Formation;

import java.util.List;

@Getter
@Setter
@Entity
public class ClubLineup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Formation formation;

    @OneToOne
    private Club club;

    @OneToMany(mappedBy = "lineup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubLineupPlayer> players;
}

