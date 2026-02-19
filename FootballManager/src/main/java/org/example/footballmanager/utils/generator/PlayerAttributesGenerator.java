package org.example.footballmanager.utils.generator;

import org.example.footballmanager.model.embeddable.PlayerAttributes;
import org.example.footballmanager.utils.enums.PlayerPosition;
import org.springframework.stereotype.Component;

@Component
public class PlayerAttributesGenerator {

    public PlayerAttributes generateAttributesForPosition(PlayerPosition position) {
        return switch (position) {
            case FORWARD -> generateForwardAttributes();
            case MIDFIELDER -> generateMidfielderAttributes();
            case DEFENDER -> generateDefenderAttributes();
            case GOALKEEPER -> generateGoalkeeperAttributes();
        };
    }

    public PlayerAttributes generateForwardAttributes() {
        PlayerAttributes attributes = new PlayerAttributes();
        attributes.setSpeed(80);
        attributes.setStrength(70);
        return attributes;
    }

    public PlayerAttributes generateMidfielderAttributes() {
        PlayerAttributes attributes = new PlayerAttributes();
        attributes.setSpeed(70);
        attributes.setStrength(75);
        return attributes;
    }

    public PlayerAttributes generateDefenderAttributes() {
        PlayerAttributes attributes = new PlayerAttributes();
        attributes.setSpeed(60);
        attributes.setStrength(85);
        return attributes;
    }

    public PlayerAttributes generateGoalkeeperAttributes() {
        PlayerAttributes attributes = new PlayerAttributes();
        attributes.setSpeed(50);
        attributes.setStrength(90);
        return attributes;
    }
}
