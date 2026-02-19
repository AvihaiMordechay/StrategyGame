package org.example.footballmanager.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PlayerAttributes {
    private int speed;
    private int strength;
}