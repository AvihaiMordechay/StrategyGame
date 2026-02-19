package org.example.footballmanager.dto;

import lombok.Getter;
import org.example.footballmanager.model.embeddable.PlayerAttributes;
import org.example.footballmanager.utils.enums.PlayerPosition;
import org.example.footballmanager.utils.enums.PlayerStatus;

@Getter
public class PlayerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private PlayerPosition position;
    private PlayerStatus currentStatus;
    private PlayerAttributes attributes;

    public PlayerDTO(Long id,
                     String firstName,
                     String lastName,
                     int age,
                     PlayerPosition position,
                     PlayerStatus currentStatus,
                     PlayerAttributes attributes)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
        this.currentStatus = currentStatus;
        this.attributes = attributes;
    }

}
