package org.example.footballmanager.utils.exceptions;

public class MatchIsNotRunningException extends GameException {
    public MatchIsNotRunningException(Long matchId) {
        super("Match with id " + matchId + " is not running.");
    }
}
