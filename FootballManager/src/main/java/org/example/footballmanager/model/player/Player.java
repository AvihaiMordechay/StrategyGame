package org.example.footballmanager.model.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.model.embeddable.PlayerAttributes;
import org.example.footballmanager.utils.enums.PlayerPosition;

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
    private boolean hasClub;

    @Embedded
    private PlayerAttributes attributes;

    @Enumerated(EnumType.STRING)
    private PlayerPosition defaultPosition;
}
