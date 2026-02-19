package org.example.footballmanager.utils.exceptions;

public class MaxPlayerPerClubException extends GameException{
    public MaxPlayerPerClubException(Long clubId) {
        super("There is no space in the club (ID: " + clubId + ") to add more players.");
    }
}
