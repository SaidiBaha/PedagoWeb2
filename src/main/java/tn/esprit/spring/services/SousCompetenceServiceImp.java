package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.CompetenceRepository;
import tn.esprit.spring.repositories.SousCompetenceRepository;


import java.util.List;

@AllArgsConstructor

@Service
public class SousCompetenceServiceImp implements ISousCompetenceService {

    private SousCompetenceRepository sousCompetenceRepository;
    private CompetenceRepository competenceRepository;



    public List<SousCompetence> getAllSousCompetences() {
        return sousCompetenceRepository.findAll();
    }

    public SousCompetence getSousCompetenceById(Long id) {
        return sousCompetenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SousCompetence not found with id: " + id));
    }

    public SousCompetence createSousCompetence(Long competenceId, SousCompetence sousCompetence) {
        Competence competence = competenceRepository.findById(competenceId)
                .orElseThrow(() -> new NotFoundException("Competence not found with id: " + competenceId));

        sousCompetence.setCompetence(competence);
        return sousCompetenceRepository.save(sousCompetence);
    }

    public SousCompetence updateSousCompetence(Long id, SousCompetence sousCompetence) {
        SousCompetence existingSousCompetence = getSousCompetenceById(id);
        existingSousCompetence.setNom(sousCompetence.getNom());

        return sousCompetenceRepository.save(existingSousCompetence);
    }

    public void deleteSousCompetence(Long id) {
        SousCompetence sousCompetence = getSousCompetenceById(id);
        sousCompetenceRepository.delete(sousCompetence);
    }

//** function getSousCompentencesByCompetenceID**///////////


    public List<SousCompetence>getSousCompetencesByCompetenceId(Long competenceId){

        return sousCompetenceRepository.findByCompetenceId(competenceId);
    }









}
