package org.example.footballmanager.controller;

import org.example.footballmanager.dto.MatchDTO;
import org.example.footballmanager.engine.MatchOrchestrator;
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
    private final MatchOrchestrator matchOrchestrator;

    public MatchController(MatchService matchService, MatchOrchestrator matchOrchestrator) {
        this.matchService = matchService;
        this.matchOrchestrator = matchOrchestrator;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMatch(@RequestBody MatchDTO matchDTO) {
        matchService.addMatch(matchDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/prepare")
    public ResponseEntity<?> prepareMatch() {
        matchOrchestrator.process();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
