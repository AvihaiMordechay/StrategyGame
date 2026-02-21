package org.example.footballmanager.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.footballmanager.utils.enums.Formation;

@Getter
@Setter
public class DefineLineupDTO {
    private Formation formation;
}
