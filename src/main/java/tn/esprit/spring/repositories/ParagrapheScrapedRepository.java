package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.ParagrapheScraped;

@Repository
public interface ParagrapheScrapedRepository extends JpaRepository <ParagrapheScraped , Long> {
}
