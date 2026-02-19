package org.example.footballmanager.controller;

import org.example.footballmanager.dto.MatchDTO;
import org.example.footballmanager.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMatch(@RequestBody MatchDTO matchDTO) {
        matchService.addMatch(matchDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{matchId}/prepare")
    public ResponseEntity<?> prepareMatch(@PathVariable Long matchId) {
        matchService.prepareMatch(matchId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
