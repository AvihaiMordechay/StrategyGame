package org.example.footballmanager.service;

import org.example.footballmanager.dto.ClubDTO;
import org.example.footballmanager.dto.ClubPlayerDTO;
import org.example.footballmanager.dto.TeamDTO;
import org.example.footballmanager.model.club.Club;
import org.example.footballmanager.model.club.ClubLineup;
import org.example.footballmanager.model.match.MatchLineup;
import org.example.footballmanager.model.player.ClubLineupPlayer;
import org.example.footballmanager.model.player.ClubPlayer;
import org.example.footballmanager.model.player.MatchPlayer;
import org.example.footballmanager.model.player.Player;
import org.example.footballmanager.repository.ClubPlayerRepository;
import org.example.footballmanager.repository.ClubRepository;
import org.example.footballmanager.repository.PlayerRepository;
import org.example.footballmanager.utils.enums.Formation;
import org.example.footballmanager.utils.enums.MatchPosition;
import org.example.footballmanager.utils.enums.PlayerStatus;
import org.example.footballmanager.utils.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Locale.filter;
import static org.example.footballmanager.utils.GameConstants.*;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubPlayerRepository clubPlayerRepository;
    private final PlayerRepository playerRepository;

    public ClubService(ClubRepository clubRepository, ClubPlayerRepository clubPlayerRepository, PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.clubPlayerRepository = clubPlayerRepository;
        this.playerRepository = playerRepository;
    }

    public void addClub(ClubDTO clubDTO) {
        Club club = new Club();
        club.setName(clubDTO.getName());
        club.setBudget(clubDTO.getBudget());
        club.setTeam(new ArrayList<>());
        clubRepository.save(club);
    }

    public Club getClub(Long clubId) {
        return clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubNotFoundException(clubId));
    }

    public void addPlayerToClub(Long clubId, Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
        if (player.isHasClub()) {
            throw new PlayerAlreadyInClubException(playerId);
        }
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubNotFoundException(clubId));
        if (club.getTeam().size() >= MAX_PLAYERS_PER_CLUB) {
            throw new MaxPlayerPerClubException(clubId);
        }

        ClubPlayer clubPlayer = new ClubPlayer();
        clubPlayer.setPlayer(player);
        clubPlayer.setClub(club);
        clubPlayer.setShirtNumber(club.getTeam().size() + 1); // TODO: handle shirt number properly
        clubPlayer.setSalary(0); // TODO: set salary properly
        clubPlayer.setClubStatus(PlayerStatus.SQUAD);

        club.getTeam().add(clubPlayer);
        player.setHasClub(true);

        clubPlayerRepository.save(clubPlayer);
        playerRepository.save(player);
    }

    public TeamDTO getTeamDTO(Long clubId) {
        Club club = getClub(clubId);

        List<ClubPlayerDTO> collect = club.getTeam()
                .stream()
                .map(clubPlayer -> new ClubPlayerDTO(
                        clubPlayer.getId(),
                        clubPlayer.getPlayer(),
                        clubPlayer.getShirtNumber(),
                        clubPlayer.getSalary(),
                        clubPlayer.getClubStatus()

                ))
                .collect(Collectors.toList());

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setPlayers(collect);
        return teamDTO;
    }

    public void defineLineup(Long clubId, Formation formation) {
        Club club = getClub(clubId);

        ClubLineup lineup = club.getCurrentLineup();
        if (lineup != null) {
            throw new LineupAlreadyDefinedException(clubId);
        } else {
            lineup = new ClubLineup();
            lineup.setFormation(formation);
            lineup.setClub(club);
            lineup.setPlayers(new ArrayList<>());
            club.setCurrentLineup(lineup);
        }
        clubRepository.save(club);
    }

    public void addPlayerToLineup(Long clubId, Long clubPlayerId, MatchPosition position) {
        Club club = getClub(clubId);
        ClubLineup lineup = club.getCurrentLineup();
        if (lineup == null) {
            throw new NoLineupDefinedException(clubId);
        }
        ClubPlayer clubPlayer = clubPlayerRepository.findById(clubPlayerId)
                .orElseThrow(() -> new ClubPlayerNotFoundException(clubPlayerId));
        if (!club.equals(clubPlayer.getClub())) {
            throw new InvalidLineupAssignmentException("Player does not belong to this club");
        }
        boolean alreadyInLineup = lineup.getPlayers().stream()
                .anyMatch(lp -> lp.getClubPlayer().getId().equals(clubPlayerId));
        if (alreadyInLineup) {
            throw new InvalidLineupAssignmentException("Player is already in the lineup");
        }
        if (position == MatchPosition.SUB && lineup.getPlayers().stream().filter(lp -> lp.getPosition() == MatchPosition.SUB).count() >= MAX_SUBSTITUTIONS) {
            throw new InvalidLineupAssignmentException("Maximum number of substitutes (" + MAX_SUBSTITUTIONS + ") already in lineup");
        }
        if (position != MatchPosition.SUB && !lineup.getFormation().isAllowedFieldPosition(position)) {
            throw new InvalidLineupAssignmentException(
                    "Position " + position + " is not valid for formation " + lineup.getFormation());
        }
        if (position != MatchPosition.SUB && lineup.getPlayers().stream()
                .anyMatch(lp -> lp.getPosition() == position)) {
            throw new InvalidLineupAssignmentException("Position " + position + " is already taken");
        }
        if (lineup.getPlayers().size() >= MAX_PLAYERS_MATCH_SQUAD) {
            throw new InvalidLineupAssignmentException("Lineup is full (max " + MAX_PLAYERS_MATCH_SQUAD + " players)");
        }
        ClubLineupPlayer lineupPlayer = new ClubLineupPlayer();
        lineupPlayer.setLineup(lineup);
        lineupPlayer.setClubPlayer(clubPlayer);
        lineupPlayer.setPosition(position);
        lineup.getPlayers().add(lineupPlayer);
        clubRepository.save(club);
    }

    public void replacePlayerInLineup(Long clubId, Long clubPlayerIdOut, Long clubPlayerIdIn) {
        Club club = getClub(clubId);
        ClubLineup lineup = club.getCurrentLineup();
        if (lineup == null) {
            throw new NoLineupDefinedException(clubId);
        }

        ClubPlayer clubPlayerOut = clubPlayerRepository.findById(clubPlayerIdOut)
                .orElseThrow(() -> new ClubPlayerNotFoundException(clubPlayerIdOut));
        ClubPlayer clubPlayerIn = clubPlayerRepository.findById(clubPlayerIdIn)
                .orElseThrow(() -> new ClubPlayerNotFoundException(clubPlayerIdIn));
        if (!club.equals(clubPlayerOut.getClub()) || !club.equals(clubPlayerIn.getClub())) {
            throw new InvalidLineupAssignmentException("One or both players do not belong to this club");
        }

        ClubLineupPlayer lineupPlayerOut = lineup.getPlayers().stream()
                .filter(lp -> lp.getClubPlayer().getId().equals(clubPlayerIdOut))
                .findFirst()
                .orElseThrow(() -> new InvalidLineupAssignmentException("Player to replace is not in the lineup"));
        ClubLineupPlayer lineupPlayerIn = lineup.getPlayers().stream()
                .filter(lp -> lp.getClubPlayer().getId().equals(clubPlayerIdIn))
                .findFirst()
                .orElseThrow(() -> new InvalidLineupAssignmentException("Player to add is not in the lineup"));

        if (lineupPlayerOut.getPosition() == MatchPosition.SUB) {
            throw new InvalidLineupAssignmentException("Player to replace is already a substitute (on the bench)");
        }

        MatchPosition positionOut = lineupPlayerOut.getPosition();
        MatchPosition positionIn = lineupPlayerIn.getPosition();
        lineupPlayerOut.setPosition(positionIn);
        lineupPlayerIn.setPosition(positionOut);
        clubRepository.save(club);

    }

    public MatchLineup getMatchLineup(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubNotFoundException(clubId));

        ClubLineup currentLineup = club.getCurrentLineup();
        if (currentLineup == null
                || currentLineup.getPlayers().size() < MIN_PLAYERS_MATCH_SQUAD
                || currentLineup.getPlayers().size() > MAX_PLAYERS_MATCH_SQUAD // TODO: this should not happen if we validate properly when adding players to lineup
        ) {
            throw new NoLineupDefinedException(clubId);
        }

        MatchLineup matchLineup = new MatchLineup();
        matchLineup.setFormation(currentLineup.getFormation());
        List<MatchPlayer> matchPlayers = currentLineup.getPlayers().stream()
                .map(this::toMatchPlayer)
                .collect(Collectors.toList());
        matchLineup.setPlayers(matchPlayers);
        return matchLineup;
    }

    private MatchPlayer toMatchPlayer(ClubLineupPlayer clubLineupPlayer) {
        MatchPlayer matchPlayer = new MatchPlayer();
        matchPlayer.setClubPlayer(clubLineupPlayer.getClubPlayer());
        matchPlayer.setPosition(clubLineupPlayer.getPosition());
        return matchPlayer;
    }

}


