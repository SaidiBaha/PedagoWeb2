package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Score;


@Repository

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
