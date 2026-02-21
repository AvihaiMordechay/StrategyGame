package org.example.footballmanager.repository;

import org.example.footballmanager.model.match.Match;
import org.example.footballmanager.utils.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByStatusAndStartTimeBefore(MatchStatus matchStatus, LocalDateTime time);

    List<Match> findByStatus(MatchStatus matchStatus);
}
