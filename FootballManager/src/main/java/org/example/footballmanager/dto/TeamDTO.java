package org.example.footballmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDTO {
    private List<ClubPlayerDTO> players;
}
