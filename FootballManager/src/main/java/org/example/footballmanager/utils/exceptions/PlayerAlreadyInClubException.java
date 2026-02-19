package org.example.footballmanager.utils.exceptions;

public class PlayerAlreadyInClubException extends GameException{

    public PlayerAlreadyInClubException(Long clubId, Long playerId) {
        super("Player with id " + playerId + " is already assigned to club with id: " + clubId);
    }
}
