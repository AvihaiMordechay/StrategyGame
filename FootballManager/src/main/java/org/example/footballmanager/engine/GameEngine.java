package org.example.footballmanager.engine;

import org.example.footballmanager.model.Match;
import org.example.footballmanager.repository.MatchRepository;
import org.example.footballmanager.service.BallService;
import org.example.footballmanager.service.ClubService;
import org.example.footballmanager.utils.enums.MatchStatus;
import org.example.footballmanager.utils.exceptions.MatchCannotStartException;
import org.example.footballmanager.utils.exceptions.MatchIsNotRunningException;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {

    private final BallService ballService;
    private final ClubService clubService;

    public GameEngine(ClubService clubService, BallService ballService) {
        this.clubService = clubService;
        this.ballService = ballService;
    }

    public void startMatch(Match match) {
        if (match.getStatus() == null || match.getStatus() != MatchStatus.NEXT_ROUND) {
            throw new MatchCannotStartException(match.getId());
        }
        match.setStatus(MatchStatus.RUNNING);
        match.setBall(ballService.addBall(match));
    }

    public void runNextTick(Match match) {
        if (match.getStatus() != MatchStatus.RUNNING) {
            throw new MatchIsNotRunningException(match.getId());
        }
        match.setCurrentTick(match.getCurrentTick() + 1);

    }
}
