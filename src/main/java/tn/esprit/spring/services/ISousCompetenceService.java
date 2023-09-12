package tn.esprit.spring.services;

import tn.esprit.spring.entity.SousCompetence;

import java.util.List;

public interface ISousCompetenceService {

    public List<SousCompetence> getAllSousCompetences();
    public SousCompetence getSousCompetenceById(Long id);
    public SousCompetence createSousCompetence(Long competenceId, SousCompetence sousCompetence);
    public SousCompetence updateSousCompetence(Long id, SousCompetence sousCompetence);
    public void deleteSousCompetence(Long id);
}
