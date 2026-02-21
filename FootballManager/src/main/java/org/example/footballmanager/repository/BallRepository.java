package org.example.footballmanager.repository;

import org.example.footballmanager.model.match.Ball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallRepository extends JpaRepository<Ball, Long> {
}
