package org.example.footballmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatchDTO {
    private Long homeClubId;
    private Long awayClubId;
    private LocalDateTime startTime;
}
