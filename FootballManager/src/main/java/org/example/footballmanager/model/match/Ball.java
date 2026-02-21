package org.example.footballmanager.model.match;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.utils.enums.BallPhase;
import org.example.footballmanager.utils.enums.TeamSide;

@Setter
@Getter
@Entity
@Table(name = "balls")
public class Ball {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "match_id", unique = true)
    private Match match;
    private int positon;
    private BallPhase phase;
    @Enumerated(EnumType.STRING)
    private TeamSide possessionTeam;
}
