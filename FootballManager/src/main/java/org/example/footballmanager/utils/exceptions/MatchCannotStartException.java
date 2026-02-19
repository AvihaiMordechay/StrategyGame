package org.example.footballmanager.utils.exceptions;

public class MatchCannotStartException extends GameException {
    public MatchCannotStartException(Long matchId) {
        super("Match with id " + matchId + " cannot be started.");
    }
}
