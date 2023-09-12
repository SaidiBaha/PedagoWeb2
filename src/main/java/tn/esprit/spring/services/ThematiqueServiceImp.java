package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.spring.entity.Competence;
import tn.esprit.spring.entity.NotFoundException;
import tn.esprit.spring.entity.Thematique;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.ThematiqueRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.util.List;

@AllArgsConstructor

@Service
public class ThematiqueServiceImp implements IThematiqueService {


    private ThematiqueRepository thematiqueRepository;
    private UserRepository userRepository;

    public List<Thematique> getAllThematiques() {
        return thematiqueRepository.findAll();
    }

    public Thematique getThematiqueById(Long id) {
        return thematiqueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Thematique not found with id: " + id));
    }

    public Thematique createThematique(Thematique thematique, Long userId ) {
        User user = userRepository.findById(userId).get();
        thematique.setUser(user);
        return thematiqueRepository.save(thematique);
    }

    public Thematique updateThematique(Long id, Thematique thematique) {
        Thematique existingThematique = getThematiqueById(id);
        existingThematique.setNom(thematique.getNom());

        return thematiqueRepository.save(existingThematique);
    }

    public void deleteThematique(Long id) {
        Thematique thematique = getThematiqueById(id);
        thematiqueRepository.delete(thematique);
    }
    //////////////////////////////////////////////////////////////////////



}
