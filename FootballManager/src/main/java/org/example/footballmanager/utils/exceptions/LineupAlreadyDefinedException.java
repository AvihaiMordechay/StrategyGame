package org.example.footballmanager.utils.exceptions;

public class LineupAlreadyDefinedException extends GameException {
    public LineupAlreadyDefinedException(Long clubId) {
        super("Club with id " + clubId + " already has a lineup defined");
    }
}
