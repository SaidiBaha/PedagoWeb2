package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Answer;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
