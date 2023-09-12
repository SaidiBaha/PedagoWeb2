package tn.esprit.spring.services;

import tn.esprit.spring.entity.Competence;

import java.util.List;

public interface ICompetenceService {

    public List<Competence> getAllCompetences();
    public Competence getCompetenceById(Long id);
    public Competence createCompetence(Long thematiqueId, Competence competence);
    public Competence updateCompetence(Long id, Competence competence);
    public void deleteCompetence(Long id);
}
