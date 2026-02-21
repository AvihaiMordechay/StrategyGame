package org.example.footballmanager.utils.exceptions;

public class ClubPlayerNotFoundException extends GameException {
    public ClubPlayerNotFoundException(Long clubPlayerId) {
        super("Club player with id " + clubPlayerId + " not found");
    }
}
