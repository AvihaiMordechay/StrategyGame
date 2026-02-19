package org.example.footballmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.model.embeddable.PlayerAttributes;
import org.example.footballmanager.utils.enums.PlayerPosition;
import org.example.footballmanager.utils.enums.PlayerStatus;

@Setter
@Getter
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;
    @Enumerated(EnumType.STRING)
    private PlayerStatus currentStatus;
    @Embedded
    private PlayerAttributes attributes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;
}
