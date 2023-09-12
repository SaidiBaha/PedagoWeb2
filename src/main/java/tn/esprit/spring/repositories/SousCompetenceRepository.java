package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Competence;
import tn.esprit.spring.entity.SousCompetence;

import java.util.List;

@Repository
public interface SousCompetenceRepository extends JpaRepository<SousCompetence, Long> {

    List<SousCompetence> findByCompetenceId(Long competenceId);
}
