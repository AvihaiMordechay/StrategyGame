package org.example.footballmanager.repository;

import org.example.footballmanager.model.match.MatchLineup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchLineupRepository extends JpaRepository<MatchLineup, Long> {
}
