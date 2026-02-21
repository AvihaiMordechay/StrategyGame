package org.example.footballmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplacePlayerInLineupDTO {
    private Long clubPlayerIdOut;
    private Long clubPlayerIdIn;
}
