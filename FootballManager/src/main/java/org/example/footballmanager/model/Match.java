package org.example.footballmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.utils.enums.MatchStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long homeClubId;
    private Long awayClubId;
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
    private LocalDateTime startTime;
    @ManyToMany
    private List<Player> homeSquad;
    @ManyToMany
    private List<Player> awaySquad;
    private Integer homeScore;
    private Integer awayScore;
    private Integer currentTick;
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<MatchEvent> events;
    @OneToOne(mappedBy = "match", cascade = CascadeType.ALL)
    private Ball ball;
}
