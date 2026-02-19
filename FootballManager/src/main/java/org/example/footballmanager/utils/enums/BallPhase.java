package org.example.footballmanager.utils.enums;

/**
 * Basic phase of play based on ball position (MVP).
 */
public enum BallPhase {
    /** Ball in defensive third */
    DEFENSIVE,

    /** Ball in middle third, building play */
    BUILD_UP,

    /** Ball in attacking third */
    ATTACK,

    /** Set piece (corner, free kick, throw-in, penalty) */
    SET_PIECE
}
