package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Competence;
import tn.esprit.spring.entity.NotFoundException;
import tn.esprit.spring.entity.Thematique;
import tn.esprit.spring.repositories.CompetenceRepository;
import tn.esprit.spring.repositories.ThematiqueRepository;

import java.util.List;

@AllArgsConstructor

@Service
public class CompetenceServiceImp implements ICompetenceService {

    private CompetenceRepository competenceRepository;
    private ThematiqueRepository thematiqueRepository;


    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    public Competence getCompetenceById(Long id) {
        return competenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Competence not found with id: " + id));
    }

    public Competence createCompetence(Long thematiqueId, Competence competence) {
        Thematique thematique = thematiqueRepository.findById(thematiqueId)
                .orElseThrow(() -> new NotFoundException("Thematique not found with id: " + thematiqueId));

        competence.setThematique(thematique);
        return competenceRepository.save(competence);
    }

    public Competence updateCompetence(Long id, Competence competence) {
        Competence existingCompetence = getCompetenceById(id);
        existingCompetence.setNom(competence.getNom());

        return competenceRepository.save(existingCompetence);
    }

    public void deleteCompetence(Long id) {
        Competence competence = getCompetenceById(id);
        competenceRepository.delete(competence);
    }

    ////////////////////////////////////////////


    public List<Competence> getCompetencesByThematiqueId(Long thematiqueId) {
        return competenceRepository.findByThematiqueId(thematiqueId);
    }

}
