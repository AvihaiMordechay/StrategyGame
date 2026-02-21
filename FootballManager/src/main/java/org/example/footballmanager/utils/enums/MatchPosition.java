package org.example.footballmanager.utils.enums;

import lombok.Getter;

/**
 * Position on the pitch. Each position has two 1-100 values:
 * - homePosition: position from the home team's perspective (1 = home goal, 100 = away goal)
 * - awayPosition: position from the away team's perspective (1 = away goal, 100 = home goal)
 * Example: RB (Right Back) is 20 for home (defensive end) and 80 for away (same spot = attacking end for away).
 */
@Getter
public enum MatchPosition {
    SUB(-1, -1),   // Substitute (bench)

    GK(5, 95),     // Goalkeeper

    RB(20, 80),    // Right Back
    LB(20, 80),    // Left Back
    RWB(25, 75),   // Right Wing Back
    LWB(25, 75),   // Left Wing Back
    CB(18, 82),    // Center Back
    RCB(18, 82),   // Right Center Back
    LCB(18, 82),   // Left Center Back

    CDM(35, 65),   // Central Defensive Midfielder
    RDM(35, 65),   // Right Defensive Midfielder
    LDM(35, 65),   // Left Defensive Midfielder

    CM(50, 50),    // Central Midfielder
    RCM(50, 50),   // Right Central Midfielder
    LCM(50, 50),   // Left Central Midfielder

    CAM(65, 35),   // Central Attacking Midfielder
    RAM(65, 35),   // Right Attacking Midfielder
    LAM(65, 35),   // Left Attacking Midfielder

    RW(72, 28),    // Right Winger
    LW(72, 28),    // Left Winger

    CF(85, 15),    // Center Forward
    ST(85, 15),    // Striker
    RS(85, 15),    // Right Striker
    LS(85, 15);    // Left Striker

    private final int homePosition;
    private final int awayPosition;

    MatchPosition(int homePosition, int awayPosition) {
        this.homePosition = homePosition;
        this.awayPosition = awayPosition;
    }
}
