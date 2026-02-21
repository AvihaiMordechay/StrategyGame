package org.example.footballmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.footballmanager.model.embeddable.PlayerAttributes;
import org.example.footballmanager.utils.enums.PlayerPosition;

@Getter
public class PlayerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private boolean hasClub;
    private PlayerAttributes attributes;
    private PlayerPosition position;

    public PlayerDTO(Long id, String firstName, String lastName, int age, boolean hasClub, PlayerAttributes attributes, PlayerPosition position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hasClub = hasClub;
        this.attributes = attributes;
        this.position = position;
    }
}
