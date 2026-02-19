package org.example.footballmanager.service;

import org.example.footballmanager.model.Ball;
import org.example.footballmanager.model.Match;
import org.example.footballmanager.repository.BallRepository;
import org.example.footballmanager.utils.enums.BallPhase;
import org.example.footballmanager.utils.exceptions.BallNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BallService {
    private static final int MIDDLE_POSITION = 50;

    private final BallRepository ballRepository;

    public BallService(BallRepository ballRepository) {
        this.ballRepository = ballRepository;
    }

    public Ball addBall(Match match) {
        Ball ball = new Ball();
        ball.setMatch(match);
        ball.setPositon(MIDDLE_POSITION);
        ball.setPhase(BallPhase.BUILD_UP);
        ball = ballRepository.save(ball);
        match.setBall(ball);
        return ball;
    }

    public Ball updateBall(Long ballId, int position, BallPhase phase) {
        Ball ball = ballRepository.findById(ballId)
                .orElseThrow(() -> new BallNotFoundException(ballId));
        ball.setPositon(position);
        ball.setPhase(phase);
        return ballRepository.save(ball);
    }
}
