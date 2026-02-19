package org.example.footballmanager.utils.exceptions;

public class NoLineupDefinedException extends GameException {

    public NoLineupDefinedException(Long clubId) {
        super("No lineup defined for club: " + clubId);
    }
}
