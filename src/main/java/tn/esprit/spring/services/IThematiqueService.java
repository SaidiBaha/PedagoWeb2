package tn.esprit.spring.services;

import tn.esprit.spring.entity.Thematique;

import java.util.List;

public interface IThematiqueService {

    public List<Thematique> getAllThematiques();
    public Thematique getThematiqueById(Long id);
    public Thematique createThematique(Thematique thematique, Long userId );
    public Thematique updateThematique(Long id, Thematique thematique);
    public void deleteThematique(Long id);

}
