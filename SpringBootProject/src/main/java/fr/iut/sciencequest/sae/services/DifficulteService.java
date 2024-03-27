package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.notFound.DifficulteNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.DifficulteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DifficulteService {
    private final DifficulteRepository difficulteRepository;

    public DifficulteService(DifficulteRepository difficulteRepository){
        this.difficulteRepository = difficulteRepository;
    }

    public Page<Difficulte> findAll(Pageable p){
        return this.difficulteRepository.findAll(p);
    }

    public Difficulte findById(int id) {
        return this.difficulteRepository.findById(id).orElseThrow(() ->
                new DifficulteNotFoundException(id)
        );
    }
}
