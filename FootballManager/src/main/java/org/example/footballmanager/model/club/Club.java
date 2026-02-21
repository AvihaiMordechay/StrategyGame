package org.example.footballmanager.model.club;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.model.player.ClubPlayer;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int budget;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubPlayer> team;

    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClubLineup currentLineup;
}
