package org.example.footballmanager.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.utils.enums.MatchPosition;

@Getter
@Setter
public class AddPlayerToLineupDTO {
    private Long clubPlayerId;
    private MatchPosition position;
}
