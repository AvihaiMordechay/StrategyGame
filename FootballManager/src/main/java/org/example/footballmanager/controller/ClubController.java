package org.example.footballmanager.controller;

import org.example.footballmanager.dto.ClubDTO;
import org.example.footballmanager.dto.TeamDTO;
import org.example.footballmanager.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClub(@RequestBody ClubDTO clubDTO) {
        clubService.addClub(clubDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{clubId}/player/{playerId}")
    public ResponseEntity<?> addPlayerToClub(@PathVariable Long clubId, @PathVariable Long playerId) {
        clubService.addPlayerToClub(clubId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{clubId}/team")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable Long clubId) {
        TeamDTO team = clubService.getTeamDTO(clubId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

}
