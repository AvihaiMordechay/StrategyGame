package org.example.footballmanager.utils.exceptions;

public class MatchNotFoundException extends GameException {

    public MatchNotFoundException(Long id) {
        super("Match with id " + id + " not found");
    }
}
