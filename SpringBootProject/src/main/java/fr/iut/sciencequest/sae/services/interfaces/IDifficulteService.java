package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Difficulte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDifficulteService {
    Page<Difficulte> findAll(Pageable p);
}
