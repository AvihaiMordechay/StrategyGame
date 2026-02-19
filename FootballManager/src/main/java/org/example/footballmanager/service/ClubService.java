package org.example.footballmanager.service;

import org.example.footballmanager.dto.ClubDTO;
import org.example.footballmanager.dto.PlayerDTO;
import org.example.footballmanager.dto.TeamDTO;
import org.example.footballmanager.model.Club;
import org.example.footballmanager.model.Player;
import org.example.footballmanager.repository.ClubRepository;
import org.example.footballmanager.repository.PlayerRepository;
import org.example.footballmanager.utils.enums.PlayerStatus;
import org.example.footballmanager.utils.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.footballmanager.utils.GameConstants.*;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    public ClubService(ClubRepository clubRepository, PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
    }

    public void addClub(ClubDTO clubDTO) {
        Club club = new Club();
        club.setName(clubDTO.getName());
        club.setBudget(clubDTO.getBudget());
        clubRepository.save(club);
    }

    public void addPlayerToClub(Long clubId, Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
        if (player.getClub() != null) {
            throw new PlayerAlreadyInClubException(player.getClub().getId(), playerId);
        }
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubNotFoundException(clubId));
        if (club.getTeam().size() + 1 > MAX_PLAYERS_PER_CLUB) {
            throw new MaxPlayerPerClubException(clubId);
        }
        player.setClub(club);
        club.getTeam().add(player);
        playerRepository.save(player);
    }

    public TeamDTO getTeamDTO(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubNotFoundException(clubId));

        List<PlayerDTO> collect = club.getTeam()
                .stream()
                .map(player -> new PlayerDTO(
                        player.getId(),
                        player.getFirstName(),
                        player.getLastName(),
                        player.getAge(),
                        player.getPosition(),
                        player.getCurrentStatus(),
                        player.getAttributes()

                ))
                .collect(Collectors.toList());

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setPlayers(collect);
        return teamDTO;
    }

    public List<Player> getMatchSquad(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ClubNotFoundException(clubId));

        List<Player> team = club.getTeam();
        if (team == null) {
            team = Collections.emptyList();
        }
        List<Player> matchSquad = team.stream()
                .filter(player -> player.getCurrentStatus() != null &&
                        (player.getCurrentStatus() == PlayerStatus.MATCH_SQUAD ||
                                player.getCurrentStatus() == PlayerStatus.STARTING_LINEUP))
                .toList();

        if (matchSquad.size() > MAX_PLAYERS_MATCH_SQUAD) {
            // TODO: THINGS WHAT TO DO AND WHO WILL CATCH THIS ERROR
            throw new RuntimeException("Too many players in match squad for club: " + clubId);
        } else if (matchSquad.size() < MIN_PLAYERS_MATCH_SQUAD) {
            throw new NoLineupDefinedException(clubId);
        }
        return matchSquad;
    }

}


