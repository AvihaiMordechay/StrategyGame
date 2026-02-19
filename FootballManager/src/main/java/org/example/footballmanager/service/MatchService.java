package org.example.footballmanager.service;

import org.example.footballmanager.dto.MatchDTO;
import org.example.footballmanager.model.Match;
import org.example.footballmanager.repository.MatchRepository;
import org.example.footballmanager.utils.enums.MatchStatus;
import org.example.footballmanager.utils.enums.TeamSide;
import org.example.footballmanager.utils.exceptions.MatchNotFoundException;
import org.example.footballmanager.utils.exceptions.NoLineupDefinedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final ClubService clubService;

    public MatchService(MatchRepository matchRepository, ClubService clubService) {
        this.matchRepository = matchRepository;
        this.clubService = clubService;
    }

    public void addMatch(MatchDTO matchDTO) {
        Match match = new Match();
        match.setHomeClubId(matchDTO.getHomeClubId());
        match.setAwayClubId(matchDTO.getAwayClubId());
        match.setStatus(MatchStatus.SCHEDULED);
        match.setStartTime(matchDTO.getStartTime());
        match.setHomeScore(0);
        match.setAwayScore(0);
        match.setCurrentTick(0);
        matchRepository.save(match);
    }

    private Match getMatch(Long matchId) {
        return matchRepository.findById(matchId).orElseThrow(() -> new MatchNotFoundException(matchId));
    }

    public void prepareMatch(Long matchId) {
        Match match = getMatch(matchId);
        match.setStatus(MatchStatus.NEXT_ROUND);
        match.setEvents(new ArrayList<>());
        match.setHomeScore(0);
        match.setAwayScore(0);
        match.setCurrentTick(0);
        try {
            match.setHomeSquad(clubService.getMatchSquad(match.getHomeClubId()));
        } catch (NoLineupDefinedException e) {
            match.setStatus(MatchStatus.FINISHED);
            addGoal(match, TeamSide.AWAY, 3);
            // TODO: ADD EVENT THAT THE GAME OVER
            // TODO: MINUS POINTS TO HOME CLUB (LEAGUE)
        }
        try {
            match.setAwaySquad(clubService.getMatchSquad(match.getAwayClubId()));
        } catch (NoLineupDefinedException e) {
            match.setStatus(MatchStatus.FINISHED);
            addGoal(match, TeamSide.HOME, 3);
            // TODO: ADD EVENT THAT THE GAME OVER
            // TODO: MINUS POINTS TO AWAY CLUB (LEAGUE)
        }
        // if the score is 3-3, the game is over and the points are added to the clubs
        if (match.getHomeScore() == 3 && match.getAwayScore() == 3) {
            // TODO: DELETE THE EVENTS AND INSERT A NEW EVENT THAT TELL THE SITUATION
            // TODO: ADD 3 POINTS TO EACH CLUB(LEAGUE)
            addGoal(match, TeamSide.HOME, -3);
            addGoal(match, TeamSide.AWAY, -3);
        }
        matchRepository.save(match);
    }

    private void addGoal(Match match, TeamSide side, int amount) {
        if (side == TeamSide.HOME) {
            match.setHomeScore(match.getHomeScore() + amount);
        } else {
            match.setAwayScore(match.getAwayScore() + amount);
        }
    }

    public void addGoal(Match match, TeamSide side) {
        addGoal(match, side, 1);
    }

}
