package tn.esprit.spring.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.services.SousCompetenceServiceImp;
import tn.esprit.spring.services.WebScraperService;


import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/sous-competences")
public class SousCompetenceController {

    @Autowired
    private SousCompetenceServiceImp sousCompetenceService;

    @GetMapping
    public List<SousCompetence> getAllSousCompetences() {
        return sousCompetenceService.getAllSousCompetences();
    }

    @GetMapping("/{id}")
    public SousCompetence getSousCompetenceById(@PathVariable Long id) {
        return sousCompetenceService.getSousCompetenceById(id);
    }

    @PostMapping("/{competenceId}")
    public SousCompetence createSousCompetence(@PathVariable Long competenceId, @RequestBody SousCompetence sousCompetence) {
        return sousCompetenceService.createSousCompetence(competenceId, sousCompetence);
    }

    @PutMapping("/{id}")
    public SousCompetence updateSousCompetence(@PathVariable Long id, @RequestBody SousCompetence sousCompetence) {
        return sousCompetenceService.updateSousCompetence(id, sousCompetence);
    }

    @DeleteMapping("/{id}")
    public void deleteSousCompetence(@PathVariable Long id) {
        sousCompetenceService.deleteSousCompetence(id);
    }

    //**                                  **//











  /*  @GetMapping("/statistics")
    public ResponseEntity<Map<SousCompetence, Double>> getStatisticsBySousCompetence() {
        Map<SousCompetence, Double> statistics = sousCompetenceService.getStatisticsBySousCompetence();
        return ResponseEntity.ok(statistics);
    }*/

    @GetMapping("/by-thematique/{competenceId}")
    public ResponseEntity<List<SousCompetence>> getSousCompetencesByCompetenceId(@PathVariable Long competenceId) {
        List<SousCompetence> Souscompetences = sousCompetenceService.getSousCompetencesByCompetenceId(competenceId);
        return ResponseEntity.ok(Souscompetences);
    }


    @Autowired
    private WebScraperService webScraperService;

    @GetMapping("/scrape-and-save/{idSousCompetence}")
  /*  public ResponseEntity<String> scrapeAndSaveToDatabase(@PathVariable Long idSousCompetence, String url) {
        SousCompetence sousCompetence = getSousCompetenceById(idSousCompetence);
        url = "https://www.pass-education.be/lire-et-ecrire-les-nombres-de-0-a-99-au-cours-lecon-3eme-primaire/";
        try {
            String scrapedData = webScraperService.scrapeAndSaveToDatabase(url, sousCompetence);
            return ResponseEntity.ok(scrapedData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error scraping website.");
        }
    }*/
    public ResponseEntity<ScrapedDataResponse> scrapeAndSaveToDatabase(@PathVariable Long idSousCompetence, String url) {
        SousCompetence sousCompetence = getSousCompetenceById(idSousCompetence);
        url = "https://www.pass-education.be/additionner-et-soustraire-en-ligne-au-cours-lecon-3eme-primaire/";
        try {
            String scrapedData = webScraperService.scrapeAndSaveToDatabase(url, sousCompetence);

            ScrapedDataResponse response = new ScrapedDataResponse(scrapedData);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}