package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.repositories.DifficulteRepository;
import fr.iut.sciencequest.sae.services.interfaces.IDifficulteService;
import org.springframework.stereotype.Service;

@Service
public class DifficulteService implements IDifficulteService {
    private final DifficulteRepository difficulteRepository;

    public DifficulteService(DifficulteRepository difficulteRepository){
        this.difficulteRepository = difficulteRepository;
    }

    @Override
    public Iterable<Difficulte> findAll(){
        return this.difficulteRepository.findAll();
    }
}
