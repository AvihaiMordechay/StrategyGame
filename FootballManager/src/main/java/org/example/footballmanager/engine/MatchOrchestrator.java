package org.example.footballmanager.engine;

import org.example.footballmanager.model.match.Match;
import org.example.footballmanager.repository.MatchRepository;
import org.example.footballmanager.service.MatchService;
import org.example.footballmanager.utils.enums.MatchStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchOrchestrator {

    private final MatchRepository matchRepository;
    private final MatchService matchService;
    private final GameEngine gameEngine;

    public MatchOrchestrator(MatchRepository matchRepository, MatchService matchService, GameEngine gameEngine) {
        this.matchRepository = matchRepository;
        this.matchService = matchService;
        this.gameEngine = gameEngine;
    }

    @Transactional
    public void process() {
        LocalDateTime now = LocalDateTime.now();

        List<Match> matchesToPrepare = matchRepository.findByStatus(MatchStatus.SCHEDULED);

        for (Match match : matchesToPrepare) {
            matchService.prepareMatch(match.getId());
        }

        List<Match> matchesToStart = matchRepository.findByStatusAndStartTimeBefore(MatchStatus.NEXT_ROUND, now);

        for (Match match : matchesToStart) {
            gameEngine.startMatch(match);
        }

        List<Match> runningMatches = matchRepository.findByStatus(MatchStatus.RUNNING);

        for (Match match : runningMatches) {
            gameEngine.runNextTick(match);
        }
    }
}
