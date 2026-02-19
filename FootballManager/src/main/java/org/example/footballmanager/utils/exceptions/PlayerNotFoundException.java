package org.example.footballmanager.utils.exceptions;

public class PlayerNotFoundException extends GameException {

    public PlayerNotFoundException(Long playerId) {
        super("Player with id " + playerId + " not found.");
    }
}
