package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Thematique;
import tn.esprit.spring.repositories.ThematiqueRepository;
import tn.esprit.spring.services.ThematiqueServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/thematiques")
public class ThematiqueController {

    @Autowired
    private ThematiqueServiceImp thematiqueService;
    private ThematiqueRepository thematiqueRepository;

    @GetMapping
    public List<Thematique> getAllThematiques() {
        return thematiqueService.getAllThematiques();
    }

    @GetMapping("/{id}")
    public Thematique getThematiqueById(@PathVariable Long id) {
        return thematiqueService.getThematiqueById(id);
    }

    @PostMapping("/{userId}")
    public Thematique createThematique(@RequestBody Thematique thematique ,@PathVariable Long userId ) {
        return thematiqueService.createThematique(thematique,userId);
    }

    @PutMapping("/{id}")
    public Thematique updateThematique(@PathVariable Long id, @RequestBody Thematique thematique) {
        return thematiqueService.updateThematique(id, thematique);
    }

    @DeleteMapping("/{id}")
    public void deleteThematique(@PathVariable Long id) {
        thematiqueService.deleteThematique(id);
    }





}

