package org.example.footballmanager.utils.exceptions;

public class ClubNotFoundException extends GameException {
    public ClubNotFoundException(Long id) {
        super("Club with id " + id + " not found");
    }
}
