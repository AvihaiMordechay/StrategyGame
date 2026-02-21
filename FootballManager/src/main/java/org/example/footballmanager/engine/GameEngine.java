package org.example.footballmanager.engine;

import org.example.footballmanager.model.match.Ball;
import org.example.footballmanager.model.match.Match;
import org.example.footballmanager.service.BallService;
import org.example.footballmanager.utils.enums.MatchStatus;
import org.example.footballmanager.utils.exceptions.MatchCannotStartException;
import org.example.footballmanager.utils.exceptions.MatchIsNotRunningException;
import org.springframework.stereotype.Component;

import static org.example.footballmanager.utils.GameConstants.TICKS_GAME;

@Component
public class GameEngine {

    private final BallService ballService;

    public GameEngine(BallService ballService) {
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
        if (match.getCurrentTick() == TICKS_GAME) {
            match.setStatus(MatchStatus.FINISHED);
            // TODO: UPDATE LEAGUE
        }
        match.setCurrentTick(match.getCurrentTick() + 1);
        Ball ball = match.getBall();

        switch (ball.getPhase()){
            case BUILD_UP -> {

            }
            case DEFENSIVE -> {

            }
        }

    }
}
