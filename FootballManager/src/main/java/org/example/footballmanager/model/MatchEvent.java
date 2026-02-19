package org.example.footballmanager.model;

import jakarta.persistence.*;
import org.example.footballmanager.utils.enums.EventType;

@Entity
@Table(name = "match_events")
public class MatchEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int minute;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String description;
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
