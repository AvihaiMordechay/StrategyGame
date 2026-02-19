package org.example.footballmanager.utils.exceptions;

public class BallNotFoundException extends GameException {

    public BallNotFoundException(Long id) {
        super("Ball with id " + id + " not found");
    }
}
