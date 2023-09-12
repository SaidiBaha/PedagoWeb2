package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Competence;
import tn.esprit.spring.services.CompetenceServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/competences")
public class CompetenceController {

    private CompetenceServiceImp competenceService;

    @GetMapping
    public List<Competence> getAllCompetences() {
        return competenceService.getAllCompetences();
    }

    @GetMapping("/{id}")
    public Competence getCompetenceById(@PathVariable Long id) {
        return competenceService.getCompetenceById(id);
    }

    @PostMapping("/{thematiqueId}")
    public Competence createCompetence(@PathVariable Long thematiqueId, @RequestBody Competence competence) {
        return competenceService.createCompetence(thematiqueId, competence);
    }

    @PutMapping("/{id}")
    public Competence updateCompetence(@PathVariable Long id, @RequestBody Competence competence) {
        return competenceService.updateCompetence(id, competence);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
    }


    @GetMapping("/by-thematique/{thematiqueId}")
    public ResponseEntity<List<Competence>> getCompetencesByThematiqueId(@PathVariable Long thematiqueId) {
        List<Competence> competences = competenceService.getCompetencesByThematiqueId(thematiqueId);
        return ResponseEntity.ok(competences);
    }

}
