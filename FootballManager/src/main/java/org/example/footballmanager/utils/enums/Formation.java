package org.example.footballmanager.utils.enums;

import java.util.Set;

import static org.example.footballmanager.utils.enums.MatchPosition.*;

public enum Formation {
    /** 4-3-3: 1 GK, 4 defenders, 3 midfielders, 3 forwards */
    F433(Set.of(GK, RB, RCB, LCB, LB, CDM, CM, CAM, LW, ST, RW)),
    /** 4-4-2: 1 GK, 4 defenders, 4 midfielders, 2 forwards */
    F442(Set.of(GK, RB, RCB, LCB, LB, RCM, LCM, RAM, LAM, ST, CF));

    private final Set<MatchPosition> allowedFieldPositions;

    Formation(Set<MatchPosition> allowedFieldPositions) {
        this.allowedFieldPositions = allowedFieldPositions;
    }

    public Set<MatchPosition> getAllowedFieldPositions() {
        return allowedFieldPositions;
    }

    /** Returns true if this position is valid for this formation (field only; SUB is always allowed separately). */
    public boolean isAllowedFieldPosition(MatchPosition position) {
        return position != null && allowedFieldPositions.contains(position);
    }
}
