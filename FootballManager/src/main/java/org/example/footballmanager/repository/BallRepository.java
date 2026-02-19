package org.example.footballmanager.repository;

import org.example.footballmanager.model.Ball;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BallRepository extends JpaRepository<Ball, Long> {
}
